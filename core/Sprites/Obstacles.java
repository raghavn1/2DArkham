package com.github.raghavn1.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.github.raghavn1.Main;
import com.github.raghavn1.Scenes.Hud;
import com.github.raghavn1.Screens.PlayScreen;

public class Obstacles extends InteractiveTileObject{
    public Obstacles(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
    }
}
