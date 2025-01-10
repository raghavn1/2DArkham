package com.github.ZainBarlas0.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.ZainBarlas0.Main;
import com.github.ZainBarlas0.Scenes.Hud;

public class PlayScreen implements Screen {
   private Main game;



   private OrthographicCamera gamecam;
   private Viewport gamePort;
   private Hud hud;
   private TmxMapLoader mapLoader;
   private TiledMap map;
   private OrthogonalTiledMapRenderer renderer;
    public  PlayScreen(Main game){
        this.game = game;

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Main.V_WIDTH,Main.V_HEIGHT,gamecam);

        hud = new Hud(game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("TestGrounds.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2,0);
    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            gamecam.position.x +=100 * dt;
        }
    }
public void update(float dt){
       handleInput(dt);

gamecam.update();
renderer.setView(gamecam);
}
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
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
