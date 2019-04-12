package com.geniny.knightslife;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglInput;

public class Main {

    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Knight's Life";
        config.height = 600;
        config.width = 800;
        config.vSyncEnabled = true;

        new LwjglApplication(new KnightGame(), config);
    }
}
