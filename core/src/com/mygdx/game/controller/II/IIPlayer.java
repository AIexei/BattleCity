package com.mygdx.game.controller.II;

import com.mygdx.game.controller.TanksController;
import com.mygdx.game.controller.WorldController;
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


    public static void decTanksCount() {
        tanksLeftCount--;
    }


    private static void update() {
        tanksOnMap = TanksController.getTanks();
    }


    private static void newTank() {
        if (curTanksCount < 4) {
            for (int i = prevAppearancePoint+1; i != prevAppearancePoint; i++, i %= 3) {
                if (canAppearOnPoint(i)) {
                    switch (i) {
                        case 0:
                            //TanksController.addTank();
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                    }
                }
            }
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


    private static boolean canAppearOnPoint (int pointNum) {
        switch (pointNum) {
            case 0:
                if (TanksController.getMapCell(1, 24) != null) {
                    return false;
                }

                break;
            case 1:
                if ((TanksController.getMapCell(12, 24) != null) ||
                    (TanksController.getMapCell(13, 24) != null)) {
                    return false;
                }
                break;
            case 2:
                if (TanksController.getMapCell(24, 24) != null) {
                    return false;
                }

                break;
        }

        return true;
    }
}
