package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.II.IIPlayer;
import com.mygdx.game.model.Shell;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.anima.AnimImages;
import com.mygdx.game.model.anima.Animation;

import java.util.LinkedList;

/**
 * Created by ������� on 14.04.2016.
 */

public class TanksController {
    private static LinkedList<Tank> tanks;
    private static Tank player;
    private static Tank[][] tanksMap;

    private static boolean endFlag = false;


    public TanksController(Tank player) {
        this.tanksMap = new Tank[26][26];
        this.tanks = new LinkedList<Tank>();
        this.player = player;
        this.tanks.add(player);
    }


    public static void drawTanks(SpriteBatch batch) {
        for (int i = 0; i < tanks.size(); i++)
            tanks.get(i).draw(batch);
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

            tanksMap[yy][xx] = tanks.get(i);
            tanksMap[yy+1][xx+1] = tanks.get(i);
            tanksMap[yy+1][xx] = tanks.get(i);
            tanksMap[yy][xx+1] = tanks.get(i);
        }
    }


    public static void addTank(Tank newTank) {
        tanks.add(newTank);
    }


    public static boolean notCollisionWithTank(float x, float y, int dir, Tank tank) {
        int xx = (int) x / 25;
        int yy = (int) y / 25;

        Tank temp = tanksMap[yy][xx];

        if ((temp != null) && (!tank.equals(temp))) {
            return false;
        }

        switch (dir) {
            case 0:
            case 2:
                temp = tanksMap[yy][xx + 1];

                if ((temp != null) && (!tank.equals(temp))) {
                    return false;
                }

                break;

            case 1:
            case 3:
                temp = tanksMap[yy + 1][xx];

                if ((temp != null) && (!tank.equals(temp))) {
                    return false;
                }

                break;
        }

        return true;
    }


    public static boolean notCollisionWithTank(float x, float y, int dir, Shell shell) {
        int xx = (int) x / 25;
        int yy = (int) y / 25;
        boolean isEnemy1 = !shell.getOwner().equals(player);
        boolean isEnemy2;
        Tank tank = shell.getOwner();

        Tank temp = tanksMap[yy][xx];

        if ((temp != null) && (!tank.equals(temp))) {
            isEnemy2 = !temp.equals(player);

            if (isEnemy1 != isEnemy2)
                return false;
        }

        switch (dir) {
            case 0:
            case 2:
                temp = tanksMap[yy][xx + 1];

                if ((temp != null) && (!tank.equals(temp))) {
                    isEnemy2 = !temp.equals(player);

                    if (isEnemy1 != isEnemy2)
                        return false;
                }

                break;

            case 1:
            case 3:
                temp = tanksMap[yy + 1][xx];

                if ((temp != null) && (!tank.equals(temp))) {
                    isEnemy2 = !temp.equals(player);

                    if (isEnemy1 != isEnemy2)
                        return false;
                }

                break;
        }

        return true;
    }


    public static void killTank(float x, float y, int dir, Tank tank) {
        int xx = (int) x / 25;
        int yy = (int) y / 25;

        Tank temp = tanksMap[yy][xx];

        if ((temp == null) || (tank.equals(temp))) {
            if (dir % 2 == 0) {
                temp = tanksMap[yy][xx + 1];
            } else {
                temp = tanksMap[yy + 1][xx];
            }
        }

        if (temp.equals(player)) {
            endFlag = true;
        } else {
            tanks.remove(temp);
            IIPlayer.decTanksCount();
        }
    }


    public static boolean isEnd() {
        return endFlag;
    }


    public static Tank getPlayer() {
        return player;
    }


    public static boolean isEmptyMapCell (int x, int y) {
        return (tanksMap[y][x] == null);
    }


    public static LinkedList<Tank> getEnemies() {
        LinkedList<Tank> result = new LinkedList<Tank>();

        for (int i = 1; i < tanks.size(); i++) {
            result.add(tanks.get(i));
        }

        return result;
    }


    public static void killAllTanks() {
        for (int i = 1; i < tanks.size(); i++) {
            float x = tanks.get(i).getX();
            float y = tanks.get(i).getY();

            AnimationsController.add(new Animation(AnimImages.getBigBang(), 2, 1.5f, x-25, y-25));
            IIPlayer.decTanksCount();
        }

        while (tanks.size() != 1) {
            tanks.remove(tanks.size()-1);
        }
    }
}
