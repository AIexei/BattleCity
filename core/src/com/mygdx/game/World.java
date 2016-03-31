package com.mygdx.game;

import java.util.ArrayList;

/**
 * Created by Алексей on 01.03.2016.
 */


public class World {
    private ArrayList<Tank> enemies;
    private static byte[][] field;
    private static float border;

    public World(byte[][] field, ArrayList<Tank> enemies, float border) {
        this.field = field;
        this.enemies = enemies;
        this.border = border;
    }

    public void update() {
        for (int i = 0; i < enemies.size(); i++) {
            float x = enemies.get(i).getX();
            float y = enemies.get(i).getY();
        }
    }

    /**
     * @param x : future Х
     * @param y : future У
     * @param dir : 0, 2 - У, 1,3 - X
     * @return : can you take this position
     */
    public static boolean canMove(float x, float y, int dir, ObjType type) {
        int xx = (int) x / 25;
        int yy = (int) y / 25;
        int limitation = 1;

        if (type == ObjType.SHELL) {
            limitation = 2;
        }

        if (field[yy][xx] <= limitation) {
            switch (dir) {
                case 0:
                case 2:
                    if (field[yy][xx + 1] > limitation)
                        return false;
                    break;

                case 1:
                case 3:
                    if (field[yy + 1][xx] > limitation)
                        return false;
                    break;
            }

            return true;
        }

        return false;
    }


    public static void destruction (float x, float y, int dir) {
        int xx = (int) x / 25;
        int yy = (int) y / 25;

        field[yy][xx] = 0;

        switch (dir) {
            case 0:
            case 2:
                field[yy][xx + 1] = 0;
                break;

            case 1:
            case 3:
                field[yy + 1][xx] = 0;
                break;
        }
    }

    public static float getBorder() {
        return border;
    }
}

