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
import tank.TankClient;

/**
 *
 * @author Buddhi
 */
public class MapControl {

    private Actor[][] map;
    private ArrayList<Player> players;
    Player client;
    private ArrayList<CoinAndLifePack> coinAndLifePacks;
    private int coinLifePackCount;

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
        for (int i = 0; i < 5; i++) {
            Player player = new Player();
            player.setType("E");
            players.add(player);
        }
        coinAndLifePacks = new ArrayList<CoinAndLifePack>();
        coinLifePackCount = 0;
    }

    /**
     * @return the map
     */
    public Actor[][] getMap() {
        return map;
    }

    public ArrayList<CoinAndLifePack> getCoinAndLifePacks() {
        return coinAndLifePacks;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Actor[][] map) {
        this.map = map;
    }

    public void setCoinAndLifePacks(ArrayList<CoinAndLifePack> coinAndLifePacks) {
        this.coinAndLifePacks = coinAndLifePacks;
    }

    public void initializeMap(String s) {
        String string = s.substring(2, s.length() - 1);

//        tokenize the string
        StringTokenizer tokenizer = new StringTokenizer(string, ":");
        String player = tokenizer.nextToken();
        String bricks = tokenizer.nextToken();
        String stones = tokenizer.nextToken();
        String water = tokenizer.nextToken();

        System.out.println("p: " + player + " bricks: " + bricks + " stones: " + stones + " water: " + water);

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
        String string = s.substring(2, s.length() - 1);
        StringTokenizer tokenizer = new StringTokenizer(string, ";");

        Player player = new Player();
        player.setName(tokenizer.nextToken());

        String location = tokenizer.nextToken();
        String[] split = location.split(",");
        player.setX(Integer.parseInt(split[0]));
        player.setY(Integer.parseInt(split[1]));

        player.setDirection(Integer.parseInt(tokenizer.nextToken()));
        player.setWhetherShot(0);
        player.setCoins(0);
        player.setPoints(0);
        player.setHealth(100);

        player.setType("C");
        client = player;
        //clientId = players.size() - 1;
        getMap()[player.getY()][player.getX()] = client;
    }

//    private void initPlayerOnMap(Player player) {
//        char playerNum = player.getName().charAt(1);
//        //int a = Integer.parseInt(String.valueOf(playerNum));
//    }
    public void updateMap(String string, TankClient tankClient) {
        String s = string.substring(2, string.length() - 1);

        StringTokenizer tokenizer = new StringTokenizer(s, ":");

        int i = tokenizer.countTokens();

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
            
            if (player.getHealth() != 0) {
                if (player.getName().charAt(1) == client.getName().charAt(1)) {
                    Empty empty = new Empty(client.getX(), client.getY());
                    getMap()[client.getY()][client.getX()] = empty;
                    client = player;
                    getMap()[client.getY()][client.getX()] = client;
                } else {
                    setPlayerOnMap(player);
                }
            } else {
                Empty empty = new Empty(player.getX(), player.getY());
                getMap()[player.getY()][player.getX()] = empty;
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
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                System.out.print(getMap()[j][i].getType() + " ");
            }
            System.out.println("");
        }
        String msg = ai.processInputMessege(getMap(), client, getCoinAndLifePacks());
        tankClient.run(msg);
    }

    private void setPlayerOnMap(Player player) {

        char playerNum = player.getName().charAt(1);
        int a = Integer.parseInt(String.valueOf(playerNum));
        if (players.get(a).getType() != "E") {
            Empty empty = new Empty(players.get(a).getX(), players.get(a).getY());
            getMap()[players.get(a).getY()][players.get(a).getX()] = empty;
        }
        players.set(a, player);
        getMap()[players.get(a).getY()][players.get(a).getX()] = player;
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
                int index = coinLifePackCount;
                getCoinAndLifePacks().add(index, lifePack);
                coinLifePackCount++;

                while (reaminTime > 0) {
                    reaminTime = reaminTime - 1000;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    getCoinAndLifePacks().get(index).setRemainTime(reaminTime);
                }
                getCoinAndLifePacks().get(index).setAlive(false);
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
                int index = coinLifePackCount;
                getCoinAndLifePacks().add(index, coin);
                coinLifePackCount++;
                while (reaminTime > 0) {
                    reaminTime = reaminTime - 1000;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    getCoinAndLifePacks().get(index).setRemainTime(reaminTime);
                }
                getCoinAndLifePacks().get(index).setAlive(false);
                Empty empty = new Empty(x, y);
                getMap()[y][x] = empty;
            }
        });
        t.start();
    }

    public void printMap() {
        System.out.println("");
        for (int i = 0; i < getMap().length; i++) {
            for (int j = 0; j < getMap()[i].length; j++) {
                System.out.print(getMap()[i][j].getType() + "  ");
            }
            System.out.println("");
        }
    }

}
