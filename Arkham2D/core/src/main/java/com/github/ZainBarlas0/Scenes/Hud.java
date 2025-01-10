package com.github.ZainBarlas0.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.ZainBarlas0.Main;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private int score;

    Label scoreLabel;
    Label Level;

    public Hud(SpriteBatch sb){
        score = 0;

        viewport = new FitViewport(Main.V_WIDTH,Main.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(scoreLabel).top();
        stage.addActor(table);

    }


}
