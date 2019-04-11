package com.geniny.knightslife.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.geniny.knightslife.model.Knight;

public class KnightController extends InputAdapter
{
    private Knight knight;

    public KnightController(Knight knight){
        this.knight = knight;
    }


    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.UP)
        {
            knight.move(0,1);
        }

        if(keycode == Input.Keys.DOWN)
        {
            knight.move(0,-1);
        }

        if(keycode == Input.Keys.LEFT)
        {
            knight.move(-1,0);
        }

        if(keycode == Input.Keys.RIGHT)
        {
            knight.move(1,0);
        }
        return false;
    }
}
