package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ������� on 01.03.2016.
 */

public class Tank implements Mobility{
    private float x;
    private float y;

    private Texture img;
    private Sprite sprite;

    private ArrayList<Shell> shells;
    private boolean canShoot;

    private Timer timer;


    public Tank(int x, int y, Texture img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.sprite = new Sprite(img);
        this.shells = new ArrayList<Shell>();
        this.canShoot = true;
        this.timer = new Timer();

        sprite.setX(x);
        sprite.setY(y);
    }

    public Tank(Texture img) {
        this.img = img;
        this.x = 0;
        this.y = 0;
        this.sprite = new Sprite(img);
        this.shells = new ArrayList<Shell>();
        this.canShoot = true;
        this.timer = new Timer();

        sprite.setX(x);
        sprite.setY(y);
    }

    @Override
    public void addX(float value) {
        if (value >= 0) {
            if (x + value >= World.getBorder() - 50) {
                x =  - 50;
            } else if (World.canMove(x + 50, y, 1)) {
                x += value;
            }
        } else {
            if (x + value <= 0) {
                x = 0;
            } else if (World.canMove(x + value, y, 3)) {
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
            } else if (World.canMove(x, y + 50, 0)) {
                y += value;
            }
        } else {
            if (y + value <= 0) {
                y = 0;
            } else if (World.canMove(x, y + value, 2)) {
                y += value;
            }
        }

        sprite.setY(y);
    }

    public void fire() {
        if (canShoot) {


            // something
            //------------------------
            //-----------------------
            System.out.println("FIRE!!!    ");


            canShoot = false;
            timer.schedule(new Reloading(), 2000);
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

    public float getRotation() {
        return sprite.getRotation();
    }

    public void setRotation(float x) {
        sprite.setRotation(x);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);

        for (int i = 0; i < shells.size(); i++) {
            shells.get(i).draw(batch);
        }
    }

    public float barrelX() {
        return 0;
    }

    public float barrelY() {
        return 0;
    }
}
