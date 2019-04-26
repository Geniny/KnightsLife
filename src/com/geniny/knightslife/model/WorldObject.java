package com.geniny.knightslife.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

public class WorldObject implements  YSortable{

    private int x , y;
    private TextureRegion texture;
    private float sizeX , sizeY;
    private List<GridPoint2> tiles;
    public boolean isWalkable;

    public WorldObject(int x , int y , TextureRegion texture, float sizeX, float sizeY, GridPoint2[] tiles  )
    {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.tiles = new ArrayList<>();
        for(GridPoint2 p: tiles)
        {
            this.tiles.add(p);
        }
        this.isWalkable = true;
    }

    public WorldObject(int x , int y , boolean walkable,  TextureRegion texture, float sizeX, float sizeY, GridPoint2[] tiles  )
    {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.tiles = new ArrayList<>();
        for(GridPoint2 p: tiles)
        {
            this.tiles.add(p);
        }
        this.isWalkable = walkable;
    }


    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public float getWorldX() {
        return x;
    }

    @Override
    public float getWorldY() {
        return y;
    }

    public void update(float delta)
    {

    }

    public TextureRegion getSprite()
    {
        return texture;
    }

    public List<GridPoint2> getTiles()
    {
        return tiles;
    }
}
