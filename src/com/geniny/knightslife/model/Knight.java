package com.geniny.knightslife.model;

public class Knight extends GameObject {


    public Knight(TileMap map , int x , int y)
    {
        this.map = map;
        this.x = x;
        this.y = y;
        map.getTile(x,y).setKnight(this);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean move(int dx , int dy)
    {
        if(x+dx >= map.getWidth() || x+dx < 0 || y+dy >= map.getHeight() || y +dy <0){
            return false;
        }
        if(map.getTile(x+dx,y+dy).getKnight() != null)
        {
            return false;
        }
        map.getTile(x,y).setKnight(null);
        x += dx;
        y += dy;
        map.getTile(x,y).setKnight(this);

        return true;
    }
}
