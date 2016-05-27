package com.mygdx.game.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Алексей on 27.05.2016.
 */

public class GameController {
    private static int lives;
    private static int stage;
    private static int score;

    private static int[] highScores;
    private static int[] killed;


    public static void create() throws Exception {
        lives = 3;
        stage = 0;
        score = 0;

        killed = new int[4];
        highScores = new int[6];

        FileInputStream fis = new FileInputStream("highScores");
        Scanner scanner = new Scanner(fis);
        int x = 0;

        while (scanner.hasNext()) {
            highScores[x++] = scanner.nextInt();
        }
    }


    public static void decLives() {
        lives--;
    }


    public static int getLives() {
        return lives;
    }


    public static int getScore() {
        return score;
    }


    public static void incScore(int dx) {
        score += dx;
    }


    public static void incStage() {
        stage++;
    }


    public static int getStage() {
        return stage;
    }


    public static void incKilled(int type) {
        killed[type]++;
    }


    public static void clearKilled() {
        Arrays.fill(killed, 0);
    }


    public static int[] getKilled() {
        return killed;
    }


    public static int[] getHighScores() {
        return highScores;
    }


    public static void clear() throws Exception {
        lives = 3;
        stage = 0;

        Arrays.fill(killed, 0);

        highScores[5] = score;
        Arrays.sort(highScores);

        for (int i = 0; i < 3; i++) {
            int temp = highScores[i];
            highScores[i] = highScores[5 - i];
            highScores[5 - i] = temp;
        }

        // дописать запись в файл

    }
}
