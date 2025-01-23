package com.github.raghavn1.Sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.github.raghavn1.Main;
import com.github.raghavn1.Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Batarang extends Sprite{
    public World world;
    public Body body;
    //private Animation shoot;



    public Batarang(World world, PlayScreen screen){
        //super(screen.getAtlas().findRegion("batmanSprites") );
        this.world = world;

        //Array<TextureRegion> batarangFrames = new Array<TextureRegion>();

//        for(int i = 3; i < 7; i++){
//            batarangFrames.add(new TextureRegion(getTexture(), i * 51 /*151*/, 464, 54, 50));
//        }
//        shoot = new Animation(0.1f, batarangFrames);
//        batarangFrames.clear();
//
//        setBounds(0, 0, 2 / Main.PPM, 2 / Main.PPM);


    }
//    public void update(float dt){
//        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
//    }

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
        body.createFixture(fdef).setUserData(this);

        if(dir == "FWD"){
            body.applyLinearImpulse(new Vector2(6f ,1.5f),new Vector2(body.getPosition().x,body.getPosition().y),true);
        }
        if(dir == "BWD"){
            body.applyLinearImpulse(new Vector2(-6f,1.5f),new Vector2(body.getPosition().x -10,body.getPosition().y) ,true);
        }

    }
}
