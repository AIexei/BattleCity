package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Алексей on 14.05.2016.
 */

public class StartScreen extends AbstractScreen {
    private Game game;
    private Texture menu;
    private SpriteBatch batch;

    private int y;


    public StartScreen(Game game) {
        this.game = game;
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        menu = new Texture("other/menu.png");
        y = -680;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.begin();
        batch.draw(menu, 0, y);
        batch.end();

        y += 7;

        if (y > 0) {
            game.setScreen(new MenuScreen(game));
        }
    }
}
