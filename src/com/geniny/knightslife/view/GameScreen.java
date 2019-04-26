package com.geniny.knightslife.view;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.geniny.knightslife.KnightGame;
import com.geniny.knightslife.Settings;
import com.geniny.knightslife.control.KnightController;
import com.geniny.knightslife.model.*;
import com.geniny.knightslife.utils.AnimationSet;

public class GameScreen extends AbstractScreen {

    private Camera camera;
    private Knight knight;
    private Texture grass1 , grass2;
    private SpriteBatch batch;
    private KnightController controller;
    private TileMap map;
    private WorldRenderer worldRenderer;
    private World world;




    public GameScreen(KnightGame app) {
        super(app);

        camera = new Camera();
        world = new World(100,100);

        //player = new Texture("res/brendan_walk_south_1.png");
        batch = new SpriteBatch();
        map = new TileMap(100,100);

        TextureAtlas atlas = app.getAssetManager().get("res/packed/textureres.atlas",TextureAtlas.class);

        AnimationSet animations = new AnimationSet(
                new Animation(0.3f/2f, atlas.findRegions("k_walk_north"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f, atlas.findRegions("k_walk_south"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f, atlas.findRegions("k_walk_west"),  Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f/2f, atlas.findRegions("k_walk_east"), Animation.PlayMode.LOOP_PINGPONG),
                atlas.findRegion("k_stand_north"),
                atlas.findRegion("k_stand_south"),
                atlas.findRegion("k_stand_west"),
                atlas.findRegion("k_stand_east")
        );
        knight = new Knight(map,0,99,animations);
        controller = new KnightController(knight);


        world.addActors(knight);
        worldRenderer = new WorldRenderer(app.getAssetManager(),world);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);

    }

    @Override
    public void render(float delta) {
        knight.update(delta);
        controller.update(delta);
        camera.update(knight.getWorldX()+0.5f,knight.getWorldY()+0.5f);


        batch.begin();
        worldRenderer.render(batch,camera);
/*
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
        batch.draw(knight.getSprites(),worldStartX + knight.getWorldX()* Settings.SCALED_TILE_SIZE,worldStartY+ knight.getWorldY()*Settings.SCALED_TILE_SIZE,Settings.SCALED_TILE_SIZE,Settings.SCALED_TILE_SIZE*1.5f);
*/
        batch.end();

    }
}
