package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.view.*;

/**
 * Created by Алексей on 14.05.2016.
 */

public class BattleCity extends Game {
    @Override
    public void create() {
        //setScreen(new GameScreen());
        //setScreen(new StageScreen(this));
        setScreen(new ResultScreen(this));
    }
}
