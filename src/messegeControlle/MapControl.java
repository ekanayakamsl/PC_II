/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messegeControlle;

import AI.AI;
import Actor.Actor;
import Actor.Brick;
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
    private ArrayList<CoinPack> coinPacks;
    private ArrayList<LifePack> lifePacks;

    private AI ai;
    int clientId;

    public MapControl() {
        map = new Actor[10][10];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Empty empty = new Empty(i, j);
                map[i][j] = empty;
            }
        }
        ai = new AI();
        players = new ArrayList<Player>();
        coinPacks = new ArrayList<CoinPack>();
        lifePacks = new ArrayList<LifePack>();
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
            map[y][x] = brick;
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
            map[y][x] = stone;
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
            map[y][x] = water1;
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

        players.add(player);
        clientId = players.size() - 1;
        map[player.getY()][player.getX()] = player;
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
                setPlayerOnMap(player);
            } else {
                Empty empty = new Empty(player.getX(), player.getY());
                map[player.getY()][player.getX()] = empty;
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

                map[y][x] = brick;
            }
        }

        System.out.println(" client name" + players.get(clientId).getName());
        String msg = ai.processInputMessege(map, players.get(clientId), lifePacks, coinPacks);
        System.out.println("net meggage == " + msg);
        tankClient.run(msg);
    }

    private void setPlayerOnMap(Player player) {
        char playerNum = player.getName().charAt(1);
        int a = Integer.parseInt(String.valueOf(playerNum));
        System.out.println(a);
        if (players.size() <= a) {
            players.add(player);
        } else if ((players.get(a).getY() != player.getY()) || (players.get(a).getX() != player.getX())) {
            Empty empty = new Empty(players.get(a).getX(), players.get(a).getY());
            map[players.get(a).getY()][players.get(a).getX()] = empty;
            players.set(a, player);
            System.out.println("(players[a].getY() != player.getY()) || (players[a].getX() != player.getX()");
        }
        map[player.getY()][player.getX()] = player;
    }

    public void updateLifepack(String string) {

        String l = string.substring(2, string.length() - 1);
        System.out.println(l);
        String details[] = l.split(":");
        System.out.println(details[0]);
        String[] positions = details[0].split(",");
        int x = Integer.parseInt(positions[0]);
        int y = Integer.parseInt(positions[1]);
        int time = Integer.parseInt(details[1]);

        LifePack lifePack = new LifePack(time, x, y);
        map[y][x] = lifePack;
        Thread t = null;
        lifePacks.add(lifePack);
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                }
                lifePacks.remove(lifePack);
                Empty empty = new Empty(x, y);
                map[y][x] = empty;
            }
        });
        t.start();
        System.out.println("LIFE PACK X  =" + x + " Y =" + y + " time " + time);

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
        map[y][x] = coin;
        coinPacks.add(coin);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                }
                coinPacks.remove(coin);
                Empty empty = new Empty(x, y);
                map[y][x] = empty;
            }
        });
        t.start();
    }

    public void printMap() {
        System.out.println("");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j].getType() + "  ");
            }
            System.out.println("");
        }
    }
}
