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
public class CoinAndLifePack extends Actor {

    private boolean alive;
    private int remainTime;
    private int weight = 0;

    public CoinAndLifePack() {
    }

    public CoinAndLifePack(int x, int y, String type) {
        super(x, y, type);
    }

    public boolean isAlive() {
        return alive;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setAlive(boolean inAlive) {
        this.alive = inAlive;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
