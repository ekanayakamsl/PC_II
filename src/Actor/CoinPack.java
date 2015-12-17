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
public class CoinPack extends Actor{

    private int amount;
    private int duration;

    public CoinPack() {
    }

    public CoinPack(int amount, int duration, int x, int y) {
        super(x, y, "C");
        this.amount = amount;
        this.duration = duration;
    }

    public int getAmount() {
        return amount;
    }

    public int getDuration() {
        return duration;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    
    
}
