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
public class LifePack extends Actor{
    private int duration;

    public LifePack() {
    }

    
    
    public LifePack(int duration, int x, int y) {
        super(x, y, "L");
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
