package com.mygdx.game.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Алексей on 27.05.2016.
 */

public class GameController {
    private static int lives;
    private static int stage;
    private static int score;

    private static boolean end;

    private static int[] highScores;
    private static int[] killed;


    public static void create() throws Exception {
        lives = 3;
        stage = 0;
        score = 0;

        end = false;

        killed = new int[4];
        highScores = new int[6];

        Scanner scanner = new Scanner((new File("highScores").getAbsoluteFile()));
        int x = 0;

        while (scanner.hasNext()) {
            highScores[x++] = scanner.nextInt();
        }
    }


    public static void lose() {
        end = true;
    }


    public static boolean isEnd() {
        return end;
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

        switch (type) {
            case 0:
                score += 100;
                break;
            case 1:
            case 2:
                score += 200;
                break;
            case 3:
                score += 300;
                break;
        }
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
        end = false;

        Arrays.fill(killed, 0);

        highScores[5] = score;
        Arrays.sort(highScores);

        for (int i = 0; i < 3; i++) {
            int temp = highScores[i];
            highScores[i] = highScores[5 - i];
            highScores[5 - i] = temp;
        }

        PrintWriter out = new PrintWriter((new File("highScores").getAbsoluteFile()));
        for (int i = 0; i < 5; i++) {
            out.println(highScores[i]);
        }

        score = 0;
        out.close();
    }


    public static void serialize() throws Exception {
        PrintWriter out = new PrintWriter((new File("load").getAbsoluteFile()));
        out.println(lives);
        out.println(stage);
        out.println(score);
        out.println(end);
        out.close();
    }


    public static void load() throws Exception {
        Scanner scanner = new Scanner((new File("load").getAbsoluteFile()));

        lives = scanner.nextInt();
        stage = scanner.nextInt();
        score = scanner.nextInt();
        end = scanner.nextBoolean();

        scanner.close();
    }
}
