package com.github.raghavn1.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.github.raghavn1.Main;
import com.github.raghavn1.Screens.PlayScreen;

public class Joker extends Sprite {
    public enum jokerState { STANDING, RUNNING, FALLING };
    public World world;
    public Body b2body;
    private TextureRegion jokerStand;
    private Animation jokerRun;
    private Animation jokerJump;
    public jokerState currentState;
    public jokerState previousState;
    private float stateTimer;
    private boolean runningRight;

    public Joker(PlayScreen screen){
        super(screen.getJokerAtlas().findRegion("Thug"));
        this.world = screen.getWorld();
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 0; i < 7; i++){
            frames.add(new TextureRegion(getTexture(), i * 27, 73, 38, 67));
        } //Running animation FIX THIS WITH THE NEW SPRITESHEET WITH CONSISTENT SPRITE DIMENSIONS

        //ADD ANOTHER FOR LOOP FOR THE JUMP ANIMATION AS IN THE SAME FORMAT AS ABOVE WITH THE WORKING SPRITESHEET
        jokerRun = new Animation(0.15f, frames);
        frames.clear();

        jokerStand = new TextureRegion(getTexture(), 0, 0, 36, 73); //FIX THIS WITH THE WORKING SPRITESHEET AND ADD THAT FRAME NUMBERS AND WIDTH HERE

        defineJoker();
        setBounds(0, 0, 18 / Main.PPM, 36 / Main.PPM);
        setRegion(jokerStand);
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
                region = (TextureRegion) jokerRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = jokerStand;
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

    public Joker.jokerState getState(){
        if(b2body.getLinearVelocity().y < 0){
            return Joker.jokerState.FALLING;
        }else if(b2body.getLinearVelocity().x != 0){
            return Joker.jokerState.RUNNING;
        }else {
            return Joker.jokerState.STANDING;
        }

    }

    public void defineJoker(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(500 / Main.PPM, 32 / Main.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
//        CircleShape shape = new CircleShape();
//        shape.setRadius(3 / Main.PPM);
        PolygonShape shape = new PolygonShape(); // Just testing out a rectangle instead of a circle
        shape.setAsBox(9f / Main.PPM, 18f / Main.PPM); // Testing

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
}
