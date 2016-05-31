package com.mygdx.game.controller.II;

import com.mygdx.game.controller.WorldController;
import com.mygdx.game.model.Tank;

import java.util.*;

/**
 * Created by ������� on 19.04.2016.
 */

public class IITankMovement {
    private static int prevRandom = -1;


    public static void move(Tank tank) {
        MoveController.move(tank, tank.getDir());
        tank.fire();
    }


    public static void changeDir(Tank tank) {
        HashSet<Integer> dirs = goodDirs(tank);
        int result = -1;

        if (dirs.size() > 0) {
            while (!dirs.contains(result)) {
                result = randomDir();
            }

            tank.setDir(result);
        }
    }


    private static int randomDir() {
        int value = (new Random()).nextInt(100);
        int curRandom;

        if (value < 15) {
            curRandom = 0;
        } else if (value < 40) {
            curRandom = 1;
        } else if (value < 65) {
            curRandom = 3;
        } else {
            curRandom = 2;
        }

        if (prevRandom == curRandom) {
            return randomDir();
        } else {
            prevRandom = curRandom;
            return curRandom;
        }
    }


    public static HashSet<Integer> goodDirs(Tank tank) {
        HashSet<Integer> result = new HashSet<Integer>();
        result.add(0);
        result.add(1);
        result.add(2);
        result.add(3);

        int prevDir = tank.getDir();
        int xx = (int) tank.getX()/25;
        int yy = (int) tank.getY()/25;

        result.remove((prevDir + 2) % 4);

        if (xx == 24) {
            result.remove(1);
        } else if (WorldController.getFieldCell(yy, xx + 2) == 4) {
            result.remove(1);
        }

        if (xx == 0) {
            result.remove(3);
        } else if (WorldController.getFieldCell(yy, xx - 1) == 4) {
            result.remove(3);
        }

        if (yy == 0) {
            result.remove(2);
        } else if (WorldController.getFieldCell(yy - 1, xx) == 4) {
            result.remove(2);
        }

        if (yy == 24) {
            result.remove(0);
        } else if (WorldController.getFieldCell(yy + 2, xx) == 4) {
            result.remove(0);
        }

        return result;
    }
}
