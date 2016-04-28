package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Shell;
import com.mygdx.game.model.Tank;

import java.util.LinkedList;

/**
 * Created by Алексей on 01.03.2016.
 */


public class WorldController {
    private static byte[][] field;
    private static float border;


    public WorldController(byte[][] field, float border) {
        this.field = field;
        this.border = border;
    }


    /**
     * Check the possibility to movement to the next cell
     *
     * @param x : future Х
     * @param y : future У
     * @param dir : 0, 2 - У, 1,3 - X
     * @return : can you take this position
     */
    public static boolean canMove(float x, float y, int dir, Object obj) {
        int xx = (int) x / 25;
        int yy = (int) y / 25;
        int limitation = 1;

        if (Shell.class.getName() == obj.getClass().getName()) {
            limitation = 2;
        }

        if (field[yy][xx] <= limitation) {
            switch (dir) {
                case 0:
                case 2:
                    if (field[yy][xx + 1] > limitation) {
                        return false;
                    }

                    break;

                case 1:
                case 3:
                    if (field[yy + 1][xx] > limitation) {
                        return false;
                    }

                    break;
            }
            return true;
        }

        return false;
    }

    public static void destruction (float x, float y, int dir) {
        int xx = (int) x / 25;
        int yy = (int) y / 25;

        if (field[yy][xx] != 4)
            field[yy][xx] = 0;

        switch (dir) {
            case 0:
            case 2:
                if (field[yy][xx + 1] != 4)
                    field[yy][xx + 1] = 0;
                break;

            case 1:
            case 3:
                if (field[yy + 1][xx] != 4)
                    field[yy + 1][xx] = 0;
                break;
        }
    }


    public static void homeDefence() {
        //...
    }


    public static float getBorder() {
        return border;
    }
}

