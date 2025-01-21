package com.github.ZainBarlas0.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.ZainBarlas0.Entities.Batarang;
import com.github.ZainBarlas0.Entities.Batman;
import com.github.ZainBarlas0.Main;
import com.github.ZainBarlas0.Scenes.HUD;
import com.github.ZainBarlas0.Tools.B2WorldCreator;




public class PlayScreen implements Screen {
    private Main game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private HUD hud;
    private TextureAtlas atlas;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Batman player;
    private Batarang projectile;
    private boolean duck;
    private String dir;
    public  PlayScreen(Main game){
        this.game = game;
        atlas = new TextureAtlas("Daredevil.pack");
        dir = "FWD";
        duck = false;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Main.V_WIDTH / Main.PPM,Main.V_HEIGHT / Main.PPM,gamecam);

        hud = new HUD(game.batch);
        maploader = new TmxMapLoader();
        map = maploader.load("ExperimentMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Main.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2,gamePort.getWorldHeight() / 2,0);
        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        player = new Batman(world,this);

    }
    public  TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.D) && player.b2body.getLinearVelocity().x < 2){
            dir = "FWD";
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0), player.b2body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && player.b2body.getLinearVelocity().x > -2){
            dir = "BWD";
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), player.b2body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){

            player.b2body.applyLinearImpulse(new Vector2(0,4f), player.b2body.getWorldCenter(),true);

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S) && duck == false){
            player.shape.setAsBox(5/Main.PPM,5/Main.PPM);
            player.height = 5f;
            duck = true;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S) && duck == true){
            player.shape.setAsBox(5/Main.PPM,10/Main.PPM);
            duck = false;
           player.height = 10f;
        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            projectile = new Batarang(world,this);
            projectile.shoot(dir, player.b2body);


        }



    }

    public void update(float dt){
        handleInput(dt);
        world.step(1f/60f,6,2);
        player.update(dt);

        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        // update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world,gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        projectile.draw(game.batch);
        game.batch.end();

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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
