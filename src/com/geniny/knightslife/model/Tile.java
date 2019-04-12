package com.geniny.knightslife.model;

public class Tile {

    private Knight knight;
    private TERRAIN terrain;

    public TERRAIN getTerrain() {
        return terrain;
    }

    public Tile(TERRAIN terrain)
    {
        this.terrain = terrain;
    }

    public void setKnight(Knight knight) {
        this.knight = knight;
    }

    public Knight getKnight()
    {
        return knight;
    }
}
