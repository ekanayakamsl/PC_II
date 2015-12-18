/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pii;

import AI.CheckMove;
import Actor.CoinPack;
import Actor.Player;
import messegeControlle.MapControl;

/**
 *
 * @author sranga
 */
public class PII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    
        CheckMove checkMove = new CheckMove();
        CoinPack coinPack = new CoinPack(100,10,2,3);
        Player player = new Player("p",1,100,3,5,10,5,5);
        checkMove.CreateValueMap(coinPack, player);

        //    }
    }

}
