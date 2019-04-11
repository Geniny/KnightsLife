package com.geniny.knightslife.model;

public class Knight extends GameObject {



    public Knight(int x , int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void move(int dx , int dy)
    {
        x += dx;
        y += dy;
    }
}
