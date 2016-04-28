package com.mygdx.game.controller.II;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.Tank;

/**
 * Created by Алексей on 19.04.2016.
 */

public class TanksGenerator {
    private static Texture[] myTextures;
    private static Texture[] enemyTextures;
    private static Texture t;


    public static void create() {
        t = new Texture("enemy1.png");
        //myTextures = new Texture[3];
        //enemyTextures = new Texture[3];

        //myTextures[0] = new Texture("tank1.png");
        //myTextures[1] = new Texture("tank2.png");
        //myTextures[2] = new Texture("tank3.png");
        //enemyTextures[0] = new Texture("enemy1.png");
        //enemyTextures[1] = new Texture("enemy2.png");
        //enemyTextures[2] = new Texture("enemy3.png");
    }

    public static Tank createTank(int point, int level, boolean isMy) {
        int x = 0;
        float speed = 2.5f;
        Texture image;

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

        if (isMy) {
            //image = myTextures[level];
            image = t;
        } else {
            image = t;
            //image = enemyTextures[level];
            if (level == 1)
                speed = 5f;
        }

        return new Tank(x, 600, 180, speed, image);
    }
}
