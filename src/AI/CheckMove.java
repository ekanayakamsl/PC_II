/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Actor.Actor;
import Actor.CoinAndLifePack;
import Actor.CoinPack;
import Actor.LifePack;
import Actor.Player;
import java.util.ArrayList;

/**
 *
 * @author sranga
 */
public class CheckMove {

    static int[][] valueMap;
    int count;

    public MoveWeight checkObstaclesAndBoundary(Player tank, Actor[][] map, MoveWeight moveWeight) {

        int curentX = tank.getX();
        int curentY = tank.getY();
        int minInf = Integer.MIN_VALUE; //-2147483648

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

    /////find best destination
    public CoinAndLifePack findBestdestination(Player player, MoveWeight moveWeigh, Actor[][] map) {
        CoinAndLifePack destination = new CoinAndLifePack();
        destination.setWeight(0);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j].getType() == "C" || map[i][j].getType() == "L") {
                    CoinAndLifePack coinAndLifePack = (CoinAndLifePack) map[i][j];

                    if (coinAndLifePack.isAlive() && map[coinAndLifePack.getY()][coinAndLifePack.getX()].getType() == "C") {
                        int xDistance = Math.abs(player.getX() - coinAndLifePack.getX());
                        int yDistance = Math.abs(player.getY() - coinAndLifePack.getY());
                        int distance = xDistance + yDistance;
                        if (xDistance != 0 && yDistance != 0) {
                            distance++;
                        }
                        if (distance * 1000 < coinAndLifePack.getRemainTime()) {
                            int w = 0;
                            if (distance == 1) {
                                w = 90000000;
                            } else if (distance == 2) {
                                w = 10000000;
                            } else if (distance == 3) {
                                w = 7000000;
                            }
                            if (coinAndLifePack.getType() == "C") {
                                CoinPack cp = (CoinPack) coinAndLifePack;
                                w = w + (10 - distance) * 1000 + cp.getAmount();
                                coinAndLifePack.setWeight(w);

                            } else {
                                LifePack lp = (LifePack) coinAndLifePack;
                                if (player.getHealth() <= 40) {
                                    w = w + (15 - distance) * 1000 + (50 - player.getHealth()) * 100;
                                    coinAndLifePack.setWeight(w);
                                } else {
                                    w = w + (15 - distance) * 1000;
                                    coinAndLifePack.setWeight(w);
                                }
                            }
                            if (destination.getWeight() < coinAndLifePack.getWeight()) {
                                destination = coinAndLifePack;
                            }
                        } else {
                            coinAndLifePack.setWeight(0);
                        }
                    } else {
                        coinAndLifePack.setWeight(0);
                    }

                }
            }
        }
        return destination;

    }

    ///check for shoot
    public MoveWeight checkForTankShoot(Actor[][] map, Player player, MoveWeight moveWeight) {
//-2147483648       
        int x = player.getX();
        int y = player.getY();
        int d = player.getDirection();

        int tankShootWeight = 900000000;
        int tankDirWeight = 100000000;

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
            }
        }
        return moveWeight;
    }

    MoveWeight checkForBrickShoot(Actor[][] map, Player player, MoveWeight moveWeight) {
        int x = player.getX();
        int y = player.getY();
        int d = player.getDirection();

        int shootNearBrick = 900000000;
        int brickShootWeight = 9000;
        int brickDirWeight = 1000;

        if (y - 1 >= 0 && map[y - 1][x].getType() == "B" && d == 0) {
            moveWeight.setShoot(moveWeight.getShoot() + shootNearBrick);
        } else if (x + 1 <= 9 && map[y][x + 1].getType() == "B" && d == 1) {
            moveWeight.setShoot(moveWeight.getShoot() + shootNearBrick);
        } else if (y + 1 <= 9 && map[y + 1][x].getType() == "B" && d == 2) {
            moveWeight.setShoot(moveWeight.getShoot() + shootNearBrick);
        } else if (x - 1 >= 0 && map[y][x - 1].getType() == "B" && d == 3) {
            moveWeight.setShoot(moveWeight.getShoot() + shootNearBrick);
        } else {
            //check up
            for (int i = y - 2; i >= 0; i--) {
                if (map[i][x].getType() == "S") {
                    break;
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
            for (int i = x + 2; i <= 9; i++) {
                if (map[y][i].getType() == "S") {
                    break;
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
            for (int i = y + 2; i <= 9; i++) {
                if (map[i][x].getType() == "S") {
                    break;
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
            for (int i = x - 2; i >= 0; i--) {
                if (map[y][i].getType() == "S") {
                    break;
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
        }
        return moveWeight;
    }

    ////////////////////////
//shorterst path finding
    int[][] shorterstPath(Player player, CoinAndLifePack destination, Actor[][] map) {
        valueMap = new int[10][10];

        int[][] vaIses = chreateMap(map);
        move(player.getX(), player.getY(), destination.getX(), destination.getY(), 1, vaIses);
        int c1 = this.count;

        int[][] vaIses1 = chreateMapWithoutBrick(map);
        move(player.getX(), player.getY(), destination.getX(), destination.getY(), 1, vaIses1);
        int c2 = this.count;
        if (c2 + 4 < c1) {
            findPath(destination.getX(), destination.getY(), vaIses1);
        } else {
            findPath(destination.getX(), destination.getY(), vaIses);
        }
        return valueMap;
    }

    void move(int x, int y, int x1, int y1, int c, int[][] map) {

        try {
            if (map[y][x] == -1) {
                //    printMap(map);
                return;
            }
            if (map[y][x] < c && map[y][x] != 0) {
                return;
            }
        } catch (Exception e) {
            //    printMap(map);
            return;
        }
        if (c > 15) {
            //   printMap(map);
            return;
        } else if (x == x1 && y == y1) {
            map[y][x] = c;
            if (this.count > c) {
                this.count = c;
            }
            return;
        } else {

            map[y][x] = c;
//            printMap(map);
            move(x, y - 1, x1, y1, c + 1, map);
            move(x + 1, y, x1, y1, c + 1, map);
            move(x, y + 1, x1, y1, c + 1, map);
            move(x - 1, y, x1, y1, c + 1, map);
        }
    }

    private int[][] chreateMap(Actor[][] map) {
        int[][] valMap = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j].getType() == "B" || map[i][j].getType() == "S" || map[i][j].getType() == "W") {
                    valMap[i][j] = -1;
                } else {
                    valMap[i][j] = 0;
                }
            }
        }
        return valMap;
    }

    private int[][] chreateMapWithoutBrick(Actor[][] map) {
        int[][] valMap = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j].getType() == "S" || map[i][j].getType() == "W") {
                    valMap[i][j] = -1;
                } else {
                    valMap[i][j] = 0;
                }
            }
        }
        return valMap;
    }

    private int[][] findPath(int x, int y, int[][] map) {
        valueMap[y][x] = 100 * map[y][x];
        if (y - 1 >= 0 && map[y][x] == map[y - 1][x] + 1) {
            findPath(x, y - 1, map);
        } else if (x + 1 <= 9 && map[y][x] == map[y][x + 1] + 1) {
            findPath(x + 1, y, map);
        } else if (y + 1 <= 9 && map[y][x] == map[y + 1][x] + 1) {
            findPath(x, y + 1, map);
        } else if (x - 1 >= 0 && map[y][x] == map[y][x - 1] + 1) {
            findPath(x - 1, y, map);
        }
        return map;
    }

    MoveWeight chechNextmoveForDes(Player player, int[][] valueMap, MoveWeight moveWeight) {
        int weight = 100;
        int x = player.getX();
        int y = player.getY();
        if (y - 1 >= 0) {
            moveWeight.setUp(moveWeight.getUp() + valueMap[y - 1][x] * weight);
        }
        if (x + 1 <= 9) {
            moveWeight.setRight(moveWeight.getRight() + valueMap[y][x + 1] * weight);
        }
        if (y + 1 <= 9) {
            moveWeight.setDown(moveWeight.getDown() + valueMap[y + 1][x] * weight);
        }
        if (x - 1 >= 0) {
            moveWeight.setLeft(moveWeight.getLeft() + valueMap[y][x - 1] * weight);
        }
        return moveWeight;
    }

