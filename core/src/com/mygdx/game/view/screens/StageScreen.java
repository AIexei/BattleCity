package com.mygdx.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGame;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.model.DigitsImages;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Алексей on 14.05.2016.
 */
public class StageScreen extends AbstractScreen {
    private Texture texture;
    private ShapeRenderer renderer;
    private SpriteBatch batch;
    private MyGame game;

    private int stage;
    private int y1;
    private int y2;
    private boolean canMove;


    public StageScreen(MyGame game, int stage) {
        this.game = game;
        this.game.curScreen(this);
        this.stage = stage;
    }


    @Override
    public void show() {
        texture = new Texture("text/tbStage.png");
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        y1 = 0;
        y2 = 340;
        canMove = false;

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                canMove = true;
            }
        }, 1500);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GRAY);
        renderer.rect(0, y1, 800, 340);
        renderer.rect(0, y2, 800, 340);
        renderer.end();


        if (!canMove) {
            batch.begin();
            batch.draw(texture, 310, 327);
            batch.draw(DigitsImages.getBlackDigit(stage + 1), 450, 327);
            batch.end();
        }

        if (canMove) {
            y1 -= 10;
            y2 += 10;
        }

        if (y2 > 680) {
            game.setScreen(new GameScreen(game, stage));
        }

        //if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
        //    game.setScreen(new GameScreen(game, stage));
        //}
    }

    @Override
    public void dispose() {
        try {
            GameController.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
