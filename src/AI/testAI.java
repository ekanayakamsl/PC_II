/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Actor.Actor;
import Actor.Brick;
import Actor.CoinPack;
import Actor.Empty;
import Actor.LifePack;
import Actor.Player;
import Actor.Stone;
import Actor.Water;
import java.util.ArrayList;
import javafx.scene.control.Tab;

/**
 *
 * @author sranga
 */
public class testAI {

    public static void main(String[] args) {
       
        
        testAI taI = new testAI();
        
        Actor[][] actorses =  taI.createMap();
        taI.printMap(actorses);
        
               
        CheckMove checkMove = new CheckMove();
        Player player = new Player("p1", 0, 0, 100, 1000, 1000, 0, 0);
        CoinPack coinPack = new CoinPack(100, 100, 4, 4);
        
        checkMove.shorterstPath(player, coinPack, actorses);
        
        System.out.println(coinPack.getTankDirection());
        System.out.println(coinPack.getMoveCount());
        
        
    }
    
    public Player setPlayer(int x,int y,int d,int h){
        Player player = new Player("P1", d, h, h, h, h, x, y);
        return player;
    } 

    public ArrayList<Actor> lifepackAndCoin(){
        ArrayList<Actor> actors = new ArrayList<Actor>();
        CoinPack coinPack;
        coinPack = new CoinPack(1000, 1000, 3, 1);
        actors.add(coinPack);
        coinPack = new CoinPack(1000, 1000, 3, 5);
        actors.add(coinPack);
        coinPack = new CoinPack(1000, 1000, 1, 8);
        actors.add(coinPack);
        coinPack = new CoinPack(1000, 1000, 6, 8);
        actors.add(coinPack);
        
        LifePack lifePack;
        lifePack = new LifePack(100, 4, 3);
        actors.add(lifePack);
        lifePack = new LifePack(100, 4, 7);
        actors.add(lifePack);
        lifePack = new LifePack(100, 8, 6);
        actors.add(lifePack);
        lifePack = new LifePack(100, 8, 9);
        actors.add(lifePack);
        return actors;
    }
    
    public Actor[][] createMap() {
        Actor[][] actorses = new Actor[10][10];
        Empty empty;
        Stone stone;
        Brick brick;
        Water water;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                empty = new Empty(j, i);
                actorses[i][j] = empty;
            }
        }
        brick = new Brick(5, 0, 10);
        actorses[0][5] = brick;

        brick = new Brick(1, 1, 10);
        actorses[1][1] = brick;

        brick = new Brick(3, 2, 10);
        actorses[2][3] = brick;

        brick = new Brick(7, 2, 1);
        actorses[2][7] = brick;

        brick = new Brick(5, 5, 1);
        actorses[5][5] = brick;

        brick = new Brick(2, 6, 1);
        actorses[6][2] = brick;

        brick = new Brick(7, 7, 1);
        actorses[7][7] = brick;

        brick = new Brick(7, 8, 1);
        actorses[8][7] = brick;

        brick = new Brick(1, 9, 1);
        actorses[9][1] = brick;

        stone = new Stone(2, 3);
        actorses[3][2] = brick;

        stone = new Stone(6, 4);
        actorses[4][6] = brick;

        stone = new Stone(4, 6);
        actorses[6][4] = brick;

        water = new Water(0, 4);
        actorses[4][0] = water;

        water = new Water(7, 1);
        actorses[1][7] = water;
        water = new Water(4, 2);
        actorses[2][4] = water;
        water = new Water(1, 5);
        actorses[5][1] = water;
        water = new Water(6, 5);
        actorses[5][6] = water;
        water = new Water(4, 8);
        actorses[8][4] = water;
        water = new Water(4, 9);
        actorses[9][4] = water;
        
        return actorses;
    }
    
    public void  printMap(Actor [][] actorses){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(actorses[i][j].getType() +"  ");
            }
            System.out.println();
        }
    }
    public void  printMap(int [][] actorses){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(actorses[i][j] +"  ");
            }
            System.out.println();
        }
    }

}
