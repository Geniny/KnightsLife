package com.geniny.knightslife.view;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.geniny.knightslife.KnightGame;
import com.geniny.knightslife.Settings;
import com.geniny.knightslife.control.KnightController;
import com.geniny.knightslife.model.*;
import com.geniny.knightslife.utils.AnimationSet;

public class GameScreen extends AbstractScreen {

    private static GameScreen screen = null;
    private Camera camera;
    private Knight knight;
    private Texture grass1 , grass2;
    private SpriteBatch batch;
    private KnightController controller;
    private TileMap map;
    private WorldRenderer worldRenderer;
    private World world;
    private Viewport gameViewport;
    private int uiScale = 2;

    private Stage uiStage;
    private Table root;
    private DialogueBox dialogueBox;

    public static GameScreen InitializeScreen(KnightGame game){
        if(screen == null) {
            screen = new GameScreen(game);
            return screen;
        }
        return null;
    }


    private GameScreen(KnightGame app) {
        super(app);

        gameViewport = new ScreenViewport();
        camera = new Camera();
        world = new World(10,10);

        //player = new Texture("res/brendan_walk_south_1.png");
        batch = new SpriteBatch();
        map = new TileMap(10,10);

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
        knight = new Knight(map,5,5,animations);
        controller = new KnightController(knight);


        world.addActors(knight);
        worldRenderer = new WorldRenderer(app.getAssetManager(),world);

        initUI();
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
        uiStage.act(delta);
        gameViewport.apply();

        batch.begin();
        worldRenderer.render(batch,camera);
        batch.end();

        uiStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
        uiStage.getViewport().update(width/uiScale, height/uiScale, true);
        gameViewport.update(width,height);
    }


    private void initUI() {
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale, true);

        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);

        dialogueBox = new DialogueBox(getApp().getSkin());
        dialogueBox.animateText("Hello young wenderer \nI'm glad to see you there");

        root.add(dialogueBox).expand().align(Align.bottom).pad(8f);
        if( dialogueBox.isFinished())
        {
            root.removeActor(dialogueBox);
        }

    }
}
