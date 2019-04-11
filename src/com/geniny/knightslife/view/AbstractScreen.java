package com.geniny.knightslife.view;

import com.badlogic.gdx.Screen;
import com.geniny.knightslife.KnightGame;

public abstract class  AbstractScreen implements Screen {

    private KnightGame app;

    public AbstractScreen(KnightGame app) {
        this.app = app;
    }

    @Override
    public  abstract void show();

    @Override
    public abstract void render(float v);

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
