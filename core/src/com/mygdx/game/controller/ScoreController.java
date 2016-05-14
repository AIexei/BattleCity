package com.mygdx.game.controller;

/**
 * Created by ������� on 28.04.2016.
 */

public class ScoreController {
    private static int score;

    public static void create() {
        score = 0;
    }

    public static void incScore(int dx) {
        score += dx;
    }
}
