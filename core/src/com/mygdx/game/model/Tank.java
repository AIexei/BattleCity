package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.II.TanksGenerator;
import com.mygdx.game.controller.ShellsController;
import com.mygdx.game.controller.TanksController;
import com.mygdx.game.controller.WorldController;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ������� on 01.03.2016.
 */

public class Tank {
    private float x;
    private float y;
    private float speed;

    private int level;
    private int direction;
    private int reloadingTime;

    private boolean isPlayer;
    private boolean canShoot;
    private boolean moveFlag;

    private Sprite sprite;

    private Timer timer;


    public Tank(int x, int y, int rt, int l, float r, float s,  Texture image) {
        this.x = x;
        this.y = y;
        this.speed = s;
        this.level = l;
        this.sprite = new Sprite(image);
        this.canShoot = true;
        this.timer = new Timer();
        this.moveFlag = false;
        this.direction = (int) (r/90);
        this.reloadingTime = rt;
        this.isPlayer = false;

        sprite.setRotation(r);
        sprite.setX(x);
        sprite.setY(y);
    }


    public Tank(int x, int y, Texture image) {
        this(x, y, 1000, 0, 0, 2.5f, image);
    }


    public Tank(Texture image) {
        this(0, 0, 1000, 0, 0, 2.5f, image);
    }


    public void addX(float value) {
        if (value >= 0) {
            if (x + speed >= WorldController.getBorder() - 50) {
                x = WorldController.getBorder() - 50;
            } else if ((WorldController.canMove(x + 50, y, 1, this)) &&
                       (TanksController.notCollisionWithTank(x + 50, y, 1, this))) {
                x += speed;
            }
        } else {
            if (x - speed <= 0) {
                x = 0;
            } else if ((WorldController.canMove(x - speed, y, 3, this)) &&
                       (TanksController.notCollisionWithTank(x - speed, y, 3, this))) {
                x -= speed;
            }
        }

        sprite.setX(x);
    }


    public void addY(float value) {
        if (value >= 0) {
            if (y + speed >= WorldController.getBorder() - 50) {
                y = WorldController.getBorder() - 50;
            } else if ((WorldController.canMove(x, y + 50, 0, this)) &&
                       (TanksController.notCollisionWithTank(x, y + 50, 0, this))) {
                y += speed;
            }
        } else {
            if (y - speed <= 0) {
                y = 0;
            } else if ((WorldController.canMove(x, y - speed, 2, this)) &&
                       (TanksController.notCollisionWithTank(x, y - speed, 2, this))) {
                y -= speed;
            }
        }

        sprite.setY(y);
    }


    public void fire() {
        if (canShoot) {
            ShellsController.addShell(new Shell(this));

            canShoot = false;
            timer.schedule(new Reloading(), reloadingTime);
        }
    }


    private class Reloading extends TimerTask {
        @Override
        public void run() {
            canShoot = true;
        }
    }


    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;

        sprite.setTexture(TanksGenerator.getTexture(level, true));

        switch (level) {
            case 0:
                reloadingTime = 1000;
                break;
            case 1:
                reloadingTime = 800;
                break;
            case 2:
                reloadingTime = 600;
                break;
        }
    }


    public void incLevel() {
        if (level != 2)
            level++;

        if (isPlayer) {
            sprite.setTexture(TanksGenerator.getTexture(level, true));

            switch (level) {
                case 0:
                    reloadingTime = 1000;
                    break;
                case 1:
                    reloadingTime = 800;
                    break;
                case 2:
                    reloadingTime = 600;
                    break;
            }
        }
    }


    public void decLevel() {
        if (level != 0)
            level--;

        if (isPlayer) {
            sprite.setTexture(TanksGenerator.getTexture(level, true));

            switch (level) {
                case 0:
                    reloadingTime = 1000;
                    break;
                case 1:
                    reloadingTime = 800;
                    break;
                case 2:
                    reloadingTime = 600;
                    break;
            }
        } else {
            sprite.setTexture(TanksGenerator.getTexture(3, false));
        }
    }


    public float getX() {
        return x;
    }


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


    public void isPlayer(boolean value) {
        this.isPlayer = value;
    }


    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }


    public void setDir(int dir) {
        this.direction = dir;
    }


    public int getDir() {
        return direction;
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
