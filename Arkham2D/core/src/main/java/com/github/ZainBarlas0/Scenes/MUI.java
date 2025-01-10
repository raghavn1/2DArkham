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

public class MUI {
    public Stage stage;
    private Viewport viewport;

    Label Title;
    Label Enter;

    public MUI(SpriteBatch sb){

        viewport = new FitViewport(Main.V_WIDTH,Main.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Title = new Label("Arkham:2D", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Title.setFontScale(3,3);
        Enter = new Label("Enter to play", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Enter.setFontScale(3,3);
        table.add(Title).center();
        table.row();
        table.add(Enter).bottom().expand(0,-10);
        stage.addActor(table);

    }
}
