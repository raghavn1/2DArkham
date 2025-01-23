package com.github.raghavn1.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.github.raghavn1.Main;
import com.github.raghavn1.Screens.PlayScreen;

public class Batman extends Sprite {
    public enum State { FALLING, JUMPING, STANDING, RUNNING };
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion batmanStand;
    private Animation batmanRun;
    private Animation batmanJump;
    private float stateTimer;
    private boolean runningRight;

    public Batman(PlayScreen screen){
        super(screen.getBatmanAtlas().findRegion("batmanSprites"));
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 1; i < 8; i++){
            frames.add(new TextureRegion(getTexture(), i * 57, 115, 44, 44));
        } //Running animation FIX THIS WITH THE NEW SPRITESHEET WITH CONSISTENT SPRITE DIMENSIONS

        //ADD ANOTHER FOR LOOP FOR THE JUMP ANIMATION AS IN THE SAME FORMAT AS ABOVE WITH THE WORKING SPRITESHEET
        batmanRun = new Animation(0.1f, frames);
        frames.clear();

        batmanStand = new TextureRegion(getTexture(), 116, 13, 34, 52); //FIX THIS WITH THE WORKING SPRITESHEET AND ADD THAT FRAME NUMBERS AND WIDTH HERE

        defineBatman();
        setBounds(0, 0, 17 / Main.PPM, 26 / Main.PPM);
        setRegion(batmanStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch(currentState){
//            case JUMPING:
//                region = batmanJump.getKeyFrame(stateTimer);
//                break;
            case RUNNING:
                region = (TextureRegion) batmanRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = batmanStand;
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        } //Checks if batman is standing still and was facing left or right previously

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;

    }

    public State getState(){
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING)){
            return State.JUMPING;
        }else if(b2body.getLinearVelocity().y < 0){
            return State.FALLING;
        }else if(b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }else {
            return State.STANDING;
        }

    }

    public void defineBatman(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Main.PPM, 32 / Main.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
//        CircleShape shape = new CircleShape();
//        shape.setRadius(3 / Main.PPM);
        PolygonShape shape = new PolygonShape(); // Just testing out a rectangle instead of a circle
        shape.setAsBox(6f / Main.PPM, 12f / Main.PPM); // Testing

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
}
