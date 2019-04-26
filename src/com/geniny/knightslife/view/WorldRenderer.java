package com.geniny.knightslife.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.geniny.knightslife.Settings;
import com.geniny.knightslife.model.*;

public class WorldRenderer {

    private AssetManager assetManager;
    private World world;
    private Texture grass1, grass2;

    private TextureAtlas atlas;

    private List<Integer> renderedObjects = new ArrayList<Integer>();
    private List<YSortable> forRendering = new ArrayList<YSortable>();

    public WorldRenderer(AssetManager assetManager, World world) {
        this.assetManager = assetManager;
        this.world = world;
        grass1 = new Texture("res/grass1.png");
        grass2 = new Texture("res/grass2.png");

        atlas = assetManager.get("res/packed/textureres.atlas", TextureAtlas.class);
    }

    public void render(SpriteBatch batch, Camera camera) {
        float worldStartX = Gdx.graphics.getWidth()/2 - camera.getCameraX()*Settings.SCALED_TILE_SIZE;
        float worldStartY = Gdx.graphics.getHeight()/2 - camera.getCameraY()*Settings.SCALED_TILE_SIZE;

        for(int x = 0; x < world.getMap().getWidth();x++)
            for(int y = 0 ; y < world.getMap().getHeight();y++)
            {
                Texture render;
                if(world.getMap().getTile(x,y).getTerrain() == TERRAIN.GRASS_1)
                    render = grass1;
                else
                    render = grass2;
                batch.draw(render,
                        (int)(worldStartX+x*Settings.SCALED_TILE_SIZE),
                        (int)(worldStartY+y*Settings.SCALED_TILE_SIZE),
                        (int)(Settings.SCALED_TILE_SIZE),
                        (int)(Settings.SCALED_TILE_SIZE));
            }

        /* collect objects and actors */
        for (int x = 0; x < world.getMap().getWidth(); x++)
            for (int y = 0; y < world.getMap().getHeight(); y++)
            {
                if (world.getMap().getTile(x, y).getObject() != null)
                {
                    WorldObject object = world.getMap().getTile(x, y).getObject();
                    if (renderedObjects.contains(object.hashCode()))
                    {
                        continue;
                    }
                    forRendering.add(object);
                    renderedObjects.add(object.hashCode());
                }
                if(world.getMap().getTile(x,y).getKnight()!= null)
                {
                    Knight actor = world.getMap().getTile(x, y).getKnight();
                    forRendering.add(actor);
                }

            }


        Collections.sort(forRendering, new WorldObjectYComparator());
        Collections.reverse(forRendering);

        for (YSortable loc : forRendering) {
            batch.draw(loc.getSprite(),
                    (int)(worldStartX+loc.getWorldX()*Settings.SCALED_TILE_SIZE),
                    (int)(worldStartY+loc.getWorldY()*Settings.SCALED_TILE_SIZE),
                    (int)(Settings.SCALED_TILE_SIZE),
                    (int)(Settings.SCALED_TILE_SIZE));
        }

        renderedObjects.clear();
        forRendering.clear();
    }

    public void setWorld(World world) {
        this.world = world;
    }

}

