package com.mygdx.game.controller.II;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.model.Tank;

/**
 * Created by Алексей on 12.04.2016.
 */

public class MoveController {
    public static void move(Tank tank, int dir) {
        if (tank.getMoveFlag()) {
            equalizer(tank);
        } else {
            inputControl(tank, dir);
        }

        tank.fire();
    }

    private static void inputControl(Tank tank, int dir) {
        if (dir == 0) {
            if (tank.getRotation() != 0) {
                tank.setMoveFlag(true);
                equalizer(tank);
            }

            if (!tank.getMoveFlag()) {
                if (tank.getRotation() != 0) {
                    tank.setRotation(0);
                } else {
                    tank.addY(1);
                }
            }
        } else if (dir == 2) {
            if (tank.getRotation() != 180) {
                tank.setMoveFlag(true);
                equalizer(tank);
            }

            if (!tank.getMoveFlag()) {
                if (tank.getRotation() != 180) {
                    tank.setRotation(180);
                } else {
                    tank.addY(-1);
                }
            }
        } else if (dir == 3) {
            if (tank.getRotation() != 90) {
                tank.setMoveFlag(true);
                equalizer(tank);
            }

            if (!tank.getMoveFlag()) {
                if (tank.getRotation() != 90) {
                    tank.setRotation(90);
                } else {
                    tank.addX(-1);
                }
            }
        } else if (dir == 1) {
            if (tank.getRotation() != 270) {
                tank.setMoveFlag(true);
                equalizer(tank);
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
    }


    private static void equalizer(Tank tank) {
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
