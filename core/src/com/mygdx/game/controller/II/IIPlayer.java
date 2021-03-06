package com.mygdx.game.controller.II;

import com.mygdx.game.controller.AnimationsController;
import com.mygdx.game.controller.TanksController;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.anima.AnimImages;
import com.mygdx.game.model.anima.Animation;
import com.mygdx.game.view.GameInfoView;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Алексей on 03.04.2016.
 */

public class IIPlayer {
    private static LinkedList<Tank> tanksOnMap = new LinkedList<Tank>();
    private static Timer timer;

    private static int curTanksCount;
    private static int tanksLeftCount;
    private static int prevAppearancePoint;

    private static boolean createTank;
    private static boolean stopPowerup;
    private static boolean stopActions;


    public static void create() {
        createTank = false;
        stopPowerup = false;
        stopActions = false;

        curTanksCount = 0;
        tanksLeftCount = 12;
        prevAppearancePoint = 0;

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                createTank = true;
            }
        }, 0, 1500);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < curTanksCount; i++) {
                    IITankMovement.changeDir(tanksOnMap.get(i));
                }
            }
        }, 0, 4000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < curTanksCount; i++) {
                    HashSet<Integer> goodDirs = IITankMovement.goodDirs(tanksOnMap.get(i));
                    int curDir = tanksOnMap.get(i).getDir();

                    if (!goodDirs.contains(curDir)) {
                        IITankMovement.changeDir(tanksOnMap.get(i));
                    }
                }
            }
        }, 2000, 4000);
    }


    public static void actions() {
        if (!stopActions) {
            GameInfoView.setTanksLeft(tanksLeftCount);

            tanksOnMap = TanksController.getEnemies();
            curTanksCount = TanksController.getEnemies().size();

            if (!stopPowerup) {
                for (int i = 0; i < curTanksCount; i++) {
                    IITankMovement.move(tanksOnMap.get(i));
                }
            }

            if (createTank) {
                if (tanksLeftCount > 0) {
                    newTank();
                    createTank = false;
                }
            }
        }
    }


    public static int getTanksLeftCount() {
        return tanksLeftCount;
    }


    public static void setStopPowerup(boolean value) {
        stopPowerup = value;
    }


    public static void decTanksCount() {
        tanksLeftCount--;
    }


    public static void setStopActions(boolean value) {
        stopActions = value;
    }


    private static void newTank() {
        if ((curTanksCount < 4) && (tanksLeftCount - curTanksCount > 0)){
            for (int i = (prevAppearancePoint+1) % 3; i != prevAppearancePoint; i++, i %= 3) {
                if (canAppearOnPoint(i)) {
                    switch (i) {
                        case 0:
                            AnimationsController.add(new Animation(AnimImages.getBirth(), 4, 4f, 0, 600));
                            prevAppearancePoint = 0;

                            (new Timer()).schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    TanksController.addTank(TanksGenerator.createTank(0));
                                }
                            }, 500);

                            return;
                        case 1:
                            AnimationsController.add(new Animation(AnimImages.getBirth(), 4, 4f, 300, 600));
                            prevAppearancePoint = 1;

                            (new Timer()).schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    TanksController.addTank(TanksGenerator.createTank(1));
                                }
                            }, 500);

                            return;
                        case 2:
                            AnimationsController.add(new Animation(AnimImages.getBirth(), 4, 4f, 600, 600));
                            prevAppearancePoint = 2;

                            (new Timer()).schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    TanksController.addTank(TanksGenerator.createTank(2));
                                }
                            }, 500);

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


    public static void dispose() {
        timer.cancel();
        timer.purge();
    }
}