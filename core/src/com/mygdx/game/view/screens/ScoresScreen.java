package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.DigitsImages;

/**
 * Created by Алексей on 27.05.2016.
 */

public class ScoresScreen extends AbstractScreen {
    private MyGame game;
    private SpriteBatch batch;

    private Texture tHighScores;

    private int[] scores;
    private int x;


    public ScoresScreen(MyGame game) {
        this.game = game;
    }


    @Override
    public void show() {
        scores = GameController.getHighScores();
        batch = new SpriteBatch();
        x = 280;
        tHighScores = new Texture("text/tHS.png");
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.begin();
        batch.draw(tHighScores, 280, 550);

        DigitsImages.drawNumber(batch, 1, x, 430);
        DigitsImages.drawNumber(batch, 2, x, 350);
        DigitsImages.drawNumber(batch, 3, x, 270);
        DigitsImages.drawNumber(batch, 4, x, 190);
        DigitsImages.drawNumber(batch, 5, x, 110);

        DigitsImages.drawMinus(batch, x + 40, 430);
        DigitsImages.drawMinus(batch, x + 40, 350);
        DigitsImages.drawMinus(batch, x + 40, 270);
        DigitsImages.drawMinus(batch, x + 40, 190);
        DigitsImages.drawMinus(batch, x + 40, 110);

        DigitsImages.drawNumber(batch, scores[0], x + 80, 430);
        DigitsImages.drawNumber(batch, scores[1], x + 80, 350);
        DigitsImages.drawNumber(batch, scores[2], x + 80, 270);
        DigitsImages.drawNumber(batch, scores[3], x + 80, 190);
        DigitsImages.drawNumber(batch, scores[4], x + 80, 110);

        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            game.setScreen(new MenuScreen(game));
        }
    }


    @Override
    public void dispose() {
        tHighScores.dispose();
        batch.dispose();
    }
}
