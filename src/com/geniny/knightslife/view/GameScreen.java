package com.geniny.knightslife.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geniny.knightslife.KnightGame;
import com.geniny.knightslife.Settings;
import com.geniny.knightslife.control.KnightController;
import com.geniny.knightslife.model.Knight;

public class GameScreen extends AbstractScreen {

    private Knight knight;
    private Texture player;
    private SpriteBatch batch;
    private KnightController controller;




    public GameScreen(KnightGame app) {
        super(app);

        player = new Texture("res/knight1.png");
        batch = new SpriteBatch();
        knight = new Knight(0,0);
        controller = new KnightController(knight);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(player,knight.getX()* Settings.SCALED_TILE_SIZE,knight.getY()*Settings.SCALED_TILE_SIZE,Settings.SCALED_TILE_SIZE,Settings.SCALED_TILE_SIZE*1.5f);

        batch.end();

    }
}
