package com.geniny.knightslife;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.geniny.knightslife.utils.SkinGenerator;
import com.geniny.knightslife.view.GameScreen;

public class KnightGame extends Game {

    private GameScreen screen, screen2;
    private AssetManager assetManager;
    private Skin skin;

    @Override
    public void create() {
        assetManager = new AssetManager();
        assetManager.load("res/packed/textureres.atlas", TextureAtlas.class);
        assetManager.load("res/packed/uipack.atlas",TextureAtlas.class);
        assetManager.load("res/font/small_letters_font.fnt", BitmapFont.class);
        assetManager.finishLoading();
        skin = SkinGenerator.generateSkin(assetManager);
        screen = GameScreen.InitializeScreen(this);

        screen2 = GameScreen.InitializeScreen(this);
        if(screen2 == screen)
            System.out.println("screen 2 is null");

        this.setScreen(screen);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }
    public Skin  getSkin(){
        return skin;
    }
    public  AssetManager getAssetManager(){
        return assetManager;
    }

}
