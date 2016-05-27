package com.mygdx.game.controller.input;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.controller.II.IIPlayer;
import com.mygdx.game.controller.PowerupsController;
import com.mygdx.game.model.Tank;
import com.mygdx.game.view.screens.MenuScreen;

/**
 * Created by ������� on 14.04.2016.
 */

public class InputController {
    private static Tank tank;
    private static Game game;

    private static boolean canMove;


    public static void create(Tank player, Game g) {
        tank = player;
        game = g;
        canMove = true;
    }


    public static void setCanMove(boolean value) {
        canMove = value;
    }


    public static void inputProcessing() {
        if (canMove) {
            if (tank.getMoveFlag()) {
                equalizer();
            } else {
                inputControl();
            }
        }
    }


    private static void inputControl() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (tank.getRotation() != 0) {
                tank.setMoveFlag(true);
                equalizer();
            }

            if (!tank.getMoveFlag()) {
                if (tank.getRotation() != 0) {
                    tank.setRotation(0);
                } else {
                    tank.addY(1);
                }
            }
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (tank.getRotation() != 180) {
                tank.setMoveFlag(true);
                equalizer();
            }

            if (!tank.getMoveFlag()) {
                if (tank.getRotation() != 180) {
                    tank.setRotation(180);
                } else {
                    tank.addY(-1);
                }
            }
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (tank.getRotation() != 90) {
                tank.setMoveFlag(true);
                equalizer();
            }

            if (!tank.getMoveFlag()) {
                if (tank.getRotation() != 90) {
                    tank.setRotation(90);
                } else {
                    tank.addX(-1);
                }
            }
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (tank.getRotation() != 270) {
                tank.setMoveFlag(true);
                equalizer();
            }

            if (!tank.getMoveFlag()) {
                if (tank.getRotation() != 270) {
                    tank.setRotation(270);
                } else {
                    tank.addX(1);
                }
            }
        } else {
            tank.setMoveFlag(true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            tank.fire();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            IIPlayer.dispose();
            PowerupsController.dispose();
            game.setScreen(new MenuScreen(game));
        }
    }


    private static void equalizer() {
        int direction = (int) tank.getRotation();
        int xx = (int)(tank.getX() / 25);
        int yy = (int)(tank.getY() / 25);

        switch(direction) {
            case 0:
                if ((tank.getY() % 25 != 0) && (tank.getY() < (yy * 25) + 25)) {
                    tank.addY(1);
                } else {
                    tank.setMoveFlag(false);
                }

                break;
            case 90:
                if ((tank.getX() % 25 != 0) && (tank.getX() > (xx * 25) - 25)) {
                    tank.addX(-1);
                } else {
                    tank.setMoveFlag(false);
                }

                break;
            case 180:
                if ((tank.getY() % 25 != 0) && (tank.getY() > (yy * 25) - 25)) {
                    tank.addY(-1);
                } else {
                    tank.setMoveFlag(false);
                }

                break;
            case 270:
                if ((tank.getX() % 25 != 0) && (tank.getX() < (xx * 25) + 25)) {
                    tank.addX(1);
                } else {
                    tank.setMoveFlag(false);
                }

                break;
        }
    }
}
