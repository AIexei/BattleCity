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
    private int accessory;

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


    public Shell(float tankX, float tankY, int tankDirection, int accessory) {
        this.direction = tankDirection;
        this.sprite = new Sprite(img);
        this.isFlying = true;
        this.accessory = accessory;

        switch(direction) {
            case 0:
                this.y = tankY + 50;
                this.x = tankX + 23; // shell in [23,27]
                break;
            case 1:
                this.y = tankY + 23;
                this.x = tankX + 50;
                sprite.setRotation(270);
                break;
            case 2:
                this.y = tankY;
                this.x = tankX + 23;
                sprite.setRotation(180);
                break;
            case 3:
                this.y = tankY + 23;
                this.x = tankX;
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
                    if (y + 2.5f > World.getBorder() - 6) {
                        System.out.println("out");
                        isFlying = false;
                    } else if ((World.canMove(x, y + 2.5f, direction, this)) && (World.notCollisionWithTank(x, y + 2.5f, direction, this))) {
                        y += 2.5f;
                    } else {
                        World.destruction(x, y + 2.5f, direction);
                        isFlying = false;
                    }

                    ////////////
                    //////////////
                    ///////////
                }
                break;
            case 1:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (x + 2.5f > World.getBorder() - 6) {
                        System.out.println("out");
                        isFlying = false;
                    } else if ((World.canMove(x + 2.5f, y, direction, this)) && (World.notCollisionWithTank(x + 2.5f, y, direction, this))) {
                        x += 2.5f;
                    } else {
                        World.destruction(x + 2.5f, y, direction);
                        isFlying = false;
                    }
                    ////////////
                    //////////////
                    ///////////
                }
                break;
            case 2:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (y - 2.5f <= 0) {
                        System.out.println("out");
                        isFlying = false;
                    } else if ((World.canMove(x, y - 2.5f, direction, this)) && (World.notCollisionWithTank(x, y - 2.5f, direction, this))) {
                        y -= 2.5f;
                    } else {
                        World.destruction(x, y - 2.5f, direction);
                        isFlying = false;
                    }

                    ////////////
                    //////////////
                    ///////////
                }
                break;
            case 3:
                for (float i = 0; i < speed; i += 2.5f) {
                    if (x - 2.5f <= 0) {
                        System.out.println("out");
                        isFlying = false;
                    } else if ((World.canMove(x - 2.5f, y, direction, this)) && (World.notCollisionWithTank(x - 2.5f, y, direction, this))) {
                        x -= 2.5f;
                    } else {
                        World.destruction(x - 2.5f, y, direction);
                        isFlying = false;
                    }
                    ////////////
                    //////////////
                    ///////////
                }
                break;
        }

        sprite.setX(x);
        sprite.setY(y);
    }
}
