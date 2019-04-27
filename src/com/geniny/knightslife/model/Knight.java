package com.geniny.knightslife.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.geniny.knightslife.Settings;
import com.geniny.knightslife.utils.AnimationSet;

public class Knight extends GameObject implements YSortable {

    private TileMap map;
    private float worldX , worldY , animTimer , walkTimer;
    private int srcX , srcY , destX, destY;
    private KNIGHT_STATE state;
    private DIRECTION facing;
    private boolean moveRequestThisFrame;
    private AnimationSet animations;

    private float WALK_TIME_PER_TILE = 0.4f;
    private float REFACE_TIME = 0.1f;

    public enum KNIGHT_STATE
    {
        WALKING,
        STANDING,
        REFACING,
        ;
    }

    public Knight(TileMap map , int x , int y , AnimationSet animations)
    {
        this.animations = animations;
        this.map = map;
        this.x = x;
        this.y = y;
        this.worldX = x;
        this.worldY = y;
        this.state = KNIGHT_STATE.STANDING;
        this.facing = DIRECTION.SOUTH;
        map.getTile(x,y).setKnight(this);
    }

    public boolean move(DIRECTION dir)
    {

        if(state == KNIGHT_STATE.WALKING) {
            if (facing == dir) {
                moveRequestThisFrame = true;
            }
            return false;
        }
        if(x+dir.getDx() >= map.getWidth() || x+dir.getDx() < 0 || y+dir.getDy() >= map.getHeight() || y +dir.getDy() <0){
            if(dir == DIRECTION.SOUTH || dir == DIRECTION.NORTH)
                dir = DIRECTION.EAST;
            else if (dir == DIRECTION.WEST )
                dir = DIRECTION.NORTH;
            else if(dir == DIRECTION.EAST)
                dir = DIRECTION.SOUTH;

            reface(dir);
            return false;
        }
        if(map.getTile(x+dir.getDx(),y+dir.getDy()).getKnight() != null)
        {

            reface(dir);
            return false;
        }
        if(map.getTile(x+dir.getDx(), y + dir.getDy()).getObject() != null)
        {
            WorldObject o = map.getTile(x+dir.getDx(),y + dir.getDy()).getObject();
            if(!o.isWalkable)
            {
                reface(dir);
                return false;
            }
        }
        initialiseMove(dir);
        map.getTile(x,y).setKnight(null);
        x += dir.getDx();
        y += dir.getDy();
        map.getTile(x,y).setKnight(this);

        return true;
    }

    private void initialiseMove( DIRECTION dir)
    {
        this.facing = dir;
        this.srcY = y;
        this.srcX = x;
        this.destX = x + dir.getDx();
        this.destY = y + dir.getDy();
        this.worldX = x;
        this.worldY = y;
        animTimer = 0f;
        state = KNIGHT_STATE.WALKING;
    }

    private void finishMove(){
        state = KNIGHT_STATE.STANDING;
        this.worldX = destX;
        this.worldY = destY;
        this.destY = 0;
        this.destX = 0;
        this.srcX = 0;
        this.srcY = 0;
    }

    public void update(float delta)
    {
        if(state == KNIGHT_STATE.WALKING)
        {
            walkTimer += delta;
            animTimer += delta;
            worldX = Interpolation.linear.apply(srcX,destX,animTimer/ WALK_TIME_PER_TILE);
            worldY = Interpolation.linear.apply(srcY,destY,animTimer/ WALK_TIME_PER_TILE);
            if(animTimer > WALK_TIME_PER_TILE)
            {
                float leftOverTime = animTimer - WALK_TIME_PER_TILE;
                finishMove();
                if(moveRequestThisFrame){
                   if(move(facing))
                   {
                       animTimer += leftOverTime;
                       worldX = Interpolation.linear.apply(srcX,destX,animTimer/ WALK_TIME_PER_TILE);
                       worldY = Interpolation.linear.apply(srcY,destY,animTimer/ WALK_TIME_PER_TILE);
                   }
                }
                else
                    walkTimer = 0f;
            }
        }
        if(state == KNIGHT_STATE.REFACING){
            animTimer += delta;
            if(animTimer > REFACE_TIME){
                state = KNIGHT_STATE.STANDING;
            }
        }
        moveRequestThisFrame = false;
    }

     public int getX(){
        return x;
     }

     public int getY(){
        return y;
     }

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY()
    {
        return worldY;
    }

    public TextureRegion getSprite(){
        if(state == KNIGHT_STATE.WALKING)
            return animations.getWalking(facing).getKeyFrame(walkTimer);
        else if(state == KNIGHT_STATE.STANDING)
            return  animations.getStanding(facing);
        else if(state == KNIGHT_STATE.REFACING)
            return animations.getWalking(facing).getKeyFrames()[0];
        return animations.getStanding(DIRECTION.SOUTH);
    }


    public void reface(DIRECTION dir) {
        if (state != KNIGHT_STATE.WALKING.STANDING) {
            return;
        }
        if (facing == dir) {
            return;
        }
        facing = dir;
        state = KNIGHT_STATE.WALKING.REFACING;
        animTimer = 0f;
    }
}
