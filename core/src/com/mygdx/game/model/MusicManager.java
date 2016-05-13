package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Алексей on 11.05.2016.
 */

public class MusicManager {
    private static Music shoot;
    private static Music hit;
    private static Music kill;
    private static Music powerOn;
    private static Music powerOff;
    private static Music engine;
    private static Music moving;


    static {
        shoot = Gdx.audio.newMusic(Gdx.files.internal("music/fire.mp3"));
    }


    public static void playShoot() {
        shoot.play();
    }


    public static void playHit() {
        hit.play();
    }


    public static void playKill() {
        kill.play();
    }


    public static void playPowerOn() {
        powerOn.play();
    }


    public static void playPowerOff() {
        powerOff.play();
    }


    public static void playEngine() {
        engine.play();
    }


    public static void playMoving() {
        moving.play();
    }
}
