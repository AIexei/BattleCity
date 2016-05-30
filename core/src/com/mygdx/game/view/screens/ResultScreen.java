package com.mygdx.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.DigitsImages;
import com.sun.media.jfxmediaimpl.platform.gstreamer.GSTAudioEqualizer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Алексей on 14.05.2016.
 */
public class ResultScreen extends AbstractScreen {
    private MyGame game;
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
    private int[] killed;


    public ResultScreen(MyGame game) {
        this.game = game;
        this.game.curScreen(this);
        this.killed = GameController.getKilled();
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

        DigitsImages.drawNumber(batch, GameController.getStage() + 1, 450, 550);

        if (curResult >= 0) {
            int x = killed[0];
            int res = x * 100;

            DigitsImages.drawMinus(batch, 470, 465);
            DigitsImages.drawNumber(batch, x, 420, 465);
            DigitsImages.drawNumber(batch, res, 200, 465);
            batch.draw(enemy1, 500, 450);
            batch.draw(tPts, 300, 465);
        }

        if (curResult >= 1) {
            int x = killed[1];
            int res = x * 200;

            DigitsImages.drawMinus(batch, 470, 365);
            DigitsImages.drawNumber(batch, x, 420, 365);
            DigitsImages.drawNumber(batch, res, 200, 365);
            batch.draw(enemy2, 500, 350);
            batch.draw(tPts, 300, 365);
        }

        if (curResult >= 2) {
            int x = killed[2];
            int res = x * 200;

            DigitsImages.drawMinus(batch, 470, 265);
            DigitsImages.drawNumber(batch, x, 420, 265);
            DigitsImages.drawNumber(batch, res, 200, 265);
            batch.draw(enemy4, 500, 250);
            batch.draw(tPts, 300, 265);
        }

        if (curResult >= 3) {
            int x = killed[3];
            int res = x * 300;

            DigitsImages.drawMinus(batch, 470, 165);
            DigitsImages.drawNumber(batch, x, 420, 165);
            DigitsImages.drawNumber(batch, res, 200, 165);
            batch.draw(enemy3, 500, 150);
            batch.draw(tPts, 300, 165);
        }

        if (curResult >= 4) {
            DigitsImages.drawNumber(batch, GameController.getScore(), 400, 65);
            batch.draw(tTotal, 200, 65);
        }

        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            try {
                setScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void hide() {
        System.out.println("serialize");
        try {
            if (!(GameController.isEnd()) && (GameController.getStage() < 7)) {
                GameController.incStage();
                GameController.serialize();

            } else {
                GameController.clear();
                GameController.serialize();
            }
        } catch (Exception e)  {
            e.printStackTrace();
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


    private void setScreen() throws Exception {
        if (!(GameController.isEnd()) && (GameController.getStage() < 7)){
            GameController.incStage();
            GameController.clearKilled();
            GameController.serialize();
            game.setScreen(new StageScreen(game, GameController.getStage()));
        } else {
            try {
                GameController.clear();
                GameController.serialize();
                game.setScreen(new MenuScreen(game));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
