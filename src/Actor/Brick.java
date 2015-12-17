/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actor;

/**
 *
 * @author Buddhi
 */
public class Brick extends Actor {

    private double health;

    public Brick() {
    }

    public Brick(double health, int x, int y) {
        super(x, y, "B");
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    
}
