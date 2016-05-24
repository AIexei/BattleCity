package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.I18NBundle;

/**
 * Created by Алексей on 14.05.2016.
 */

public class StartScreen extends AbstractScreen {
    private Game game;
    private Texture menu;
    private Texture tPlay;
    private Texture tBest;
    private Texture tAbout;
    private Texture tExit;
    private SpriteBatch batch;

    private int y;


    public StartScreen(Game game) {
        this.game = game;
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        tPlay = new Texture("text/tPlay.png");
        tBest = new Texture("text/tBest.png");
        tAbout = new Texture("text/tAbout.png");
        tExit = new Texture("text/pExit.png");
        menu = new Texture("other/menu.png");
        y = -680;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.begin();

        batch.draw(menu, 0, y);
        batch.draw(tPlay, 350, 255+y);
        batch.draw(tBest, 350, 195+y);
        batch.draw(tAbout, 350, 135+y);
        batch.draw(tExit, 350, 75+y);

        batch.end();

        y += 7;

        if (y > 0) {
            game.setScreen(new MenuScreen(game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new MenuScreen(game));
        }
    }
}
