package com.mygdx.game.controller.II;

import com.mygdx.game.controller.TanksController;
import com.mygdx.game.model.Tank;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Алексей on 03.04.2016.
 */

public class IIPlayer {
    private static LinkedList<Tank> tanksOnMap = new LinkedList<Tank>();
    private static Timer timer = new Timer();

    private static int curTanksCount = 0;
    private static int tanksLeftCount = 16;
    private static int prevAppearancePoint = 0;

    private static boolean createTank = false;
    private static boolean stopPowerup = false;


    public static void create() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                createTank = true;
            }
        }, 0, 5000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < curTanksCount; i++) {
                    IITankMovement.changeDir(tanksOnMap.get(i));
                }
            }
        }, 0, 3000);
    }


    public static void actions() {
        tanksOnMap = TanksController.getEnemies();
        curTanksCount = TanksController.getEnemies().size();

        if (!stopPowerup) {
            for (int i = 0; i < curTanksCount; i++) {
                IITankMovement.move(tanksOnMap.get(i));
            }
        }

        if (createTank) {
            newTank();
            createTank = false;
        }
    }


    public static void setStopPowerup(boolean value) {
        stopPowerup = value;
    }


    public static void decTanksCount() {
        tanksLeftCount--;
    }


    public static void newTank() {
        if (curTanksCount < 4) {
            for (int i = (prevAppearancePoint+1) % 3; i != prevAppearancePoint; i++, i %= 3) {
                if (canAppearOnPoint(i)) {
                    switch (i) {
                        case 0:
                            TanksController.addTank(TanksGenerator.createTank(0));
                            prevAppearancePoint = 0;
                            return;
                        case 1:
                            TanksController.addTank(TanksGenerator.createTank(1));
                            prevAppearancePoint = 1;
                            return;
                        case 2:
                            TanksController.addTank(TanksGenerator.createTank(2));
                            prevAppearancePoint = 2;
                            return;
                    }
                }
            }
        }
    }


    private static boolean canAppearOnPoint (int pointNum) {
        switch (pointNum) {
            case 0:
                if (!TanksController.isEmptyMapCell(1, 24)) {
                    return false;
                }

                break;
            case 1:
                if ((!TanksController.isEmptyMapCell(12, 24)) ||
                    (!TanksController.isEmptyMapCell(13, 24))) {
                    return false;
                }
                break;
            case 2:
                if (!TanksController.isEmptyMapCell(24, 24)) {
                    return false;
                }

                break;
        }

        return true;
    }
}