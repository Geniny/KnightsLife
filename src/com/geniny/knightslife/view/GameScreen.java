package com.geniny.knightslife.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geniny.knightslife.KnightGame;
import com.geniny.knightslife.Settings;
import com.geniny.knightslife.control.KnightController;
import com.geniny.knightslife.model.Camera;
import com.geniny.knightslife.model.Knight;
import com.geniny.knightslife.model.TERRAIN;
import com.geniny.knightslife.model.TileMap;

public class GameScreen extends AbstractScreen {

    private Camera camera;
    private Knight knight;
    private Texture player , grass1 , grass2;
    private SpriteBatch batch;
    private KnightController controller;
    private TileMap map;




    public GameScreen(KnightGame app) {
        super(app);

        camera = new Camera();
        grass1 = new Texture("res/grass1.png");
        grass2 = new Texture("res/grass2.png");
        player = new Texture("res/brendan_walk_south_1.png");
        batch = new SpriteBatch();
        map = new TileMap(10,10);
        knight = new Knight(map,0,0);
        controller = new KnightController(knight);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);

    }

    @Override
    public void render(float delta) {
        camera.update(knight.getWorldX()+0.5f,knight.getWorldY()+0.5f);
        knight.update(delta);
        batch.begin();

        float worldStartX = Gdx.graphics.getWidth()/2 - camera.getCameraX()*Settings.SCALED_TILE_SIZE;
        float worldStartY = Gdx.graphics.getHeight()/2 - camera.getCameraY()*Settings.SCALED_TILE_SIZE;
        for(int x = 0; x < map.getWidth(); x++)
            for(int y = 0; y < map.getHeight(); y++) {
                Texture render;
                if(map.getTile(x,y).getTerrain() == TERRAIN.GRASS_1){
                    render = grass1;
                }
                else
                    render = grass2;
                batch.draw(render,
                        worldStartX + x * Settings.SCALED_TILE_SIZE,
                        worldStartY + y * Settings.SCALED_TILE_SIZE,
                        Settings.SCALED_TILE_SIZE,
                        Settings.SCALED_TILE_SIZE);
            }
        batch.draw(player,worldStartX + knight.getWorldX()* Settings.SCALED_TILE_SIZE,worldStartY+ knight.getWorldY()*Settings.SCALED_TILE_SIZE,Settings.SCALED_TILE_SIZE,Settings.SCALED_TILE_SIZE*1.5f);

        batch.end();

    }
}
