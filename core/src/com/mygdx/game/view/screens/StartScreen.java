package com.mygdx.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGame;

/**
 * Created by Алексей on 14.05.2016.
 */

public class StartScreen extends AbstractScreen {
    private MyGame game;
    private Texture menu;
    private Texture tNewGame;
    private Texture tContinue;
    private Texture tBest;
    private Texture tExit;
    private Texture choise;
    private SpriteBatch batch;

    private int y;


    public StartScreen(MyGame game) {
        this.game = game;
        this.game.curScreen(this);
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        choise = new Texture("other/menuChoise.png");
        tNewGame = new Texture("text/newGame.png");
        tContinue = new Texture("text/continue.png");
        tBest = new Texture("text/tBest.png");
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
        batch.draw(choise, 290, 250+y);
        batch.draw(tNewGame, 350, 255+y);
        batch.draw(tContinue, 350, 195+y);
        batch.draw(tBest, 350, 135+y);
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
