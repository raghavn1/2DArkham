package com.github.raghavn1.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.raghavn1.Main;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    public static Integer worldTimer;
    public static float timeCount;
    public static Integer score;

    static Label countdownLabel;
    private static Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label batmanLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 30;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(Main.V_WIDTH,Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("Level: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        batmanLabel = new Label("BATMAN!", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(batmanLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);

        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);

    }

    public static void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value){ //Add this method "addScore(scorenumber)" to any interactive tile object to change the score
        score += value;
        scoreLabel.setText(String.format("%04d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
