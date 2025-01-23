package com.github.raghavn1.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.raghavn1.Main;
import com.github.raghavn1.Scenes.Hud;
import com.sun.org.apache.xpath.internal.operations.Or;

public class EndScreen implements Screen {
    private Main game;
    private Viewport viewport;
    private Stage stage;
    private Texture backgroundTexture;
    private SpriteBatch batch;
    private Texture back;
    private Table background;
    public EndScreen(Main game){
        this.game = game;
        viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main)game).batch);

        back = new Texture(Gdx.files.internal("gameOver.jpg"));
        background = new Table();
        background.background(new TextureRegionDrawable(new TextureRegion(back))).setFillParent(true);
        stage.addActor(background);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        stage.draw();
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
