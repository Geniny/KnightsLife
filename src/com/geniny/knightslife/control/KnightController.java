package com.geniny.knightslife.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.geniny.knightslife.model.DIRECTION;
import com.geniny.knightslife.model.Knight;

public class KnightController extends InputAdapter
{
    private Knight knight;
    private boolean [] buttonPress;
    private float[] buttonTimer;
    private float WALK_REFACE_THRESHOLD = 0.07f;

    public KnightController(Knight knight)
    {
        this.knight = knight;
        buttonPress = new boolean[DIRECTION.values().length];
        buttonPress[DIRECTION.NORTH.ordinal()] = false;
        buttonPress[DIRECTION.SOUTH.ordinal()] = false;
        buttonPress[DIRECTION.EAST.ordinal()] = false;
        buttonPress[DIRECTION.WEST.ordinal()] = false;
        buttonTimer = new float[DIRECTION.values().length];
        buttonTimer[DIRECTION.NORTH.ordinal()] = 0f;
        buttonTimer[DIRECTION.SOUTH.ordinal()] = 0f;
        buttonTimer[DIRECTION.EAST.ordinal()] = 0f;
        buttonTimer[DIRECTION.WEST.ordinal()] = 0f;
    }


    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.UP)
        {
            buttonPress[DIRECTION.NORTH.ordinal()] = true;
        }

        if(keycode == Input.Keys.DOWN)
        {
            buttonPress[DIRECTION.SOUTH.ordinal()] = true;
        }

        if(keycode == Input.Keys.LEFT)
        {
            buttonPress[DIRECTION.WEST.ordinal()] = true;
        }

        if(keycode == Input.Keys.RIGHT)
        {
            buttonPress[DIRECTION.EAST.ordinal()] = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if(keycode == Input.Keys.UP)
        {
            releaseDirection(DIRECTION.NORTH);
        }

        if(keycode == Input.Keys.DOWN)
        {
            releaseDirection(DIRECTION.SOUTH);
        }

        if(keycode == Input.Keys.LEFT)
        {
            releaseDirection(DIRECTION.WEST);
        }

        if(keycode == Input.Keys.RIGHT)
        {
            releaseDirection(DIRECTION.EAST);
        }
        return false;
    }

    public void update(float delta){
        if(buttonPress[DIRECTION.NORTH.ordinal()]){
            updateDirection(DIRECTION.NORTH , delta);
            return;
        }
        if(buttonPress[DIRECTION.SOUTH.ordinal()]){
            updateDirection(DIRECTION.SOUTH , delta);
            return;
        }
        if(buttonPress[DIRECTION.WEST.ordinal()]){
            updateDirection(DIRECTION.WEST , delta);
            return;
        }
        if(buttonPress[DIRECTION.EAST.ordinal()]){
            updateDirection(DIRECTION.EAST , delta);
            return;
        }
    }
    
    private void updateDirection(DIRECTION dir, float delta) {
        buttonTimer[dir.ordinal()] += delta;
        considerMove(dir);
    }
    
    private void releaseDirection(DIRECTION dir) {
        buttonPress[dir.ordinal()] = false;
        considerReface(dir);
        buttonTimer[dir.ordinal()] = 0f;
    }

    private void considerMove(DIRECTION dir) {
        if (buttonTimer[dir.ordinal()] > WALK_REFACE_THRESHOLD) {
            knight.move(dir);
        }
    }

    private void considerReface(DIRECTION dir) {
        if (buttonTimer[dir.ordinal()] < WALK_REFACE_THRESHOLD) {
            knight.reface(dir);
        }
    }

    
}
