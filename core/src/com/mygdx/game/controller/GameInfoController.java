package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.BattleCity;
import com.mygdx.game.controller.II.IIPlayer;

/**
 * Created by Алексей on 11.05.2016.
 */

public class GameInfoController {
    private static int tanksLeft;
    private static int mapNumber;

    private static Texture smallTank;
    private static Texture flag;


    static {
        smallTank = new Texture("menus/smallTank.png");
        flag = new Texture("menus/levelNum.png");
    }


    public static void create() {
        tanksLeft = IIPlayer.getTanksLeftCount();
        mapNumber = BattleCity.getMapNumber();
        System.out.println(tanksLeft);
    }


    public static void setTanksLeft(int value) {
        tanksLeft = value;
    }


    public static void draw(SpriteBatch batch) {
        int rows = tanksLeft / 2;

        for (int i = 0; i < rows; i++) {
            batch.draw(smallTank, 692, 600 - (25 * (i+1)));
            batch.draw(smallTank, 717, 600 - (25 * (i+1)));
        }

        if (tanksLeft % 2 == 1) {
            batch.draw(smallTank, 692, 600 - (25 * (rows+1)));
        }

        batch.draw(flag, 692, 100);
    }
}
