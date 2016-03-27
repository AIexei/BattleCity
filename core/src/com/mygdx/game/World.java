package com.mygdx.game;

import java.util.ArrayList;

/**
 * Created by Алексей on 01.03.2016.
 */


public class World {
    private ArrayList<Tank> list;
    private static byte[][] field;
    private static float border;

    public World(byte[][] field, ArrayList<Tank> list, float border) {
        this.field = field;
        this.list = list;
        this.border = border;
    }

    public void update() {
        for (int i = 0; i < list.size(); i++) {
            float x = list.get(i).getX();
            float y = list.get(i).getY();
        }
    }

    /**
     * @param x : future Х
     * @param y : future У
     * @param dir : 0, 2 - У, 1,3 - X
     * @return : can you take this position
     */
    public static boolean canMove(float x, float y, int dir) {
        int xx = (int) x / 25;
        int yy = (int) y / 25;

        if (field[yy][xx] == 0) {
            switch (dir) {
                case 0:
                case 2:
                    if (field[yy][xx + 1] > 0)
                        return false;
                    break;

                case 1:
                case 3:
                    if (field[yy + 1][xx] > 0)
                        return false;
                    break;
            }

            return true;
        }

        return false;
    }

    public static float getBorder() {
        return border;
    }

    public int getSize() {
        return field[0].length;
    }
}

