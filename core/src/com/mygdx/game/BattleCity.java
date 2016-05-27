package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.view.screens.ResultScreen;
import com.mygdx.game.view.screens.StartScreen;

/**
 * Created by Алексей on 14.05.2016.
 */

public class BattleCity extends Game {
    @Override
    public void create() {
        try {
            GameController.create();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setScreen(new StartScreen(this));
    }
}
