package com.github.ZainBarlas0.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.github.ZainBarlas0.Main;
import com.github.ZainBarlas0.Screens.PlayScreen;

import java.awt.Button;

public class Batman extends Sprite {
    public enum State{FALLING, JUMPING,STANDING,RUNNING,A1,A2,A3};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    public PolygonShape shape;
    public boolean jump_ability;
    public float height;
    private TextureRegion batmanstand;
    private Animation<TextureRegion> batmanRun;
    private Animation<TextureRegion> batmanJump;
    private TextureRegion ATK1;
    private TextureRegion ATK2;
    private TextureRegion ATK3;
    private float stateTimer;
    private boolean runningRight;



    public Batman(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("Daredevil.png"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i<=11; i++){
            frames.add(new TextureRegion(getTexture(),i * 28, 244,27,45));
        }
        batmanRun = new Animation<TextureRegion>(0.09f,frames);
        frames.clear();

        for(int i = 1; i<5; i++){
            frames.add(new TextureRegion(getTexture(),i*25,55,27,45));
        }
        batmanJump = new Animation<TextureRegion>(0.1f,frames);

        Batman_def();

        batmanstand = new TextureRegion(getTexture(),0,0,27,47);
        setBounds(0,0,18/Main.PPM,28/Main.PPM);
        setRegion(batmanstand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2,b2body.getPosition().y-getHeight()/2);
        setRegion(getFrame(dt));
    }

    public  TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion R;
        switch(currentState){
            case JUMPING:
                R = batmanJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                R = batmanRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
            case STANDING:
            default:
                R = batmanstand;
                break;
        }
        if((b2body.getLinearVelocity().x<0 || ! runningRight) && !R.isFlipX()){
            R.flip(true,false);
            runningRight = false;
        }else if((b2body.getLinearVelocity().x>0 || runningRight) && R.isFlipX()){
            R.flip(true,false);
            runningRight  = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt:0;
        previousState = currentState;
        return R;
    }
    public  State getState(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
            return State.A1;
        }

        if(b2body.getLinearVelocity().y>0 ||(b2body.getLinearVelocity().y<0 && previousState == State.JUMPING)){
            return State.JUMPING;
        } else if (b2body.getLinearVelocity().y<0){
            return State.FALLING;
        } else if (b2body.getLinearVelocity().x !=0) {
            return State.RUNNING;
        } else{
            return State.STANDING;
        }
    }

    public void Batman_def(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Main.PPM, 32 / Main.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        shape = new PolygonShape();
        shape.setAsBox(5f / Main.PPM, 10f / Main.PPM);


        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}
