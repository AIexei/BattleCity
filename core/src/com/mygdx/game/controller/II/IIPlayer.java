package com.mygdx.game.controller.II;

import com.mygdx.game.model.Tank;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Алексей on 03.04.2016.
 */

public class IIPlayer {
    private static LinkedList<Tank> tanksOnMap = new LinkedList<>();

    private static int prevRandom = -1;
    private static int curTanksCount = 0;
    private static int tanksLeftCount = 16;
    private static int prevAppearancePoint = 0;


    private static void newTank() {
        if (curTanksCount < 4) {
            for (int i = prevAppearancePoint+1; i != prevAppearancePoint; i++, i %= 3) {
                if (canAppearOnPoint(i)) {
                   // tanksOnMap.add(new Tank())
                }
            }
        }
    }

    public static int randomDir() {
        int value = (new Random()).nextInt(100);
        int curRandom;

        if (value < 20) {
            curRandom = 0;
        } else if (value < 45) {
            curRandom = 1;
        } else if (value < 70) {
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

    /**
     * @param pointNum
     * @return
     */
    private static boolean canAppearOnPoint (int pointNum) {
        switch (pointNum) {
            case 0:

                break;
            case 1:
                break;
            case 2:
                break;
        }

        return true;
    }
}
