package com.geniny.knightslife.model;

import java.util.Random;

public class TileMap {

    private int width , height;
    private Tile[][] tiles;

    public TileMap(int width , int height){
        this.height = height;
        this.width = width;



        tiles = new Tile[width][height];
        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
                if(Math.random() > 0.5d)
                {
                    tiles[x][y] = new Tile(TERRAIN.GRASS_1);
                }
                else
                    tiles[x][y] = new Tile(TERRAIN.GRASS_2);
    }


    public Tile getTile(int x , int y)
    {
        return  tiles[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}


