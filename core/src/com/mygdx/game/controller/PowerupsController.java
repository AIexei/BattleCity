package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.II.IIPlayer;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ������� on 24.04.2016.
 */

public class PowerupsController {
    private static ArrayList<Texture> images;

    private static boolean timerRunning;
    private static int curPowerupId;
    private static float curX;
    private static float curY;


    static {
        timerRunning = false;
        curPowerupId = -1;

        images = new ArrayList<Texture>();
        //...
    }


    public static void actions() {
        if (!timerRunning) {
            timerRunning = true;

            (new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    createPowerup();
                }
            }, (new Random()).nextInt(30) + 5);
        }
    }


    public static void draw(SpriteBatch batch) {
        if (curPowerupId != -1) {

        }
    }


    private static void createPowerup() {
        curPowerupId = (new Random()).nextInt(6);

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
    }

    // OK
    // TanksController
    private static void grenade() {
        TanksController.killAllTanks();
        timerRunning = false;
        curPowerupId = -1;
    }

    // animation
    // TanksController
    private static void immortality() {
        TanksController.setPlayerImmortality(true);

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                TanksController.setPlayerImmortality(false);
                timerRunning = false;
                curPowerupId = -1;
            }
        }, 10000);
    }

    // OK
    // IIPlayer
    private static void stopTime() {
        IIPlayer.setStopPowerup(true);

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                IIPlayer.setStopPowerup(false);
                timerRunning = false;
                curPowerupId = -1;
            }
        }, 5000);


    }

    // OK
    // WorldController
    private static void homeDefence() {
        WorldController.homeDefence(true);

        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                WorldController.homeDefence(false);
                timerRunning = false;
                curPowerupId = -1;
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
