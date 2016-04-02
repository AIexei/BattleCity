package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Алексей on 01.03.2016.
 */

public class Tank implements Mobility{
    private float x;
    private float y;
    private byte accessory;
    private byte level;

    private Texture[] images;
    private Sprite sprite;

    private ArrayList<Shell> shells;
    private boolean canShoot;

    private Timer timer;


    public Tank(int x, int y, Texture[] images, byte accessory) {
        this.x = x;
        this.y = y;
        this.level = 0;
        this.images= images;
        this.sprite = new Sprite(images[level]);
        this.shells = new ArrayList<Shell>();
        this.canShoot = true;
        this.timer = new Timer();
        this.accessory = accessory;

        sprite.setX(x);
        sprite.setY(y);
    }

    public Tank(Texture[] images, byte accessory) {
        this.images = images;
        this.x = 0;
        this.y = 0;
        this.level = 0;
        this.sprite = new Sprite(images[level]);
        this.shells = new ArrayList<Shell>();
        this.canShoot = true;
        this.timer = new Timer();
        this.accessory = accessory;

        sprite.setX(x);
        sprite.setY(y);
    }

    @Override
    public void addX(float value) {
        if (value >= 0) {
            if (x + value >= World.getBorder() - 50) {
                x = World.getBorder() - 50;
            } else if ((World.canMove(x + 50, y, 1, this)) && (World.notCollisionWithTank(x + 50, y, 1, this))) {
                x += value;
            }
        } else {
            if (x + value <= 0) {
                x = 0;
            } else if ((World.canMove(x + value, y, 3, this)) && (World.notCollisionWithTank(x + value, y, 3, this))) {
                x += value;
            }
        }

        sprite.setX(x);
    }

    @Override
    public void addY(float value) {
        if (value >= 0) {
            if (y + value >= World.getBorder() - 50) {
                y = World.getBorder() - 50;
            } else if ((World.canMove(x, y + 50, 0, this)) && (World.notCollisionWithTank(x, y + 50, 0, this))) {
                y += value;
            }
        } else {
            if (y + value <= 0) {
                y = 0;
            } else if ((World.canMove(x, y + value, 2, this)) && (World.notCollisionWithTank(x, y + value, 2, this))) {
                y += value;
            }
        }

        sprite.setY(y);
    }

    public void fire() {
        if (canShoot) {

            shells.add(new Shell(x, y, (int)(((360 - getRotation()) % 360)/90), accessory));
            System.out.println((int) getRotation());
            // something
            //------------------------
            //-----------------------
            System.out.println("FIRE!!!    ");


            canShoot = false;
            timer.schedule(new Reloading(), 1000);
        }
    }

    private class Reloading extends TimerTask {
        @Override
        public void run() {
            canShoot = true;
        }
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public byte getAccessory() {
        return accessory;
    }

    public float getRotation() {
        return sprite.getRotation();
    }

    public void setRotation(float x) {
        sprite.setRotation(x);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void drawShells(SpriteBatch batch) {
        for (int i = 0; i < shells.size(); i++) {
            if (!shells.get(i).draw(batch)) {
                shells.remove(i);
            }
        }
    }

    public void setLevel(int newLevel) {
        if (newLevel == 0) {

        } else if (newLevel == 1) {

        } else if (newLevel == 2) {

        }
    }
}
