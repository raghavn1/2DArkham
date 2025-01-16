package com.github.ZainBarlas0.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.github.ZainBarlas0.Main;

public class Batarang {
    public World world;
    public Body body;


    public Batarang(World world){
        this.world = world;


    }

    public void shoot(String dir, Body b){
        BodyDef bdef = new BodyDef();
        if(dir == "FWD") {
            bdef.position.set(b.getWorldCenter().x + 0.05f, b.getPosition().y + 0.025f);
        }else if(dir == "BWD"){
            bdef.position.set(b.getWorldCenter().x - 0.05f, b.getPosition().y + 0.025f);
        }
        bdef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(2/ Main.PPM);

        fdef.shape = shape;
        body.createFixture(fdef);

        if(dir == "FWD"){
            body.applyLinearImpulse(new Vector2(8f ,1.5f),new Vector2(body.getPosition().x,body.getPosition().y),true);
        }
        if(dir == "BWD"){
            body.applyLinearImpulse(new Vector2(-8f,1.5f),new Vector2(body.getPosition().x -10,body.getPosition().y) ,true);
        }

    }
}
