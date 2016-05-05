package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.II.IIPlayer;
import com.mygdx.game.model.Tank;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ������� on 24.04.2016.
 */

public class PowerupsController {
    private static Timer timer;
    private static ArrayList<Texture> images;

    private static boolean timerRunning;

    private static int curPowerupId;
    private static int counter;

    private static float curX;
    private static float curY;


    static {
        timerRunning = false;
        curPowerupId = -1;
        counter = 0;

        timer = new Timer();
        images = new ArrayList<Texture>();
        images.add(new Texture("pups/grenadePup.png"));
        images.add(new Texture("pups/immortalityPup.png"));
        images.add(new Texture("pups/stopPup.png"));
        images.add(new Texture("pups/homePup.png"));
        images.add(new Texture("pups/levelPup.png"));
        images.add(new Texture("pups/pointsPup.png"));
    }


    public static void actions() {
        if (!timerRunning) {
            //System.out.println("timer");
            timerRunning = true;

            timer.purge();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    createPowerup();
                }
            }, (new Random()).nextInt(10000) + 10000);
        } else {
            if (curPowerupId != -1) {
                //System.out.println("checking");

                if (checkTaking()) {
                    executePowerup();
                }
            }
        }
    }


    public static void draw(SpriteBatch batch) {
        if (curPowerupId != -1) {
            counter = (counter + 1)%50;

            if (counter > 25) {
                batch.draw(images.get(curPowerupId), curX, curY);
            }
        } else {
            counter = 0;
        }
    }


    private static boolean checkTaking() {
        Tank p = TanksController.getPlayer();

        if ((Math.abs(curX - p.getX()) <= 25) && (Math.abs(curY - p.getY()) <= 25)) {
            return true;
        } else {
            return false;
        }
    }


    private static void createPowerup() {
        Tank p = TanksController.getPlayer();

        do {
            curY = (new Random()).nextInt(21) + 4;
            curX = (new Random()).nextInt(25);
            curY *= 25;
            curX *= 25;
        } while ((curX - p.getX() <= 50) && (curY - p.getY() <= 50));

        curPowerupId = (new Random()).nextInt(6);

        timer.purge();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(curPowerupId != -1) {
                    curPowerupId = -1;
                    timerRunning = false;
                }
            }
        }, 10000);
    }


    private static void executePowerup() {
        switch (curPowerupId) {
            case 0:
                grenade();
                break;
            case 1:
                immortality();
                break;
            case 2:
                stopTime();
                break;
            case 3:
                homeDefence();
                break;
            case 4:
                levelUp();
                break;
            case 5:
                points();
                break;
        }

        //System.out.println("EXE");
    }

    // OK
    // TanksController
    private static void grenade() {
        TanksController.killAllTanks();
        timerRunning = false;
        curPowerupId = -1;
    }

    // OK
    // TanksController
    private static void immortality() {
        curPowerupId = -1;
        TanksController.setPlayerImmortality(true);

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                TanksController.setPlayerImmortality(false);
                timerRunning = false;
            }
        }, 10000);
    }

    // OK
    // IIPlayer
    private static void stopTime() {
        curPowerupId = -1;
        IIPlayer.setStopPowerup(true);

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                IIPlayer.setStopPowerup(false);
                timerRunning = false;
            }
        }, 5000);


    }

    // OK
    // WorldController
    private static void homeDefence() {
        curPowerupId = -1;
        WorldController.homeDefence(true);

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                WorldController.homeDefence(false);
                timerRunning = false;
            }
        }, 10000);
    }

    // Tank
    private static void levelUp() {
        timerRunning = false;
        curPowerupId = -1;
    }

    // OK
    private static void points() {
        ScoreController.incScore(500);
        timerRunning = false;
        curPowerupId = -1;
    }
}


