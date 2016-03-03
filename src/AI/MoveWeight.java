/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AI;

/**
 *
 * @author sranga
 */
public class MoveWeight {
    private int left = 1000;
    private int right= 1000;
    private int up= 1000;
    private int down= 1000;
    private int shoot= 900;

    public MoveWeight() {
    }
    
    public MoveWeight(int up, int right, int down, int left, int shoot) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
        this.shoot = shoot;
    }
    
    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public int getShoot() {
        return shoot;
    }

    public void setLeft(int lift) {
        this.left = lift;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public void setDown(int doun) {
        this.down = doun;
    }

    public void setShoot(int shoot) {
        this.shoot = shoot;
    }
    
    
}
