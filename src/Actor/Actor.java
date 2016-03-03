/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actor;

/**
 *
 * @author sranga
 */
public class Actor {

    private int x;
    private int y;
    private String type;
    private int tankDirection;
    private int moveCount;

    public Actor() {
    }

    public Actor(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

    public int getTankDirection() {
        return tankDirection;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTankDirection(int tankDirection) {
        this.tankDirection = tankDirection;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

}
