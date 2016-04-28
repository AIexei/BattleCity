package com.mygdx.game.controller;

/**
 * Created by Алексей on 28.04.2016.
 */

public class ScoreController {
    private static int score;

    public static void incScore(int dx) {
        score += dx;
    }
}
