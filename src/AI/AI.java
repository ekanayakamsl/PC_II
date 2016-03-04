/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Actor.Actor;
import Actor.CoinAndLifePack;
import Actor.CoinPack;
import Actor.Player;
import java.util.ArrayList;

/**
 *
 * @author sranga
 */
public class AI {

    /**
     * @param args the command line arguments
     */
    ///first tank position is 0,0 and last is 9,9
    MoveWeight moveWeight = new MoveWeight(1000, 1000, 1000, 1000, 1000);
    private AiMap map;
    CheckMove checkMove = new CheckMove();
    CoinPack coin = new CoinPack(4, 3, 100, 100);
    static CoinAndLifePack curentDes;
    static int[][] valueMap = new int[10][10];

    public AI() {
        map = new AiMap();
    }

    public String processInputMessege(Actor[][] map, Player player, ArrayList<CoinAndLifePack> coinAndLifePacks) {

        System.out.println("========================map===============================");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j].getType() + "\t");
            }
            System.out.println("");
        }
        System.out.println("==palayer==");
        System.out.println("x  = " + player.getX() + " y = " + player.getY() + " d = " + player.getDirection());

        moveWeight = checkMove.checkObstaclesAndBoundary(player, map, moveWeight);

        if (player.getHealth() >= 40) {
            System.out.println("tank direction  " + player.getDirection() + " x " + player.getX() + " y " + player.getY());
            printPositionVector(map);
            moveWeight = checkMove.checkForTankShoot(map, player, moveWeight);
            moveWeight = checkMove.checkForBrickShoot(map, player, moveWeight);
        } else {
            moveWeight = checkMove.checkFofSave(player, map,moveWeight);
            moveWeight = checkMove.checkForBrickShoot(map, player, moveWeight);
        }

        CoinAndLifePack destination = checkMove.findBestdestination(player, coinAndLifePacks, moveWeight, map);
        if (destination != null) {
            if (curentDes != null) {
                if ((curentDes.getX() != destination.getX()) || (curentDes.getY() != destination.getY())) {
                    if (destination.getWeight() > curentDes.getWeight() + valueMap[player.getY()][player.getX()]) {
                        valueMap = checkMove.shorterstPath(player, destination, map);
                        curentDes = destination;
                    } else if (map[curentDes.getY()][curentDes.getX()].getType() != "C") {
                        valueMap = new int[10][10];
                    } else if (valueMap[player.getY()][player.getX()] == 0) {
                        valueMap = checkMove.shorterstPath(player, curentDes, map);
                    }
                }
            } else {
                valueMap = checkMove.shorterstPath(player, destination, map);
                curentDes = destination;
            }

        }

       
        System.out.println("value map  next move");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(valueMap[i][j] + "\t");
            }
            System.out.println("");
        }
        moveWeight = checkMove.chechNextmoveForDes(player, valueMap, moveWeight);
        System.out.println("       ");
        String nextMove = findBestMove(moveWeight);
        System.out.println(moveWeight.getUp() + " , " + moveWeight.getRight() + " , " + moveWeight.getDown() + " , " + moveWeight.getLeft() + " , " + moveWeight.getShoot());
        System.out.println("======NEXT MOVED========" + nextMove);
        return nextMove;
    }

    public String findBestMove(MoveWeight moveWeight) {

        int marks[] = {moveWeight.getUp(), moveWeight.getRight(), moveWeight.getDown(), moveWeight.getLeft(), moveWeight.getShoot()};
        int maxMark = marks[0];

        for (int i = 0; i < 4; i++) {
            if (marks[i + 1] > maxMark) {
                maxMark = marks[i + 1];
            }
        }

        if (maxMark > 0) {
            if (maxMark == moveWeight.getUp()) {
                return "UP#";
            } else if (maxMark == moveWeight.getRight()) {
                return "RIGHT#";
            } else if (maxMark == moveWeight.getDown()) {
                return "DOWN#";
            } else if (maxMark == moveWeight.getLeft()) {
                return "LEFT#";
            } else {
                return "SHOOT#";
            }
        } else {
            return "SHOOT#";
        }
    }

    public void printPositionVector(Actor[][] posVector) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(posVector[i][j].getType() + "  ");;
            }
            System.out.println("");
        }
    }


}
