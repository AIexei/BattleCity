package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Алексей on 14.05.2016.
 */
public class ResultScreen extends AbstractScreen {
    private Game game;
    private SpriteBatch batch;


    public ResultScreen(Game game) {
        this.game = game;
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
    }


    @Override
    public void render(float delta) {

    }
}
