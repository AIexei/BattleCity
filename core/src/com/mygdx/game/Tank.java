package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sun.plugin.services.WIExplorerBrowserService;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Алексей on 01.03.2016.
 */

public class Tank {
    private float x;
    private float y;
    private int border;

    private Texture img;
    private Sprite sprite;

    private ArrayList<Shell> shells;
    private boolean canShoot;

    private Timer timer;


    public Tank(int x, int y, Texture img, int border) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.sprite = new Sprite(img);
        this.border = border;
        this.shells = new ArrayList<Shell>();
        this.canShoot = true;
        this.timer = new Timer();

        sprite.setX(x);
        sprite.setY(y);
    }

    public Tank(Texture img, int border) {
        this.img = img;
        this.x = 0;
        this.y = 0;
        this.border = border;
        this.sprite = new Sprite(img);
        this.shells = new ArrayList<Shell>();
        this.canShoot = true;
        this.timer = new Timer();

        sprite.setX(x);
        sprite.setY(y);
    }

    public void addX(float value) {
        if (value >= 0) {
            if (x + value >= border - 50) {
                x = border - 50;
            } else if (World.canDrive(x + 50, y, 1)) {
                x += value;
            }
        } else {
            if (x + value <= 0) {
                x = 0;
            } else if (World.canDrive(x + value, y, 3)) {
                x += value;
            }
        }

        sprite.setX(x);
    }

    public void addY(float value) {
        if (value >= 0) {
            if (y + value >= border - 50) {
                y = border - 50;
            } else if (World.canDrive(x, y + 50, 0)) {
                y += value;
            }
        } else {
            if (y + value <= 0) {
                y = 0;
            } else if (World.canDrive(x, y + value, 2)) {
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

    public float getX() {
        return x;
    }

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
    }
}
