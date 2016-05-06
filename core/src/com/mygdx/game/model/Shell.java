package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.AnimationsController;
import com.mygdx.game.controller.TanksController;
import com.mygdx.game.controller.WorldController;
import com.mygdx.game.model.anima.AnimImages;
import com.mygdx.game.model.anima.Animation;

/**
 * Created by ������� on 26.03.2016.
 */



public class Shell {
    private float x;
    private float y;
    private int direction;

    private Tank owner;
    private Sprite sprite;
    private boolean isFlying;

    private static float speed;
    private static Texture img;


    static {
        img = new Texture("other/shell.png");
        speed = 10f;
    }


    public Shell(Tank owner) {
        this.direction = (int)(((360 - owner.getRotation()) % 360)/90);
        this.sprite = new Sprite(img);
        this.isFlying = true;
        this.owner = owner;

        switch(direction) {
            case 0:
                this.y = owner.getY() + 50;
                this.x = owner.getX() + 21; // shell in [23,27]
                break;
            case 1:
                this.y = owner.getY() + 21;
                this.x = owner.getX() + 50;
                sprite.setRotation(270);
                break;
            case 2:
                this.y = owner.getY();
                this.x = owner.getX() + 21;
                sprite.setRotation(180);
                break;
            case 3:
                this.y = owner.getY() + 21;
                this.x = owner.getX();
                sprite.setRotation(90);
                break;
        }
    }


    public boolean draw(SpriteBatch batch) {
        move();
        sprite.draw(batch);
        return isFlying;
    }


    public Tank getOwner() {
        return this.owner;
    }


    public float getX () {
        return x;
    }


    public float getY() {
        return y;
    }


    public int getDir() {
        return direction;
    }


    private void move() {
        switch (direction) {
            case 0:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (isFlying) {
                        if (y + 2.5f > WorldController.getBorder() - 8) {
                            isFlying = false;
                            AnimationsController.add(new Animation(AnimImages.getBang(), 3, 1, x-21, y-16));
                        } else if (WorldController.canMove(x, y + 2.5f, direction, this)) {
                            if (TanksController.notCollisionWithTank(x, y + 2.5f, direction, this)) {
                                y += 2.5f;
                            } else {
                                if (TanksController.killTank(x, y + 2.5f, direction, owner)) {
                                    AnimationsController.add(new Animation(AnimImages.getBigBang(), 2, 1.5f, x-46, y-16));
                                }

                                isFlying = false;
                            }
                        } else {
                            WorldController.destruction(x, y + 2.5f, direction);

                            isFlying = false;
                            AnimationsController.add(new Animation(AnimImages.getBang(), 3, 1, x-21, y-16));
                        }
                    }
                }

                break;
            case 1:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (isFlying) {
                        if (x + 2.5f > WorldController.getBorder() - 6) {
                            isFlying = false;
                            AnimationsController.add(new Animation(AnimImages.getBang(), 3, 1, x-16, y-21));
                        } else if (WorldController.canMove(x + 2.5f, y, direction, this)) {
                            if (TanksController.notCollisionWithTank(x + 2.5f, y, direction, this)) {
                                x += 2.5f;
                            } else {
                                if (TanksController.killTank(x + 2.5f, y, direction, owner)) {
                                    AnimationsController.add(new Animation(AnimImages.getBigBang(), 2, 1.5f, x-16, y-46));
                                }

                                isFlying = false;
                            }
                        } else {
                            WorldController.destruction(x + 2.5f, y, direction);

                            isFlying = false;
                            AnimationsController.add(new Animation(AnimImages.getBang(), 3, 1, x-16, y-21));
                        }
                    }
                }

                break;
            case 2:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (isFlying) {
                        if (y - 2.5f <= 0) {
                            isFlying = false;
                            AnimationsController.add(new Animation(AnimImages.getBang(), 3, 1, x-21, y-25));
                        } else if (WorldController.canMove(x, y - 2.5f, direction, this)) {
                            if (TanksController.notCollisionWithTank(x, y - 2.5f, direction, this)) {
                                y -= 2.5f;
                            } else {
                                if (TanksController.killTank(x, y - 2.5f, direction, owner)) {
                                    AnimationsController.add(new Animation(AnimImages.getBigBang(), 2, 1.5f, x-46, y-75));
                                }

                                isFlying = false;
                            }
                        } else {
                            WorldController.destruction(x, y - 2.5f, direction);

                            isFlying = false;
                            AnimationsController.add(new Animation(AnimImages.getBang(), 3, 1, x-21, y-25));
                        }
                    }
                }

                break;
            case 3:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (isFlying) {
                        if (x - 2.5f <= 0) {
                            isFlying = false;
                            AnimationsController.add(new Animation(AnimImages.getBang(), 3, 1, x-25, y-21));
                        } else if (WorldController.canMove(x - 2.5f, y, direction, this)) {
                            if (TanksController.notCollisionWithTank(x - 2.5f, y, direction, this)) {
                                x -= 2.5f;
                            } else {
                                if (TanksController.killTank(x - 2.5f, y, direction, owner)) {
                                    AnimationsController.add(new Animation(AnimImages.getBigBang(), 2, 1.5f, x-75, y-46));
                                }

                                isFlying = false;
                            }
                        } else {
                            WorldController.destruction(x - 2.5f, y, direction);

                            isFlying = false;
                            AnimationsController.add(new Animation(AnimImages.getBang(), 3, 1, x-25, y-21));
                        }
                    }
                }

                break;
        }

        sprite.setX(x);
        sprite.setY(y);
    }
}
