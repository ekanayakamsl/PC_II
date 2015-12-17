/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messegeControlle;

/**
 *
 * @author Buddhi
 */
public class TankMap {

    private String[][] map;
    private int bricksCount;
    private int stoneCount;
    private int waterCount;
    private int playerCount;

    public TankMap() {
    }

    public TankMap(int mapSize) {
        map = new String[mapSize][mapSize];
    }

    /**
     * @return the map
     */
    public String[][] getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(String[][] map) {
        this.map = map;
    }

    /**
     * @return the bricksCount
     */
    public int getBricksCount() {
        return bricksCount;
    }

    /**
     * @param bricksCount the bricksCount to set
     */
    public void setBricksCount(int bricksCount) {
        this.bricksCount = bricksCount;
    }

    /**
     * @return the stoneCount
     */
    public int getStoneCount() {
        return stoneCount;
    }

    /**
     * @param stoneCount the stoneCount to set
     */
    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }

    /**
     * @return the waterCount
     */
    public int getWaterCount() {
        return waterCount;
    }

    /**
     * @param waterCount the waterCount to set
     */
    public void setWaterCount(int waterCount) {
        this.waterCount = waterCount;
    }

    /**
     * @return the playerCount
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * @param playerCount the playerCount to set
     */
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

}
