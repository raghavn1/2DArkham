package com.github.raghavn1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.raghavn1.Screens.PlayScreen;
import com.github.raghavn1.Screens.StartScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static final int V_WIDTH = 350;
    public static final int V_HEIGHT = 208;
    public static final float PPM = 100;
    public SpriteBatch batch;

    public static AssetManager manager;


    @Override
    public void create() {
       //For the menuscreen stuff
        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("audio/BatmanMusic.mp3", Music.class);
        manager.finishLoading();



        this.setScreen(new StartScreen(this));
     //   setScreen(new PlayScreen(this));
    }

    @Override
    public void render() {
        super.render();
    } // End of void render()

    public void dispose(){
        super.dispose();
        batch.dispose();
        manager.dispose();
    }



}
