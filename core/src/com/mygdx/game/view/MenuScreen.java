package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Алексей on 14.05.2016.
 */

public class MenuScreen extends AbstractScreen {
    private Game game;
    private Texture menu;
    private Texture tPlay;
    private Texture tBest;
    private Texture tAbout;
    private Texture tExit;
    private Sprite tChoise;
    private SpriteBatch batch;

    private int position;
    private boolean timeout;


    public MenuScreen(Game game) {
        this.game = game;
        System.out.println("menu");
    }


    @Override
    public void show() {
        System.out.println("shsow");
        batch = new SpriteBatch();

        tPlay = new Texture("text/tPlay.png");
        tBest = new Texture("text/tBest.png");
        tAbout = new Texture("text/tAbout.png");
        tExit = new Texture("text/pExit.png");
        menu = new Texture("other/menu.png");

        tChoise = new Sprite(new Texture("tanks/tank1.png"));
        tChoise.setRotation(270);
        tChoise.setX(300);
        tChoise.setY(255);

        timeout = false;
        position = 0;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.begin();

        batch.draw(menu, 0, 0);
        batch.draw(tPlay, 350, 255);
        batch.draw(tBest, 350, 195);
        batch.draw(tAbout, 350, 135);
        batch.draw(tExit, 350, 75);

        tChoise.draw(batch);
        batch.end();

        if (!timeout)
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            switch (position) {
                case 0:
                    game.setScreen(new StageScreen(game));
                    break;
                case 1:
                    game.setScreen(new StartScreen(game));
                    break;
                case 2:
                    break;
                case 3:
                    menu.dispose();
                    tPlay.dispose();
                    tBest.dispose();
                    tAbout.dispose();
                    tExit.dispose();

                    System.exit(0);
                    break;
            }

            timeout = true;
            (new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    timeout = false;
                }
            }, 200);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (position > 0) {
                position--;
                tChoise.setY(tChoise.getY() + 60);

                timeout = true;
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        timeout = false;
                    }
                }, 200);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (position < 3) {
                position++;
                tChoise.setY(tChoise.getY() - 60);

                timeout = true;
                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        timeout = false;
                    }
                }, 200);
            }
        }
    }
}

