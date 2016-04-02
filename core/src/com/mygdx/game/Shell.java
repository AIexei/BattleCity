package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Алексей on 26.03.2016.
 */



public class Shell {
    private float x;
    private float y;
    private int direction;

    private Tank owner;
    private Sprite sprite;
    private boolean isFlying;

    private static float speed = 10f;
    private static Texture img;


    public static void setSpeed(float s) {
        speed = s;
    }


    public static void loadTexture(Texture image) {
        img = image;
    }


    public Shell(Tank owner) {
        this.direction = (int)(((360 - owner.getRotation()) % 360)/90);
        this.sprite = new Sprite(img);
        this.isFlying = true;
        this.owner = owner;

        switch(direction) {
            case 0:
                this.y = owner.getY() + 50;
                this.x = owner.getX() + 23; // shell in [23,27]
                break;
            case 1:
                this.y = owner.getY() + 23;
                this.x = owner.getX() + 50;
                sprite.setRotation(270);
                break;
            case 2:
                this.y = owner.getY();
                this.x = owner.getX() + 23;
                sprite.setRotation(180);
                break;
            case 3:
                this.y = owner.getY() + 23;
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

    private void move() {
        switch (direction) {
            case 0:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (isFlying) {
                        if (y + 2.5f > World.getBorder() - 6) {
                            isFlying = false;
                        } else if (World.canMove(x, y + 2.5f, direction, this)) {
                            if (World.notCollisionWithTank(x, y + 2.5f, direction, owner)) {
                                y += 2.5f;
                            } else {
                                World.killTank(x, y + 2.5f, direction, owner);
                                isFlying = false;
                            }
                        } else {
                            World.destruction(x, y + 2.5f, direction);
                            isFlying = false;
                        }
                    }
                }

                break;
            case 1:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (isFlying) {
                        if (x + 2.5f > World.getBorder() - 6) {
                            isFlying = false;
                        } else if (World.canMove(x + 2.5f, y, direction, this)) {
                            if (World.notCollisionWithTank(x + 2.5f, y, direction, owner)) {
                                x += 2.5f;
                            } else {
                                World.killTank(x + 2.5f, y, direction, owner);
                                isFlying = false;
                            }
                        } else {
                            World.destruction(x + 2.5f, y, direction);
                            isFlying = false;
                        }
                    }
                }

                break;
            case 2:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (isFlying) {
                        if (y - 2.5f <= 0) {
                            isFlying = false;
                        } else if (World.canMove(x, y - 2.5f, direction, this)) {
                            if (World.notCollisionWithTank(x, y - 2.5f, direction, owner)) {
                                y -= 2.5f;
                            } else {
                                World.killTank(x, y - 2.5f, direction, owner);
                                isFlying = false;
                            }
                        } else {
                            World.destruction(x, y - 2.5f, direction);
                            isFlying = false;
                        }
                    }
                }

                break;
            case 3:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (isFlying) {
                        if (x - 2.5f <= 0) {
                            isFlying = false;
                        } else if (World.canMove(x - 2.5f, y, direction, this)) {
                            if (World.notCollisionWithTank(x - 2.5f, y, direction, owner)) {
                                x -= 2.5f;
                            } else {
                                World.killTank(x - 2.5f, y, direction, owner);
                                isFlying = false;
                            }
                        } else {
                            World.destruction(x - 2.5f, y, direction);
                            isFlying = false;
                        }
                    }
                }

                break;
        }

        sprite.setX(x);
        sprite.setY(y);
    }
}
