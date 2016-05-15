package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Алексей on 15.05.2016.
 */

public class GameOverView {
    private static Texture gameOver;

    private static int y;


    public static void create() {
        y = -100;
        gameOver = new Texture("other/gameOver.png");
    }


    public static void draw(SpriteBatch batch) {
        batch.draw(gameOver, 250, y);

        if (y < 303) {
            y += 5;
        }
    }
}
