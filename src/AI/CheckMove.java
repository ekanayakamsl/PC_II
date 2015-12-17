/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Actor.Player;

/**
 *
 * @author sranga
 */
public class CheckMove {

    public void checkObstaclesAndBoundary(Player tank, Actor.Actor[][] map) {

        int curentX = tank.getX();
        int curentY = tank.getY();
        //check for up
        try {
            if (map[curentY - 1][curentX].getType() == "W" || map[curentY - 1][curentX].getType() == "S" || map[curentY - 1][curentX].getType() == "B" || map[curentY - 1][curentX].getType() == "T") {
                AI.Umark = -1000;
            } else if (map[curentY - 1][curentX].getType() == "O") {
                AI.Umark += AiMap.valueMap[curentY - 1][curentX];
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            AI.Umark = -1000;
        }
        //check for right
        try {
            if (map[curentY][curentX + 1].getType() == "W" || map[curentY][curentX + 1].getType() == "S" || map[curentY][curentX + 1].getType() == "B" || map[curentY][curentX + 1].getType() == "T") {
                AI.Rmark = -1000;
            } else if (map[curentY][curentX + 1].getType() == "O") {
                AI.Rmark += AiMap.valueMap[curentY][curentX + 1];
            }

        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            AI.Rmark = -1000;
        }
        //check for down

        try {
            if (map[curentY + 1][curentX].getType() == "W" || map[curentY + 1][curentX].getType() == "S" || map[curentY + 1][curentX].getType() == "B" || map[curentY + 1][curentX].getType() == "T") {
                AI.Dmark = -1000;
            } else if (map[curentY + 1][curentX].getType() == "O") {
                AI.Dmark += AiMap.valueMap[curentY + 1][curentX];
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            AI.Dmark = -1000;
        }

        //check for left
        try {
            if (map[curentY][curentX - 1].getType() == "W" || map[curentY][curentX - 1].getType() == "S" || map[curentY][curentX - 1].getType() == "B" || map[curentY][curentX - 1].getType() == "T") {
                AI.Lmark = -1000;
            } else if (map[curentY][curentX - 1].getType() == "O") {
                AI.Lmark += AiMap.valueMap[curentY][curentX - 1];
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            AI.Lmark = -1000;
        }
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

}
