/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.util.ArrayList;
import Actor.CoinPack;

/**
 *
 * @author sranga
 */
public class AiMap {

    public static int[][] valueMap = new int[10][10];
    public ArrayList<CoinPack> coinList = new ArrayList<CoinPack>();
    public ArrayList<int[]> tankPosition = new ArrayList<int[]>();

    public AiMap() {
    }

    public void addCoin(CoinPack c) {

        int pointrxX = -c.getX();//-5
        int pointrxY = -c.getY();//-5
        coinList.add(c);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                valueMap[i][j] = valueMap[i][j] + c.getAmount() * 2 - Math.abs(pointrxX + j) - Math.abs(pointrxY + i);
            }
        }
    }
    /*
     public void removeCoin(int cX, int cY,int coinVal) {
     int pointrxX = -cX;//-5
     int pointrxY = -cY;//-5
     for (int[] pos : coinList) {
     if (pos[0] == cX && pos[1] == cY) {
     coinList.remove(pos);
     }
     }
     for (int i = 0; i < 10; i++) {
     for (int j = 0; j < 10; j++) {
     valueMap[i][j] = valueMap[i][j] - (coinVal * 2 - Math.abs(pointrxX + j) - Math.abs(pointrxY + i));
     }
     }
     }

     public void collectCoin() {
     for (int[] t : tankPosition) {
     for (int[] c : coinList) {
     if (t[0] == c[0] && t[1] == c[1]) {
     int x = 1/0;
     removeCoin(c[0], c[1],c[0]);
                    
     }
     }
     }
     }
     */

}
