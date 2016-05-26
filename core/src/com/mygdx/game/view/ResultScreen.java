package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.DigitsImages;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Алексей on 14.05.2016.
 */
public class ResultScreen extends AbstractScreen {
    private Game game;
    private SpriteBatch batch;

    private Texture tStage;
    private Texture tPts;
    private Texture tTotal;
    private Texture enemy1;
    private Texture enemy2;
    private Texture enemy3;
    private Texture enemy4;

    private boolean canDraw;
    private int curResult;


    public ResultScreen(Game game) {
        this.game = game;
    }


    @Override
    public void show() {
        batch = new SpriteBatch();

        tStage = new Texture("text/twStage.png");
        tPts = new Texture("text/tPts.png");
        tTotal = new Texture("text/tTotal.png");

        enemy1 = new Texture("tanks/enemy1.png");
        enemy2 = new Texture("tanks/enemy2.png");
        enemy3 = new Texture("tanks/enemy3.png");
        enemy4 = new Texture("tanks/enemy4.png");

        curResult = -1;

        newTimer();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.begin();
        batch.draw(tStage, 310, 550);

        DigitsImages.drawNumber(batch, 1, 450, 550);

        if (curResult >= 0) {
            DigitsImages.drawMinus(batch, 470, 465);
            DigitsImages.drawNumber(batch,5, 420, 465);
            DigitsImages.drawNumber(batch, 1234, 200, 465);
            batch.draw(enemy1, 500, 450);
            batch.draw(tPts, 300, 465);
        }

        if (curResult >= 1) {
            DigitsImages.drawMinus(batch, 470, 365);
            DigitsImages.drawNumber(batch,5, 420, 365);
            DigitsImages.drawNumber(batch, 1234, 200, 365);
            batch.draw(enemy2, 500, 350);
            batch.draw(tPts, 300, 365);
        }

        if (curResult >= 2) {
            DigitsImages.drawMinus(batch, 470, 265);
            DigitsImages.drawNumber(batch,5, 420, 265);
            DigitsImages.drawNumber(batch, 1234, 200, 265);
            batch.draw(enemy4, 500, 250);
            batch.draw(tPts, 300, 265);
        }

        if (curResult >= 3) {
            DigitsImages.drawMinus(batch, 470, 165);
            DigitsImages.drawNumber(batch,5, 420, 165);
            DigitsImages.drawNumber(batch, 1234, 200, 165);
            batch.draw(enemy3, 500, 150);
            batch.draw(tPts, 300, 165);
        }

        if (curResult >= 4) {
            batch.draw(tTotal, 200, 65);
        }

        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {

        }
    }

    private void newTimer() {
        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                if (curResult <= 4) {
                    curResult++;
                    newTimer();
                }
            }
        }, 500);
    }
}
