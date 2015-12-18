/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Actor.Actor;
import Actor.CoinPack;
import Actor.Empty;
import Actor.LifePack;
import Actor.Player;
import java.util.ArrayList;

/**
 *
 * @author sranga
 */
public class CheckMove {

    int[][] valueMap = new int[10][10];
    static int pretankX;
    static int preTankY;

    public boolean checkObstaclesAndBoundary(Player tank, Actor[][] map, ArrayList<LifePack> lifePacks, ArrayList<CoinPack> coinPacks) {

        int curentX = tank.getX();
        int curentY = tank.getY();

        boolean shoot = checkForShoot(map, tank);
        System.out.println("shoot   " + shoot);
        Actor destinationActor = findBestdestination(lifePacks, coinPacks, tank);
        CreateValueMap(destinationActor, tank);
        try {
            if (map[curentY - 1][curentX].getType() == "W" || map[curentY - 1][curentX].getType() == "S" || map[curentY - 1][curentX].getType() == "B" || map[curentY - 1][curentX].getType() == "T") {
                AI.Umark = -1000;
            } else {
                AI.Umark += valueMap[curentY - 1][curentX];
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            AI.Umark = -1000;
        }
        //check for right
        try {
            if (map[curentY][curentX + 1].getType() == "W" || map[curentY][curentX + 1].getType() == "S" || map[curentY][curentX + 1].getType() == "B" || map[curentY][curentX + 1].getType() == "T") {
                AI.Rmark = -1000;
            } else {
                AI.Rmark += valueMap[curentY][curentX + 1];
            }

        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            AI.Rmark = -1000;
        }
        //check for down

        try {
            if (map[curentY + 1][curentX].getType() == "W" || map[curentY + 1][curentX].getType() == "S" || map[curentY + 1][curentX].getType() == "B" || map[curentY + 1][curentX].getType() == "T") {
                AI.Dmark = -1000;
            } else {
                AI.Dmark += valueMap[curentY + 1][curentX];
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            AI.Dmark = -1000;
        }

        //check for left
        try {
            if (map[curentY][curentX - 1].getType() == "W" || map[curentY][curentX - 1].getType() == "S" || map[curentY][curentX - 1].getType() == "B" || map[curentY][curentX - 1].getType() == "T") {
                AI.Lmark = -1000;
            } else {
                AI.Lmark += valueMap[curentY][curentX - 1];
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            AI.Lmark = -1000;
        }
        return shoot;

    }

    public String findBestMove() {

        String dir = "";
        int marks[] = {AI.Umark, AI.Rmark, AI.Dmark, AI.Lmark};
        int maxMark = marks[0];

        for (int i = 0; i < 3; i++) {
            if (marks[i + 1] > maxMark) {
                maxMark = marks[i + 1];
            }
        }

        if (maxMark != -1000) {
            if (maxMark == AI.Umark) {
                return "UP#";
            } else if (maxMark == AI.Rmark) {
                return "RIGHT#";
            } else if (maxMark == AI.Dmark) {
                return "DOWN#";
            } else {
                return "LEFT#";
            }
        } else {
            System.out.println("Cant move");
            return null;
        }
    }

    private Actor findBestdestination(ArrayList<LifePack> lifePacks, ArrayList<CoinPack> coinPacks, Player tank) {

        Actor destination = new Actor();
        destination = new Empty(0, 0);
        try {
            int min = 100000000;
            System.out.println("coinPacks.size()  " + coinPacks.size() + "lifePAcks.size()  " + lifePacks.size());
            if (coinPacks.size() != 0) {
                for (CoinPack cp : coinPacks) {
                    int temp = Math.abs(cp.getX() - tank.getX()) + Math.abs(cp.getY() - tank.getY());
                    if (temp < min) {
                        min = temp;
                        destination = cp;
                    }
                }
            }
            if (tank.getHealth() < 100 && lifePacks.size() != 0) {
                for (LifePack lp : lifePacks) {
                    int temp = Math.abs(lp.getX() - tank.getX()) + Math.abs(lp.getY() - tank.getY());
                    if (temp < min) {
                        min = temp;
                        destination = lp;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("destination.getType()  " + destination.getType());
        return destination;
    }

    public void CreateValueMap(Actor destination, Player player) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                valueMap[i][j] = 10;
            }
        }

        //if (destination.getType() != "O") {
        //System.out.println("destination.getX() " + destination.getX() + " destination.getY() " + destination.getY());
        //int pointrxX = -destination.getX();//-5
        //int pointrxY = -destination.getY();//-5
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                valueMap[i][j] = valueMap[i][j] + 100 - Math.abs(4 + j) - Math.abs(4 + i);
                //valueMap[i][j] = valueMap[i][j] + 100 - Math.abs(pointrxX + j) - Math.abs(pointrxY + i);
            }
        }
        //    }
        //} else {
        //    System.out.println("invalid destination");
        //}
        valueMap[preTankY][pretankX] = valueMap[preTankY][pretankX] - 5;
        pretankX = player.getX();
        preTankY = player.getY();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(valueMap[i][j] + "  ");
            }
            System.out.println("");
        }

    }

    public boolean checkForShoot(Actor[][] map, Player tank) {
        int x = tank.getX();
        int y = tank.getY();
        if (tank.getDirection() == 0) {

            for (int i = y - 1; i >= 0; i--) {
                if (map[i][x].getType() == "B" || map[i][x].getType() == "T") {
                    return true;
                } else if (map[i][x].getType() == "S") {
                    return false;
                }
            }

        } else if (tank.getDirection() == 1) {
            for (int i = x + 1; i <= 9; i++) {
                if (map[y][i].getType() == "B" || map[y][i].getType() == "T") {
                    return true;
                } else if (map[y][i].getType() == "S") {
                    return false;
                }
            }

        } else if (tank.getDirection() == 2) {

            for (int i = y + 1; i <= 9; i++) {
                if (map[i][x].getType() == "B" || map[i][x].getType() == "T") {
                    return true;
                } else if (map[i][x].getType() == "S") {
                    return false;
                }
            }
        } else {
            for (int i = x - 1; i >= 0; i--) {
                if (map[y][i].getType() == "B" || map[y][i].getType() == "T") {
                    return true;
                } else if (map[y][i].getType() == "S") {
                    return false;
                }
            }
        }
        return false;
    }

}
