package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Алексей on 01.03.2016.
 */


public class World {
    private static ArrayList<Tank> tanks;
    private static byte[][] field;
    private static Tank[][] tanksMap;
    private static float border;


    public World(byte[][] field, ArrayList<Tank> tanks, float border) {
        this.field = field;
        this.tanksMap = new Tank[field.length][field.length];
        this.tanks = tanks;
        this.border = border;
    }

    public static void drawTanks(SpriteBatch batch) {
        for (int i = 0; i < tanks.size(); i++)
            tanks.get(i).draw(batch);
    }

    public static void drawShells(SpriteBatch batch) {
        for (int i = 0; i < tanks.size(); i++)
            tanks.get(i).drawShells(batch);

        // жизнь пули не должна зависеть от жизни танка
    }

    public static void update() {
        for (int i = 0; i < tanksMap.length; i++) {
            for (int j = 0; j < tanksMap.length; j++) {
                tanksMap[i][j] = null;
            }
        }

        for (int i = 0; i < tanks.size(); i++) {
            int xx = (int) tanks.get(i).getX() / 25;
            int yy = (int) tanks.get(i).getY() / 25;
            byte accessory = tanks.get(i).getAccessory();

            tanksMap[yy][xx] = tanks.get(i);
            tanksMap[yy+1][xx+1] = tanks.get(i);
            tanksMap[yy+1][xx] = tanks.get(i);
            tanksMap[yy][xx+1] = tanks.get(i);
        }
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

    public static boolean notCollisionWithTank(float x, float y, int dir, Object obj) {
        int xx = (int) x / 25;
        int yy = (int) y / 25;
        boolean isShell = Shell.class.getClass().equals(obj.getClass());
        Tank temp = tanksMap[yy][xx];

        if (temp != null) {
            if (!temp.equals(obj)) {
                if (isShell)
                    killTank();

                return false;
            }
        }

        switch (dir) {
            case 0:
            case 2:
                temp = tanksMap[yy][xx + 1];

                if (temp != null) {
                    if (!temp.equals(obj)) {
                        if (isShell)
                            killTank();

                        return false;
                    }
                }

                break;

            case 1:
            case 3:
                temp = tanksMap[yy + 1][xx];

                if (temp != null){
                    if (!temp.equals(obj)) {
                        if (isShell)
                            killTank();

                        return false;
                    }
                }

                break;
        }

        return true;
    }

    public static float getBorder() {
        return border;
    }

    private static void killTank() {

    }
}

