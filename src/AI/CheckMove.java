/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Actor.Actor;
import Actor.CoinAndLifePack;
import Actor.Empty;
import Actor.Player;
import com.sun.jndi.ldap.Ber;
import java.util.ArrayList;

/**
 *
 * @author sranga
 */
public class CheckMove {

//    int[][] valueMap = new int[10][10];
    static int pretankX;
    static int preTankY;

    public MoveWeight checkObstaclesAndBoundary(Player tank, Actor[][] map, MoveWeight moveWeight, int[][] valueMap) {

        int curentX = tank.getX();
        int curentY = tank.getY();
        int minInf = Integer.MIN_VALUE;

//        Actor destinationActor = findBestdestination(lifePacksAndCoinPacks, tank);
        //CreateValueMap(destinationActor, tank);
        try {
            if (map[curentY - 1][curentX].getType() == "W" || map[curentY - 1][curentX].getType() == "S" || map[curentY - 1][curentX].getType() == "T") {
                moveWeight.setUp(moveWeight.getUp() - 100000);
            } else if (tank.getDirection() == 0 && map[curentY - 1][curentX].getType() == "B") {
                moveWeight.setUp(moveWeight.getUp() - 100000);
            } else {
                moveWeight.setUp(moveWeight.getUp() + valueMap[curentY - 1][curentX]);
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            moveWeight.setUp(moveWeight.getUp() - 100000);

        }
        //check for right
        try {
            if (map[curentY][curentX + 1].getType() == "W" || map[curentY][curentX + 1].getType() == "S" || map[curentY][curentX + 1].getType() == "T") {
                moveWeight.setRight(moveWeight.getRight() - 100000);
            } else if (tank.getDirection() == 1 && map[curentY][curentX + 1].getType() == "B") {
                moveWeight.setRight(moveWeight.getRight() - 100000);

            } else {
                moveWeight.setRight(moveWeight.getRight() + valueMap[curentY][curentX + 1]);
            }

        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            moveWeight.setRight(moveWeight.getRight() - 100000);

        }
        //check for down

        try {
            if (map[curentY + 1][curentX].getType() == "W" || map[curentY + 1][curentX].getType() == "S" || map[curentY + 1][curentX].getType() == "T") {
                moveWeight.setDown(moveWeight.getDown() - 100000);

            } else if (tank.getDirection() == 2 && map[curentY + 1][curentX].getType() == "B") {
                moveWeight.setDown(moveWeight.getDown() - 100000);

            } else {
                moveWeight.setDown(moveWeight.getDown() + valueMap[curentY + 1][curentX]);
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            moveWeight.setDown(moveWeight.getDown() - 100000);

        }

        //check for left
        try {
            if (map[curentY][curentX - 1].getType() == "W" || map[curentY][curentX - 1].getType() == "S" || map[curentY][curentX - 1].getType() == "B" || map[curentY][curentX - 1].getType() == "T") {
                moveWeight.setLeft(moveWeight.getLeft() - 100000);

            } else if (tank.getDirection() == 3 && map[curentY][curentX - 1].getType() == "B") {
                moveWeight.setLeft(moveWeight.getLeft() - 100000);

            } else {
                moveWeight.setLeft(moveWeight.getLeft() + valueMap[curentY][curentX - 1]);
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            moveWeight.setLeft(moveWeight.getLeft() - 100000);
        }
        return moveWeight;
    }

    public MoveWeight checkObstaclesAndBoundary(Player tank, Actor[][] map, MoveWeight moveWeight) {

        int curentX = tank.getX();
        int curentY = tank.getY();
        int minInf = Integer.MIN_VALUE; //-2147483648

//        Actor destinationActor = findBestdestination(lifePacksAndCoinPacks, tank);
        //CreateValueMap(destinationActor, tank);
        try {
            if (map[curentY - 1][curentX].getType() == "W" || map[curentY - 1][curentX].getType() == "S" || map[curentY - 1][curentX].getType() == "T") {
                moveWeight.setUp(minInf);
            } else if (tank.getDirection() == 0 && map[curentY - 1][curentX].getType() == "B") {
                moveWeight.setUp(minInf);
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            moveWeight.setUp(minInf);

        }
        //check for right
        try {
            if (map[curentY][curentX + 1].getType() == "W" || map[curentY][curentX + 1].getType() == "S" || map[curentY][curentX + 1].getType() == "T") {
                moveWeight.setRight(minInf);
            } else if (tank.getDirection() == 1 && map[curentY][curentX + 1].getType() == "B") {
                moveWeight.setRight(minInf);
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            moveWeight.setRight(minInf);

        }
        //check for down

        try {
            if (map[curentY + 1][curentX].getType() == "W" || map[curentY + 1][curentX].getType() == "S" || map[curentY + 1][curentX].getType() == "T") {
                moveWeight.setDown(minInf);

            } else if (tank.getDirection() == 2 && map[curentY + 1][curentX].getType() == "B") {
                moveWeight.setDown(minInf);
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            moveWeight.setDown(minInf);

        }

        //check for left
        try {
            if (map[curentY][curentX - 1].getType() == "W" || map[curentY][curentX - 1].getType() == "S" || map[curentY][curentX - 1].getType() == "B" || map[curentY][curentX - 1].getType() == "T") {
                moveWeight.setLeft(minInf);

            } else if (tank.getDirection() == 3 && map[curentY][curentX - 1].getType() == "B") {
                moveWeight.setLeft(minInf);

            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            moveWeight.setLeft(minInf);
        }
        return moveWeight;
    }

    public ArrayList<CoinAndLifePack> findReachableDes(ArrayList<CoinAndLifePack> coinAndLifePacks, Player player) {

        for (CoinAndLifePack coinAndLifePack : coinAndLifePacks) {
            if (coinAndLifePack.isAlive()) {
                int distance = Math.abs(player.getX() - coinAndLifePack.getX()) + Math.abs(player.getY() - coinAndLifePack.getY());
                coinAndLifePack.setAlive(distance * 1000 < coinAndLifePack.getRemainTime());
            }
        }

        return coinAndLifePacks;
    }

    public int findBestMove(MoveWeight moveWeight) {

        int marks[] = {moveWeight.getUp(), moveWeight.getRight(), moveWeight.getDown(), moveWeight.getLeft()};
        int maxMark = marks[0];

        for (int i = 0; i < 3; i++) {
            if (marks[i + 1] > maxMark) {
                maxMark = marks[i + 1];
            }
        }

        if (maxMark > -10000) {
            if (maxMark == moveWeight.getUp()) {
                return 0;
            } else if (maxMark == moveWeight.getRight()) {
                return 1;
            } else if (maxMark == moveWeight.getDown()) {
                return 2;
            } else {
                return 3;
            }
        } else {
            System.out.println("Cant move");
            return 10;
        }
    }

    private Actor findBestdestination(Player player, Actor[][] map, ArrayList<Actor> lifePacksAndCoinPacks, MoveWeight moveWeigh) {

        Actor destination = new Actor();
        destination = lifePacksAndCoinPacks.get(0);
        return destination;
    }

    public Actor findMoveCount(Player player, Actor destination, Actor[][] map) {

        int startX = player.getX();
        int startY = player.getY();
        int endX = destination.getX();
        int endY = destination.getY();
        Actor[][] tanPathMap = map;

        int[][] valueMap = shorterstPath(endX, endY);
        System.out.println("destination = ==" + endX + "," + endY);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(valueMap[i][j] + "  ");
            }
            System.out.println();
        }

//        tanPathMap[startX][startY] = player;
        Player tempPlayer = player;

        int moveCount = 0;
        int move = 10;
        int nextMove = 10;
        int steps = 0;
        while ((tempPlayer.getX() != endX || tempPlayer.getY() != endY) && steps < 50) {
            if ((tempPlayer.getX() == startX) && (tempPlayer.getY() == startY)) {
                destination.setTankDirection(nextMove);
            }
            MoveWeight moveWeight = new MoveWeight(1000, 1000, 1000, 1000, 1000);
            System.out.println("tep p " + tempPlayer.getX() + "," + tempPlayer.getY() + "," + tempPlayer.getDirection());

            moveWeight = checkObstaclesAndBoundary(tempPlayer, map, moveWeight, valueMap);
            move = findBestMove(moveWeight);
            System.out.println(steps + " th move =" + move);
            if (move == 10) {
                System.out.println("breaked at " + steps);
                break;
            }
            if ((Math.abs(nextMove - move) == 2)) {
                moveCount--;
            }
            if (move == nextMove) {
                valueMap = shorterstPath(endX, endY);
                valueMap[tempPlayer.getY()][tempPlayer.getX()] = valueMap[tempPlayer.getY()][tempPlayer.getX()] - 500;
                if (move == 0) {
                    tempPlayer.setY(tempPlayer.getY() - 1);
                } else if (move == 1) {
                    tempPlayer.setX(tempPlayer.getX() + 1);
                    System.out.println(tempPlayer.getX() + "," + tempPlayer.getY());
                } else if (move == 2) {
                    tempPlayer.setY(tempPlayer.getY() + 1);
                } else if (move == 3) {
                    tempPlayer.setX(tempPlayer.getX() - 1);
                }
                if (map[tempPlayer.getX()][tempPlayer.getY()].getType() == "T") {
                    moveCount = moveCount - 2;
                }

            }
            tempPlayer.setDirection(move);
            map[tempPlayer.getY()][tempPlayer.getX()] = player;
            moveCount++;
            steps++;
            nextMove = move;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(map[i][j].getType() + "  ");
                }
                System.out.println();
            }
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(valueMap[i][j] + "  ");
                }
                System.out.println();
            }
        }
        destination.setMoveCount(moveCount);
        return destination;
    }

    public int[][] shorterstPath(int endX, int endY) {
        int[][] valueMap = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                valueMap[i][j] = (20 - Math.abs(-endX + j) - Math.abs(-endY + i)) * 10;
            }
        }
        return valueMap;
    }

    /*
     private Actor[][] setTankOnMap(Player player, int nextMove, Actor[][] map) {
     int x = player.getX();
     int y = player.getY();
     if (nextMove == 0) {
     map[x][y - 1] = player;
     }
     if (nextMove == 1) {
     map[x + 1][y] = player;
     }
     if (nextMove == 2) {
     map[x][y + 1] = player;
     }
     if (nextMove == 3) {
     map[x - 1][y] = player;
     }
     return map;
     }
    
     */
    /*
     public int[][] shorterstPath(Actor destination, Player player) {

     int[][] valueMap = new int[10][10];
     for (int i = 0; i < 10; i++) {
     for (int j = 0; j < 10; j++) {
     valueMap[i][j] = 0;
     }
     }
     if (destination.getType() != "O") {
     int pointrxX = -destination.getX();//-5
     int pointrxY = -destination.getY();//-5
     for (int i = 0; i < 10; i++) {
     for (int j = 0; j < 10; j++) {
     valueMap[i][j] = (valueMap[i][j] - Math.abs(pointrxX + j) - Math.abs(pointrxY + i)) * 10;
     }
     }
     } else {
     System.out.println("invalid destination");
     //}
     }

     /*valueMap[preTankY][pretankX] = valueMap[preTankY][pretankX] - 3;
     pretankX = player.getX();
     preTankY = player.getY();
     for (int i = 0; i < 10; i++) {
     for (int j = 0; j < 10; j++) {
     System.out.print(valueMap[i][j] + "  ");
     }
     System.out.println("");
     }
         
     return valueMap;

     }
     
     public boolean checkForShoot(Actor[][] map, Player tank) {
     int x = tank.getX();
     int y = tank.getY();
     if (tank.getDirection() == 0) {
     if (y - 1 >= 0 && map[y - 1][x].getType() == "B") {
     return true;
     } else {
     for (int i = y - 1; i >= 0; i--) {
     if (map[i][x].getType() == "T") {
     return true;
     } else if (map[i][x].getType() == "S") {
     return false;
     }
     }
     }

     } else if (tank.getDirection() == 1) {
     if (x + 1 <= 9 && map[y][x + 1].getType() == "B") {
     return true;
     } else {
     for (int i = x + 1; i <= 9; i++) {
     if (map[y][i].getType() == "T") {
     return true;
     } else if (map[y][i].getType() == "S") {
     return false;
     }
     }
     }

     } else if (tank.getDirection() == 2) {

     if (x + 1 <= 9 && map[y + 1][x].getType() == "B") {
     return false;
     } else {

     for (int i = y + 1; i <= 9; i++) {
     if (map[i][x].getType() == "T") {
     return true;
     } else if (map[i][x].getType() == "S") {
     return false;
     }
     }
     }
     } else {
     if (x - 1 >= 0 && map[y][x - 1].getType() == "B") {

     } else {
     for (int i = x - 1; i >= 0; i--) {
     if (map[y][i].getType() == "T") {
     return true;
     } else if (map[y][i].getType() == "S") {
     return false;
     }
     }
     }
     }
     return false;
     }
     */
    public MoveWeight checkForShoot(Actor[][] map, Player player, MoveWeight moveWeight) {

        int x = player.getX();
        int y = player.getY();
        int d = player.getDirection();

        int tankShootWeight = 50000000;
        int tankDirWeight = 10000000;
        int brickShootWeight = 500000;
        int brickDirWeight = 100000;
        
        //check up
        for (int i = y - 1; i >= 0; i--) {
            if (map[i][x].getType() == "S") {
                break;
            } else if (map[i][x].getType() == "T") {
                if (d == 0) {
                    moveWeight.setShoot(moveWeight.getShoot() + tankShootWeight);
                    break;
                } else {
                    moveWeight.setUp(moveWeight.getUp() + tankDirWeight);
                    break;
                }
            } else if (map[i][x].getType() == "B") {
                if (d == 0) {
                    moveWeight.setShoot(moveWeight.getShoot() + brickShootWeight);
                    break;
                } else {
                    moveWeight.setUp(moveWeight.getUp() + brickDirWeight);
                    break;
                }
            }
        }
        //check right
        for (int i = x + 1; i <= 9; i++) {
            if (map[y][i].getType() == "S") {
                break;
            } else if (map[y][i].getType() == "T") {
                if (d == 1) {
                    moveWeight.setShoot(moveWeight.getShoot() + tankShootWeight);
                    break;
                } else {
                    moveWeight.setRight(moveWeight.getRight() + tankDirWeight);
                    break;
                }
            } else if (map[y][i].getType() == "B") {
                if (d == 1) {
                    moveWeight.setShoot(moveWeight.getShoot() + brickShootWeight);
                    break;
                } else {
                    moveWeight.setRight(moveWeight.getRight() + brickDirWeight);
                    break;
                }
            }
        }
        //check down
        for (int i = y + 1; i <= 9; i++) {
            if (map[i][x].getType() == "S") {
                break;
            } else if (map[i][x].getType() == "T") {
                if (d == 2) {
                    moveWeight.setShoot(moveWeight.getShoot() + tankShootWeight);
                    break;
                } else {
                    moveWeight.setDown(moveWeight.getDown() + tankDirWeight);
                    break;
                }
            } else if (map[i][x].getType() == "B") {
                if (d == 2) {
                    moveWeight.setShoot(moveWeight.getShoot() + brickShootWeight);
                    break;
                } else {
                    moveWeight.setDown(moveWeight.getDown() + brickDirWeight);
                    break;
                }
            }
        }
        //check left
        for (int i = x - 1; i >= 0; i--) {
            if (map[y][i].getType() == "S") {
                break;
            } else if (map[y][i].getType() == "T") {
                if (d == 3) {
                    moveWeight.setShoot(moveWeight.getShoot() + tankShootWeight);
                    break;
                } else {
                    moveWeight.setLeft(moveWeight.getLeft() + tankDirWeight);
                    break;
                }
            } else if (map[y][i].getType() == "B") {
                if (d == 3) {
                    moveWeight.setShoot(moveWeight.getShoot() + brickShootWeight);
                    break;
                } else {
                    moveWeight.setLeft(moveWeight.getLeft() + brickDirWeight);
                    break;
                }
            }
        }
        return moveWeight;
    }

}
