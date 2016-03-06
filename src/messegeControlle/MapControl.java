/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messegeControlle;

import AI.AI;
import Actor.Actor;
import Actor.Brick;
import Actor.CoinAndLifePack;
import Actor.CoinPack;
import Actor.Empty;
import Actor.LifePack;
import Actor.Player;
import Actor.Stone;
import Actor.Water;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.text.PlainDocument;
import tank.TankClient;

/**
 *
 * @author Buddhi
 */
public class MapControl {

    private Actor[][] map;
    private ArrayList<Player> players;
    Player client;
    String playerName;

    //int clientId;
    public MapControl() {
        map = new Actor[10][10];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Empty empty = new Empty(i, j);
                map[i][j] = empty;
            }
        }
        players = new ArrayList<Player>();
    }

    /**
     * @return the map
     */
    public Actor[][] getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Actor[][] map) {
        this.map = map;
    }

    public void initializeMap(String s) {

        //I:P2:8,6;0,8;8,4;4,7;1,3;1,8;7,4:6,8;7,1;4,3;2,4;7,6;9,3;7,2;6,3:2,7;2,6;9,8;1,4;1,7;4,2;5,7;3,8;0,3;2,3#
        String string = s.substring(2, s.length() - 1);

//        tokenize the string
        StringTokenizer tokenizer = new StringTokenizer(string, ":");
        this.playerName = tokenizer.nextToken();
        String bricks = tokenizer.nextToken();
        String stones = tokenizer.nextToken();
        String water = tokenizer.nextToken();

        System.out.println("p: " + this.playerName + " bricks: " + bricks + " stones: " + stones + " water: " + water);

//        tokenize string for bricks
        StringTokenizer brickTokenizer = new StringTokenizer(bricks, ";");
        while (brickTokenizer.hasMoreTokens()) {
            String bt = brickTokenizer.nextToken();
            String[] split = bt.split(",");

            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

//            i for bricks
            Brick brick = new Brick(100, x, y);
            getMap()[y][x] = brick;
        }

//        tokenize string for stones
        StringTokenizer stoneTokenizer = new StringTokenizer(stones, ";");
        while (stoneTokenizer.hasMoreTokens()) {
            String st = stoneTokenizer.nextToken();
            String[] split = st.split(",");

            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

//            2 for stones
            Stone stone = new Stone(x, y);
            getMap()[y][x] = stone;
        }

//        tokenize string for water
        StringTokenizer waterTokenizer = new StringTokenizer(water, ";");
        while (waterTokenizer.hasMoreTokens()) {
            String wt = waterTokenizer.nextToken();
            String[] split = wt.split(",");

            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

//            3 for water
            Water water1 = new Water(x, y);
            getMap()[y][x] = water1;
        }

//        printMap();
    }

    public void setPlayer(String s) {

        //S:P0;0,0;0:P1;0,9;0:P2;9,0;0#
        String string = s.substring(2, s.length() - 1);

        StringTokenizer tokenizer = new StringTokenizer(string, ":");
        while (tokenizer.hasMoreTokens()) {
            String bt = tokenizer.nextToken();
            StringTokenizer token = new StringTokenizer(bt, ";");
            Player player = new Player();
            String name = token.nextToken();
            String cod = token.nextToken();
            String dir = token.nextToken();
            player.setName(name);
            player.setDirection(Integer.parseInt(dir));

            StringTokenizer token1 = new StringTokenizer(cod, ",");
            player.setX(Integer.parseInt(token1.nextToken()));
            player.setY(Integer.parseInt(token1.nextToken()));
            player.setWhetherShot(0);
            player.setCoins(0);
            player.setPoints(0);
            player.setHealth(100);

            if (player.getName() == null ? this.playerName == null : player.getName().equals(this.playerName)) {
                player.setType("P");
                client = player;
            }
            char playerNum = player.getName().charAt(1);
            int index = Integer.parseInt(String.valueOf(playerNum));
            players.add(index, player);
            getMap()[player.getY()][player.getX()] = player;
        }
    }

    public void updateMap(String string, TankClient tankClient) {
        String s = string.substring(2, string.length() - 1);

        StringTokenizer tokenizer = new StringTokenizer(s, ":");

        int i = tokenizer.countTokens();

        for (Player player : this.players) {
            if(!player.isIsDeth()){
                Empty empty = new Empty(player.getX(), player.getY());
                getMap()[empty.getY()][empty.getX()] = empty;
                System.out.println(player.getName()+"======X=="+player.getX()+"====Y====="+player.getX()+"==========================" +getMap()[empty.getY()][empty.getX()].getType());
            }
        }

        for (int j = 1; j < i; j++) {
            String s1 = tokenizer.nextToken();
            StringTokenizer st = new StringTokenizer(s1, ";");

            String playerName = st.nextToken();

            String location = st.nextToken();
            String l[] = location.split(",");
            int x = Integer.parseInt(l[0]);
            int y = Integer.parseInt(l[1]);

            int direction = Integer.parseInt(st.nextToken());
            int whetherShot = Integer.parseInt(st.nextToken());
            int health = Integer.parseInt(st.nextToken());
            int coins = Integer.parseInt(st.nextToken());
            int points = Integer.parseInt(st.nextToken());

            Player player = new Player(playerName, direction, whetherShot, coins, points, health, x, y);
            char playerNum = player.getName().charAt(1);
            int index = Integer.parseInt(String.valueOf(playerNum));

            if (!this.players.get(index).isIsDeth()) {
                if (player.getHealth() != 0) {
                    if (player.getName() == null ? client.getName() == null : player.getName().equals(client.getName())) {
                        player.setType("P");
                        client = player;
                    }
                    getMap()[player.getY()][player.getX()] = player;
                } else {
                    player.setIsDeth(true);
                    CoinPack coinPack = new CoinPack(points, Integer.MAX_VALUE, x, y);
                    getMap()[x][y] = coinPack;
                    System.out.println("=====play "+player.getName()+" was deth====");
                }
                this.players.set(index, player);
            }
        }

        String s1 = tokenizer.nextToken();
        StringTokenizer st = new StringTokenizer(s1, ";");
        while (st.hasMoreTokens()) {
            String[] ses = st.nextToken().split(",");

            int health = Integer.parseInt(ses[2]);
            if (health < 100) {
                int x = Integer.parseInt(ses[0]);
                int y = Integer.parseInt(ses[1]);

                Brick brick = new Brick(health, x, y);

                if (brick.getHealth() != 4) {
                    getMap()[y][x] = brick;
                } else {
                    Empty empty = new Empty(brick.getX(), brick.getY());
                    getMap()[y][x] = empty;
                }
            }
        }

        AI ai = new AI();
        client.setType("P");
        String msg = ai.processInputMessege(getMap(), client);
        tankClient.run(msg);
    }

    public void updateLifepack(String string) {

        String l = string.substring(2, string.length() - 1);
        String details[] = l.split(":");
        String[] positions = details[0].split(",");
        int x = Integer.parseInt(positions[0]);
        int y = Integer.parseInt(positions[1]);
        int time = Integer.parseInt(details[1]);

        LifePack lifePack = new LifePack(time, x, y);
        lifePack.setAlive(true);
        getMap()[y][x] = lifePack;
        Thread t = null;

        t = new Thread(new Runnable() {

            @Override
            public void run() {
                int reaminTime = time;
                while (reaminTime > 0) {
                    reaminTime = reaminTime - 1000;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
                Empty empty = new Empty(x, y);
                getMap()[y][x] = empty;
            }
        });
        t.start();
    }

    public void updateCoin(String string) {

        String c = string.substring(2, string.length() - 1);
        String details[] = c.split(":");
        String[] positions = details[0].split(",");
        int x = Integer.parseInt(positions[0]);
        int y = Integer.parseInt(positions[1]);

        int time = Integer.parseInt(details[1]);
        int amount = Integer.parseInt(details[2]);

        CoinPack coin = new CoinPack(amount, time, x, y);
        coin.setAlive(true);
        getMap()[y][x] = coin;

        Thread t = null;

        t = new Thread(new Runnable() {

            @Override
            public void run() {
                int reaminTime = time;
                while (reaminTime > 0) {
                    reaminTime = reaminTime - 1000;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
                Empty empty = new Empty(x, y);
                getMap()[y][x] = empty;
            }
        });
        t.start();
    }

}
