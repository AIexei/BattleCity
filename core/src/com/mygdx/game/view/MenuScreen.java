package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by Алексей on 14.05.2016.
 */

public class MenuScreen extends AbstractScreen {
    private Game game;
    private Texture menu;
    private SpriteBatch batch;

    private int position;


    public MenuScreen(Game game) {
        this.game = game;
        System.out.println("menu");
    }


    @Override
    public void show() {
        System.out.println("shsow");
        batch = new SpriteBatch();
        menu = new Texture("other/menu.png");
        position = 0;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.begin();
        batch.draw(menu, 0, 0);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {
            game.setScreen(new StageScreen(game));
        }
    }
}

