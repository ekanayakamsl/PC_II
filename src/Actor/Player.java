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
public class Player extends Actor {

    private String name;
    private int direction;
    private int whetherShot;
    private int coins;
    private int points;
    private int health;
    private int id;
    private boolean isDeth = false;

    public Player() {
    }

    public Player(String name, int direction, int whetherShot, int coins, int points, int health, int x, int y) {
        super(x, y, "T");
        this.name = name;
        this.direction = direction;
        this.whetherShot = whetherShot;
        this.coins = coins;
        this.points = points;
        this.health = health;
    }

    public boolean isIsDeth() {
        return isDeth;
    }

    public void setIsDeth(boolean isDeth) {
        this.isDeth = isDeth;
    }

    
    
    public String getName() {
        return name;
    }

    public int getDirection() {
        return direction;
    }

    public int getWhetherShot() {
        return whetherShot;
    }

    public int getCoins() {
        return coins;
    }

    public int getPoints() {
        return points;
    }

    public int getHealth() {
        return health;
    }

    public int getId() {
        return id;
    }
   
    public void setName(String name) {
        this.name = name;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWhetherShot(int whetherShot) {
        this.whetherShot = whetherShot;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
