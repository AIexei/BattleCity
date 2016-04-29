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


    private static void grenade() {
        // убить все танки
        TanksController.killAllTanks();
        timerRunning = false;
        curPowerupId = -1;
    }


    private static void immortality() {
        // как нибудь

    }


    private static void stopTime() {
        // остановить танк мувмент
        IIPlayer.stopMoving();
    }


    private static void homeDefence() {
        // достроить дом блоками, и через пару сек обратно вернуть

        WorldController.homeDefence();
    }


    private static void levelUp() {
        // изи катка
        timerRunning = false;
        curPowerupId = -1;
    }


    private static void points() {
        // изи изи катка

        ScoreController.incScore(500);
        timerRunning = false;
        curPowerupId = -1;
    }
}
