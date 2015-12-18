/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Actor.Actor;
import Actor.CoinPack;
import Actor.LifePack;
import Actor.Player;
import java.util.ArrayList;
import tank.TankClient;

/**
 *
 * @author sranga
 */
public class AI {

    /**
     * @param args the command line arguments
     */
    ///first tank position is 0,0 and last is 9,9
    public static int Lmark;
    public static int Rmark;
    public static int Umark;
    public static int Dmark;
    private AiMap map;
    CheckMove checkMove = new CheckMove();
    CoinPack coin = new CoinPack(4, 3, 100, 100);

    public AI() {
        System.out.println("");
        map = new AiMap();
        printPositionVector(map.valueMap);

    }

    public String processInputMessege(Actor[][] map, Player player, ArrayList<LifePack> lifePacks, ArrayList<CoinPack> coinPacks) {
        //Map map = new Map();

        Umark = 0;
        Rmark = 0;
        Dmark = 0;
        Lmark = 0;

        checkMove.checkObstaclesAndBoundary(player, map, lifePacks, coinPacks);
        String nextMove;
        System.out.println(Umark + "  " + Rmark + "  " + Dmark + "  " + Lmark);
        if (checkMove.checkForShoot(map, player)) {
            nextMove = "SHOOT#";
        } else {
            nextMove = checkMove.findBestMove();
        }
        System.out.println(nextMove);
        return nextMove;
    }

    public void printPositionVector(int[][] posVector) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(posVector[i][j] + "  ");;
            }
            System.out.println("");
        }
    }

    public void printPositionVector(String[][] posVector) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(posVector[i][j] + "  ");;
            }
            System.out.println("");
        }
    }

}
