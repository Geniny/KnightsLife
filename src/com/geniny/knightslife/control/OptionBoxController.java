package com.geniny.knightslife.control;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.geniny.knightslife.ui.OptionBox;


public class OptionBoxController extends InputAdapter {

    private OptionBox box;

    public OptionBoxController(OptionBox box)
    {
        this.box = box;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        if(keycode == Keys.W)
        {
            box.moveUp();
        }
        else if(keycode == Keys.S)
        {
            box.moveDown();
        }
        return false;
    }
}
