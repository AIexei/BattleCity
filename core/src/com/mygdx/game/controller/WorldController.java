package com.mygdx.game.controller;

import com.mygdx.game.model.Shell;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by ������� on 01.03.2016.
 */


public class WorldController {
    private static byte[][] field;
    private static float border;
    private static boolean homeDef;
    private static boolean isEnd;

    private static Set<Byte> indestruct;


    public static void create(byte[][] f, float b) {
        field = f;
        border = b;
        homeDef = false;
        isEnd = false;

        indestruct = new HashSet<Byte>();
        indestruct.add((byte) 1);
        indestruct.add((byte) 2);
        indestruct.add((byte) 4);
        indestruct.add((byte) 5);
    }

    /**
     * Check the possibility to movement to the next cell
     *
     * @param x : future �
     * @param y : future �
     * @param dir : 0, 2 - �, 1,3 - X
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

        if (field[yy][xx] == 5) {
            isEnd = true;
        } else if (!indestruct.contains(field[yy][xx]))
            field[yy][xx] = 0;

        switch (dir) {
            case 0:
            case 2:
                if (!indestruct.contains(field[yy][xx+1]))
                    field[yy][xx + 1] = 0;
                break;

            case 1:
            case 3:
                if (!indestruct.contains(field[yy+1][xx]))
                    field[yy + 1][xx] = 0;
                break;
        }
    }


    public static void homeDefence(boolean value) {
        if (value != homeDef) {
            homeDef = value;

            byte filler = 3;
            if (homeDef) {
                filler = 4;
            }

            field[0][11] = filler;
            field[1][11] = filler;
            field[2][11] = filler;
            field[0][14] = filler;
            field[1][14] = filler;
            field[2][14] = filler;
            field[2][12] = filler;
            field[2][13] = filler;
        }
    }


    public static int getFieldCell(int yy, int xx) {
        return field[yy][xx];
    }


    public static boolean isEnd() {
        return isEnd;
    }


    public static float getBorder() {
        return border;
    }
}

