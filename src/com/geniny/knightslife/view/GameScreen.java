package com.geniny.knightslife.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.geniny.knightslife.control.DialogueController;
import com.geniny.knightslife.control.KnightController;
import com.geniny.knightslife.control.OptionBoxController;
import com.geniny.knightslife.model.*;
import com.geniny.knightslife.ui.Dialogue;
import com.geniny.knightslife.ui.DialogueBox;
import com.geniny.knightslife.ui.DialogueNode;
import com.geniny.knightslife.ui.OptionBox;
import com.geniny.knightslife.utils.AnimationSet;

public class GameScreen extends AbstractScreen {

    private static GameScreen screen = null;
    private Camera camera;
    private Knight knight;
    private Texture grass1 , grass2;
    private SpriteBatch batch;

    private KnightController controller;
    private DialogueController dialogueController;
    private InputMultiplexer multiplexer;

    private TileMap map;
    private WorldRenderer worldRenderer;
    private World world;
    private Viewport gameViewport;
    private int uiScale = 2;

    private Stage uiStage;
    private Table dialogRoot;
    private DialogueBox dialogueBox;
    private OptionBox optionBox;

    private Dialogue dialogue;

    public static GameScreen InitializeScreen(KnightGame game){
        if(screen == null) {
            screen = new GameScreen(game);
            return screen;
        }
        return screen;
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
        initUI();

        knight = new Knight(map,5,5,animations);
        controller = new KnightController(knight);
        dialogueController = new DialogueController(dialogueBox, optionBox);

        world.addActors(knight);
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(0 , dialogueController);
        multiplexer.addProcessor( 1, controller);


        worldRenderer = new WorldRenderer(app.getAssetManager(),world);

        dialogue = new Dialogue();

        DialogueNode node1 = new DialogueNode("Hello! \nNcie to meet you." , 0);
        DialogueNode node2 = new DialogueNode("A u a boy / girl" , 1);
        DialogueNode node3 = new DialogueNode("U a Boy" , 2);
        DialogueNode node4 = new DialogueNode("U a Girl" , 3);

        node1.makeLinear(node2.getID());
        node2.addChoice("Boy", 2);
        node2.addChoice("Girl", 3);

        dialogue.addNode(node1);
        dialogue.addNode(node2);
        dialogue.addNode(node3);
        dialogue.addNode(node4);

        dialogueController.startDialogue(dialogue);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        knight.update(delta);
        controller.update(delta);
        dialogueController.update(delta);
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

        dialogRoot = new Table();
        dialogRoot.setFillParent(true);
        uiStage.addActor(dialogRoot);

        dialogueBox = new DialogueBox(getApp().getSkin());
        dialogueBox.setVisible(false);

        optionBox = new OptionBox(getApp().getSkin());
        optionBox.setVisible(false);

        Table dialogTable = new Table();
        dialogTable.add(optionBox)
                .expand()
                .align(Align.right)
                .space(8f)
                .row();
        dialogTable.add(dialogueBox)
                .expand()
                .align(Align.bottom)
                .space(8f)
                .row();


        dialogRoot.add(dialogTable).expand().align(Align.bottom);


    }
}
