package com.github.ZainBarlas0.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.ZainBarlas0.Main;
import com.github.ZainBarlas0.Scenes.MUI;

public class MenuScreen implements Screen {
    private Main game;
    public Viewport viewport;
    public OrthographicCamera gamecam;

    private MUI ui;
    public MenuScreen(Main game){
        this.game = game;
        gamecam = new OrthographicCamera();
        viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT,gamecam);
       ui = new MUI(game.batch);
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(ui.stage.getCamera().combined);
        ui.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
