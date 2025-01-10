package com.github.ZainBarlas0;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.ZainBarlas0.Screens.MenuScreen;
import com.github.ZainBarlas0.Screens.PlayScreen;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    enum Screen{
        TITLE, MAIN_GAME
    }

    Screen currentScreen = Screen.TITLE;
    public SpriteBatch batch;
    public static final int V_WIDTH = 400;
    public  static final int V_HEIGHT = 208;

    @Override
    public void create() {
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
      super.render();
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            if(currentScreen == Screen.TITLE){
                currentScreen = Screen.MAIN_GAME;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            if(currentScreen == Screen.MAIN_GAME){
                currentScreen = Screen.TITLE;
            }
        }
      if(currentScreen == Screen.TITLE){
          setScreen(new MenuScreen(this));
      }
      if(currentScreen == Screen.MAIN_GAME){
          setScreen(new PlayScreen(this));
      }
    }

}
