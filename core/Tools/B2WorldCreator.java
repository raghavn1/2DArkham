package com.github.raghavn1.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.github.raghavn1.Main;
import com.github.raghavn1.Screens.PlayScreen;
import com.github.raghavn1.Sprites.Batman;
import com.github.raghavn1.Sprites.Obstacles;
import com.github.raghavn1.Sprites.Powerups;

public class B2WorldCreator {
    public B2WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        //Create body and fixture variables for the playable character Batman
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        //Ground layer on the tilemap
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Main.PPM, (rect.getY() + rect.getHeight() / 2) / Main.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2) / Main.PPM, (rect.getHeight() / 2) / Main.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //Obstacles layer on the tilemap
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Obstacles(screen, rect);
        }

        //MAKE A POWERUPS LAYER IN THE TILEMAP ON TILED AND THEN ADD SOME POWERUPS TO TEST AND UPDATE THE FILE IN THE
        //ASSETS FOLDER

//        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            new Powerups(world, map, rect);
//        }
    }
}
