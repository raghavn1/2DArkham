package com.github.ZainBarlas0.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Obstacles extends InteractiveTileObject{
    public Obstacles(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
    }
}
