package com.github.raghavn1.Sprites;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.github.raghavn1.Main;
import com.github.raghavn1.Scenes.Hud;
import com.github.raghavn1.Screens.PlayScreen;
import com.sun.media.sound.ModelDirectedPlayer;

import java.util.ArrayList;

public class MyContactlistener implements ContactListener {

//    public Main game = new Main();
//    public PlayScreen screen = new PlayScreen(game);
    public static ArrayList<Body> removeList  = new ArrayList<Body>();

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if(a == null || b == null) return;
        if(a.getUserData() == null || b.getUserData() == null) return;

        if(a.getUserData() instanceof Joker && b.getUserData() instanceof Batarang){
//            PlayScreen.DESTROY = true;
            addremove(a.getBody());
            addremove(b.getBody());
            System.out.println("HIT");
            Hud.addScore(10);


        }else if(a.getUserData() instanceof Batarang && b.getUserData() instanceof Joker) {
//            PlayScreen.DESTROY = true;
            addremove(a.getBody());
            addremove(b.getBody());
            System.out.println("HIT");
            Hud.addScore(10);
        }




    }
    public void addremove(Body b){
        removeList.add(b);
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
