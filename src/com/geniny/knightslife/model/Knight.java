package com.geniny.knightslife.model;

import com.badlogic.gdx.math.Interpolation;
import com.geniny.knightslife.Settings;

public class Knight extends GameObject {

    private float worldX , worldY;
    private int srcX , srcY;
    private  int destX, destY;
    private float animTimer;

    private KNIGHT_STATE state;

    public enum KNIGHT_STATE{
        WALKING,
        STANDING,
        ;
    }

    public Knight(TileMap map , int x , int y)
    {
        this.map = map;
        this.x = x;
        this.y = y;
        this.worldX = x;
        this.worldY = y;
        this.state = KNIGHT_STATE.STANDING;
        map.getTile(x,y).setKnight(this);
    }

    public boolean move(int dx , int dy)
    {
        if(state != KNIGHT_STATE.STANDING)
            return false;
        if(x+dx >= map.getWidth() || x+dx < 0 || y+dy >= map.getHeight() || y +dy <0){
            return false;
        }
        if(map.getTile(x+dx,y+dy).getKnight() != null)
        {
            return false;
        }
        initializeMove(x,y,dx,dy);
        map.getTile(x,y).setKnight(null);
        x += dx;
        y += dy;
        map.getTile(x,y).setKnight(this);

        return true;
    }

    private void initializeMove(int oldX , int oldY , int dx , int dy)
    {
        this.srcX = oldX;
        this.srcY = oldY;
        this.destX = oldX + dx;
        this.destY = oldY + dy;
        this.worldY = oldY;
        this.worldX = oldX;
        animTimer = 0f;
        state = KNIGHT_STATE.WALKING;
    }

    public void finishMove(){
        state = KNIGHT_STATE.STANDING;
        this.worldX = destX;
        this.worldY = destY;
        this.srcX = 0;
        this.srcY = 0;
        this.destY = 0;
        this.destX = 0;

    }

    public void update(float delta)
    {
        if(state == KNIGHT_STATE.WALKING){
            animTimer += delta;
            worldY = Interpolation.linear.apply(srcY,destY,animTimer/ Settings.ANIM_TIME);
            worldX = Interpolation.linear.apply(srcX,destX,animTimer/Settings.ANIM_TIME);
            if(animTimer > Settings.ANIM_TIME)
                finishMove();
        }
    }

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY(){
        return worldY;
    }
}
