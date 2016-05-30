package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.II.IIPlayer;
import com.mygdx.game.model.DigitsImages;

/**
 * Created by Алексей on 26.05.2016.
 */
public class GameInfoView {
    private static int tanksLeft;

    private static Texture smallTank;
    private static Texture flag;

    static {
        smallTank = new Texture("menus/smallTank.png");
        flag = new Texture("menus/levelNum.png");
    }


    public static void create() {
        tanksLeft = IIPlayer.getTanksLeftCount();
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

        batch.draw(flag, 680, 100);
        batch.draw(DigitsImages.getBlackDigit(GameController.getStage()+1), 750, 100);

        batch.draw(DigitsImages.getBlackDigit(GameController.getLives()), 750, 250);
    }
}

