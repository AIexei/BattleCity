package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Алексей on 14.05.2016.
 */
public class StageScreen extends AbstractScreen {
    private ShapeRenderer renderer;
    private Game game;

    private int y1;
    private int y2;
    private boolean canMove;


    public StageScreen(Game game) {
        this.game = game;
        System.out.println("stage");
    }


    @Override
    public void show() {
        renderer = new ShapeRenderer();

        y1 = 0;
        y2 = 340;
        canMove = false;

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                canMove = true;
            }
        }, 3000);
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

        if (canMove) {
            y1 -= 10;
            y2 += 10;
        }

        if (y2 > 680) {
            game.setScreen(new GameScreen());
        }
    }
}
