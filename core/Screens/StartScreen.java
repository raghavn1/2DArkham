package com.github.raghavn1.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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


public class StartScreen implements Screen {
    private Viewport viewport;
    private Stage stage;
    private Main game;
    private Texture backgroundTexture;
    private Texture back;
    private Table background;

    public StartScreen(Main game){
        this.game = game;
        viewport = new FitViewport(Main.V_WIDTH, Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main)game).batch);

//        backgroundTexture = new Texture(Gdx.files.internal("Arkham 2D.png"));
//        batch = new SpriteBatch();

        back = new Texture(Gdx.files.internal("Arkham 2D.png"));
        background = new Table();
        background.background(new TextureRegionDrawable(new TextureRegion(back))).setFillParent(true);
        stage.addActor(background);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new PlayScreen((Main) game));
            dispose();
        }
        stage.draw(); //Drawing the background image that we added to the table and filled the whole screen with
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