//    ////////////////
    //save from shootted
    MoveWeight checkFofSave(Player player, Actor[][] map, MoveWeight moveWeight) {
        int x = player.getX();
        int y = player.getY();
        int d = player.getDirection();

        int saveWeight = 9000000;

        //check up
        for (int i = y - 1; i >= 0; i--) {
            if (map[i][x].getType() == "S") {
                break;
            }
            if (map[i][x].getType() == "T") {
                moveWeight.setLeft(moveWeight.getLeft() + saveWeight);
                moveWeight.setRight(moveWeight.getRight() + saveWeight);
                break;
            }
        }
        //check right
        for (int i = x + 1; i <= 9; i++) {
            if (map[y][i].getType() == "S") {
                break;
            }
            if (map[y][i].getType() == "T") {
                moveWeight.setUp(moveWeight.getUp() + saveWeight);
                moveWeight.setDown(moveWeight.getDown() + saveWeight);
                break;
            }
        }
        //check down
        for (int i = y + 1; i <= 9; i++) {
            if (map[i][x].getType() == "S") {
                break;
            }
            if (map[i][x].getType() == "T") {
                moveWeight.setLeft(moveWeight.getLeft() + saveWeight);
                moveWeight.setRight(moveWeight.getRight() + saveWeight);
                break;
            }
        }
        //check left
        for (int i = x - 1; i >= 0; i--) {
            if (map[y][i].getType() == "S") {
                break;
            }
            if (map[y][i].getType() == "T") {
                moveWeight.setUp(moveWeight.getUp() + saveWeight);
                moveWeight.setDown(moveWeight.getDown() + saveWeight);
                break;
            }
        }
        return moveWeight;
    }

    MoveWeight checkTankForShoot(Player player, Actor[][] map, MoveWeight moveWeight) {
        //check up
        int x = player.getX();
        int y = player.getY();
        int fightWeight = 9000000;

        //up right
        for (int i = x + 1; i <= 9; i++) {
            if (y - 1 >= 0 && map[y - 1][i].getType() == "S") {
                break;
            }
            if (y - 1 >= 0 && map[y - 1][i].getType() == "T") {
                moveWeight.setUp(moveWeight.getUp() + fightWeight);
                break;
            }
        }
        //up left
        for (int i = x - 1; i >= 0; i--) {
            if (y - 1 >= 0 && map[y - 1][i].getType() == "S") {
                break;
            }
            if (y - 1 >= 0 && map[y - 1][i].getType() == "T") {
                moveWeight.setUp(moveWeight.getUp() + fightWeight);
                break;
            }
        }

        //down right
        for (int i = x + 1; i <= 9; i++) {
            if (y + 1 <= 9 && map[y + 1][i].getType() == "S") {
                break;
            }
            if (y + 1 <= 9 && map[y + 1][i].getType() == "T") {
                moveWeight.setDown(moveWeight.getDown() + fightWeight);
                break;
            }
        }
        //down left
        for (int i = x - 1; i >= 0; i--) {
            if (y + 1 <= 9 && map[y + 1][i].getType() == "S") {
                break;
            }
            if (y + 1 <= 9 && map[y + 1][i].getType() == "T") {
                moveWeight.setDown(moveWeight.getDown() + fightWeight);
                break;
            }
        }

        //left up
        for (int i = y - 1; i >= 0; i--) {
            if (x - 1 >= 0 && map[i][x - 1].getType() == "S") {
                break;
            }
            if (x - 1 >= 0 && map[i][x - 1].getType() == "T") {
                moveWeight.setLeft(moveWeight.getLeft() + fightWeight);
                break;
            }
        }
        //left down
        for (int i = y + 1; i <= 9; i++) {
            if (x - 1 >= 0 && map[i][x - 1].getType() == "S") {
                break;
            }
            if (x - 1 >= 0 && map[i][x - 1].getType() == "T") {
                moveWeight.setLeft(moveWeight.getLeft() + fightWeight);
                break;
            }
        }

        //right up
        for (int i = y - 1; i >= 0; i--) {
            if (x + 1 <= 9 && map[i][x + 1].getType() == "S") {
                break;
            }
            if (x + 1 <= 9 && map[i][x + 1].getType() == "T") {
                moveWeight.setRight(moveWeight.getRight() + fightWeight);
                break;
            }
        }
        //right down
        for (int i = y + 1; i <= 9; i++) {
            if (x + 1 <= 9 && map[i][x + 1].getType() == "S") {
                break;
            }
            if (x + 1 <= 9 && map[i][x + 1].getType() == "T") {
                moveWeight.setRight(moveWeight.getRight() + fightWeight);
                break;
            }
        }

        return moveWeight;
    }

}
