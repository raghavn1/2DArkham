package com.github.raghavn1.Screens;

import static com.github.raghavn1.Sprites.MyContactlistener.removeList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.raghavn1.Main;
import com.github.raghavn1.Scenes.Hud;
import com.github.raghavn1.Sprites.Batarang;
import com.github.raghavn1.Sprites.Batman;
import com.github.raghavn1.Sprites.MyContactlistener;
import com.github.raghavn1.Tools.B2WorldCreator;
import com.github.raghavn1.Sprites.Joker;

import java.util.ArrayList;


public class PlayScreen implements Screen {
    private Main game;
    public  static boolean DESTROY = false;
    private TextureAtlas batmanAtlas;//Spritesheet texture atlas .pack file
    private TextureAtlas jokerAtlas;//Spritesheet texture atlas .pack file
    private OrthographicCamera gamecam;//Camera that is centered to the player position
    private Viewport gamePort; //View/window
    private Hud hud; //Heads up display
    //Map stuff
    private TmxMapLoader mapLoader; //Maploader
    private TiledMap map; //TiledMap
    private OrthogonalTiledMapRenderer renderer; //Map renderer
    private World world; //World
    private Batman player; //Sprite
    private Joker joker; //Sprite
    private Box2DDebugRenderer b2dr; //Shows debug lines around collidable objects
    private Batarang projectile; //Throwing sprite
    private String dir; //Direction string to see which way the player is facing to throw the batarang

    private Music music;

    private MyContactlistener contactlistener;

    public PlayScreen(Main game){
        contactlistener = new MyContactlistener();

        batmanAtlas = new TextureAtlas("batmanSprites.pack");
        jokerAtlas = new TextureAtlas("ThugSprite.pack");

        dir = "FWD";

        this.game = game;
        //Create a camera to make the character move through the cam world
        gamecam = new OrthographicCamera();
        //Create a fitViewport to keep the height and width ratio the same regardless of screen size
        gamePort = new FitViewport(Main.V_WIDTH / Main.PPM, Main.V_HEIGHT / Main.PPM, gamecam);
        //Creating a hud for  score/timer/level info
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("ExperimentMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Main.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,-10), true);
        world.setContactListener(contactlistener);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(this);

        //Create a new Batman player in the game world
        player = new Batman(this);
        //joker = new Joker(this);
        for(int i = 0 ; i<4; i++) {
            joker = new Joker(this);
        }

        music = Main.manager.get("audio/BatmanMusic.mp3", Music.class);
        music.setLooping(true);
        music.play();
    }

    public TextureAtlas getBatmanAtlas(){
        return batmanAtlas;
    }

    public TextureAtlas getJokerAtlas(){
        return jokerAtlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
//        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//            gamecam.position.x += 100 * dt;
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//            gamecam.position.x -= 100 * dt;
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
//            gamecam.position.y += 20 * dt;
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//            gamecam.position.y -= 20 * dt;
//        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            player.b2body.applyLinearImpulse(new Vector2(0,4f), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && player.b2body.getLinearVelocity().x <= 2){
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
            dir = "FWD";
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && player.b2body.getLinearVelocity().x >= -2){
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
            dir = "BWD";
        }//Change the Vector2 x and y numbers for each input to change how gravity affects the player and how far they move
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            projectile = new Batarang(world, this);
            projectile.shoot(dir, player.b2body);
        }

    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);

        if(contactlistener.removeList.size() > 0) {
            for (Body body : contactlistener.removeList) {
                world.destroyBody(body);
                joker.getTexture().dispose();
                contactlistener.removeList.remove(body);
            }
        }
        removeList.clear();

        player.update(dt);
        joker.update(dt);
        hud.update(dt);

        gamecam.position.x = player.b2body.getPosition().x;

        gamecam.update();
        renderer.setView(gamecam);
    }


    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Render the game map
        renderer.render();
        //Render the map with Box2DDebug Lines around the boxes
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        joker.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(Hud.worldTimer <= 0){
            game.setScreen(new EndScreen(game));
            music.stop();
            dispose();
        }
    }


    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
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
