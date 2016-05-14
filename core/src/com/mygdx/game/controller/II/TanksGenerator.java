package com.mygdx.game.controller.II;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.Tank;

import java.util.Random;

/**
 * Created by Àëåêñåé on 19.04.2016.
 */

public class TanksGenerator {
    private static Texture[] myTextures;
    private static Texture[] enemyTextures;

    static {
        myTextures = new Texture[3];
        enemyTextures = new Texture[5];

        myTextures[0] = new Texture("tanks/tank1.png");
        myTextures[1] = new Texture("tanks/tank2.png");
        myTextures[2] = new Texture("tanks/tank3.png");
        enemyTextures[0] = new Texture("tanks/enemy1.png");
        enemyTextures[1] = new Texture("tanks/enemy2.png");
        enemyTextures[2] = new Texture("tanks/enemy3.png");
        enemyTextures[3] = new Texture("tanks/enemy33.png");
        enemyTextures[4] = new Texture("tanks/enemy4.png");
    }


    public static Tank createTank(int point) {
        int x = 0;
        int tankType = (new Random()).nextInt(4);

        switch (point) {
            case 0:
                x = 0;
                break;
            case 1:
                x = 300;
                break;
            case 2:
                x = 600;
                break;
        }

        switch (tankType) {
            case 0:
                return new Tank(x, 600, 1200, 0, 180, 2.5f, enemyTextures[0]);
            case 1:
                return new Tank(x, 600, 1600, 0, 180, 5f, enemyTextures[1]);
            case 2:
                return new Tank(x, 600, 1100, 0, 180, 2.5f, enemyTextures[4]);
            default:
                return new Tank(x, 600, 1400, 1, 180, 2.5f, enemyTextures[2]);
        }
    }


    public static Texture getTexture(int type, boolean isMy) {
        if (isMy) {
            return myTextures[type];
        } else {
            return enemyTextures[type];
        }
    }
}