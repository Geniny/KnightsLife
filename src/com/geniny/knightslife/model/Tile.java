package com.geniny.knightslife.model;


public class Tile {

	private TERRAIN terrain;
	private Knight knight;
	private WorldObject object;

	public Tile(TERRAIN terrain)
	{
		this.terrain = terrain;
	}

	public TERRAIN getTerrain()
	{
		return terrain;
	}

	public Knight getKnight(){
		return knight;
	}

	public void setKnight(Knight knight){
		this.knight = knight;
	}

	public WorldObject getObject(){ return object; }

	public void setObject(WorldObject object){ this.object = object; }

}
