package com.geniny.knightslife.model;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

public class World {

    private TileMap map;
    private List<Knight> actors;
    private List<WorldObject> objects;

    public World(int width , int height)
    {
        this.map = new TileMap(width,height);
        actors = new ArrayList<>();
        objects = new ArrayList<>();
    }

    public void addActors(Knight a)
    {
        map.getTile(a.getX(),a.getY()).setKnight(a);
        actors.add(a);
    }

    public void addObject(WorldObject o)
    {
        for(GridPoint2 p : o.getTiles())
        {
            map.getTile(o.getX() +p.x,o.getY()+p.y).setObject(o);

        }
        objects.add(o);
    }

    public void update(float delta)
    {
        for(Knight a : actors)
        {
            a.update(delta);
        }
        for(WorldObject o: objects)
        {
            o.update(delta);
        }
    }

    public TileMap getMap()
    {
        return map;
    }


}
