package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ShellsController;
import com.mygdx.game.controller.TanksController;
import com.mygdx.game.controller.WorldController;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Алексей on 01.03.2016.
 */

public class Tank implements Mobility {
    private float x;
    private float y;
    private byte level;
    private boolean canShoot;
    private boolean moveFlag;

    private Texture[] images;
    private Sprite sprite;

    private Timer timer;


    public Tank(int x, int y, Texture[] images) {
        this.x = x;
        this.y = y;
        this.level = 0;
        this.images= images;
        this.sprite = new Sprite(images[level]);
        this.canShoot = true;
        this.timer = new Timer();
        this.moveFlag = false;

        sprite.setX(x);
        sprite.setY(y);
    }

    public Tank(Texture[] images) {
        this.images = images;
        this.x = 0;
        this.y = 0;
        this.level = 0;
        this.sprite = new Sprite(images[level]);
        this.canShoot = true;
        this.timer = new Timer();

        sprite.setX(x);
        sprite.setY(y);
    }

    @Override
    public void addX(float value) {
        if (value >= 0) {
            if (x + value >= WorldController.getBorder() - 50) {
                x = WorldController.getBorder() - 50;
            } else if ((WorldController.canMove(x + 50, y, 1, this)) && (TanksController.notCollisionWithTank(x + 50, y, 1, this))) {
                x += value;
            }
        } else {
            if (x + value <= 0) {
                x = 0;
            } else if ((WorldController.canMove(x + value, y, 3, this)) && (TanksController.notCollisionWithTank(x + value, y, 3, this))) {
                x += value;
            }
        }

        sprite.setX(x);
    }

    @Override
    public void addY(float value) {
        if (value >= 0) {
            if (y + value >= WorldController.getBorder() - 50) {
                y = WorldController.getBorder() - 50;
            } else if ((WorldController.canMove(x, y + 50, 0, this)) && (TanksController.notCollisionWithTank(x, y + 50, 0, this))) {
                y += value;
            }
        } else {
            if (y + value <= 0) {
                y = 0;
            } else if ((WorldController.canMove(x, y + value, 2, this)) && (TanksController.notCollisionWithTank(x, y + value, 2, this))) {
                y += value;
            }
        }

        sprite.setY(y);
    }

    public void fire() {
        if (canShoot) {

            ShellsController.addShell(new Shell(this));
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

    public boolean getMoveFlag() {
        return moveFlag;
    }

    public void setMoveFlag(boolean newFlag) {
        moveFlag = newFlag;
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


    @Override
    protected void finalize() throws Throwable {
        System.out.print(this);
        System.out.println("dispose");
        super.finalize();
    }
}
