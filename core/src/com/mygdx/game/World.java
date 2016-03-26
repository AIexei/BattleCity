package com.mygdx.game;

import java.util.ArrayList;

/**
 * Created by Алексей on 01.03.2016.
 */


public class World {
    private ArrayList<Tank> list;
    private static byte[][] field;

    public World(byte[][] field, ArrayList<Tank> list) {
        this.field = field;
        this.list = list;
    }

    public void update() {
        for (int i = 0; i < list.size(); i++) {
            float x = list.get(i).getX();
            float y = list.get(i).getY();
        }
    }

    public static boolean canDrive(float x, float y, int dir) {
        int xx = (int) x / 25;
        int yy = (int) y / 25;

        if (field[xx][yy] == 0) {
            switch (dir) {
                case 0:
                case 2:
                    if (field[xx + 1][yy] > 0)
                        return false;
                    break;

                case 1:
                case 3:
                    if (field[xx][yy + 1] > 0)
                        return false;
                    break;
            }

            return true;
        }

        return false;
    }

    public int getValue(int x, int y) {
        return field[x][y];
    }

    public int getSize() {
        return field[0].length;
    }
}

