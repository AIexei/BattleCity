package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Алексей on 01.03.2016.
 */

public class BattleCity extends Game {
    SpriteBatch batch;
    Texture[] tanks;
    Texture brick;
    Texture block;
    Texture emblem;
    Texture grass;
    Texture water;
    ArrayList<Tank> arrayList;

    World world;
    Tank tank;
    Tank enemy;

    final float tankSpeed = 2.5f;
    boolean flag = false;
    byte[][] arr;

    long start = 0;
    long frames = 0;

    @Override
    public void create() {
        start = System.currentTimeMillis();

        tanks = new Texture[3];

        tanks[0] = new Texture("tank1.png");
        tanks[1] = new Texture("tank2.png");
        tanks[2] = new Texture("tank3.png");
        batch = new SpriteBatch();
        brick = new Texture("kir.png");
        block = new Texture("block.png");
        emblem = new Texture("flag.png");
        grass = new Texture("trawa.png");
        water = new Texture("voda.png");

        Shell.setSpeed(10f);
        Shell.loadTexture(new Texture("shell.png"));

        arr = new byte[26][26];

        try (FileInputStream fileReader = new FileInputStream("map1.m")) {
            Scanner scanner = new Scanner(fileReader);

            for (int i = 0; i < 26; i++)
                for (int j = 0; j < 26; j++)
                    arr[25 -i][j] = scanner.nextByte();

        } catch(IOException ioe) {

            System.out.println("File not found");
        }

        tank = new Tank(tanks, (byte)8);
        enemy = new Tank(100, 225,tanks, (byte) 8);
        arrayList = new ArrayList<Tank>();
        arrayList.add(tank);
        arrayList.add(enemy);

        world = new World(arr, arrayList, 650);
    }


    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);


        if (flag) {
            equalizer();
        } else {
            inputControl();
        }

        batch.begin();

        World.drawTanks(batch);

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (arr[i][j] == 1) {
                    batch.draw(grass, j * 25, i * 25);
                } else if (arr[i][j] == 2) {
                    batch.draw(water, j * 25, i * 25);
                } else if (arr[i][j] == 3) {
                    batch.draw(brick, j * 25, i * 25);
                } else if (arr[i][j] == 4) {
                    batch.draw(block, j * 25, i * 25);
                }
            }
        }

        batch.draw(emblem, 12 * 25, 0);
        World.drawShells(batch);

        World.update();

        batch.end();


        frames++;
        long time = System.currentTimeMillis() - start;
        System.out.println(1000 * ((double)frames / (double) time));

    }


    @Override
    public void dispose() {
        System.exit(0);
    }



    public void inputControl() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (tank.getRotation() != 0) {
                flag = true;
                equalizer();
            }

            if (flag == false) {
                if (tank.getRotation() != 0) {
                    tank.setRotation(0);
                } else {
                    tank.addY(tankSpeed);
                }
            }
        } else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (tank.getRotation() != 180) {
                flag = true;
                equalizer();
            }

            if (flag == false) {
                if (tank.getRotation() != 180) {
                    tank.setRotation(180);
                } else {
                    tank.addY(-tankSpeed);
                }
            }
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (tank.getRotation() != 90) {
                flag = true;
                equalizer();
            }

            if (flag == false) {
                if (tank.getRotation() != 90) {
                    tank.setRotation(90);
                } else {
                    tank.addX(-tankSpeed);
                }
            }
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (tank.getRotation() != 270) {
                flag = true;
                equalizer();
            }

            if (flag == false) {
                if (tank.getRotation() != 270) {
                    tank.setRotation(270);
                } else {
                    tank.addX(tankSpeed);
                }
            }
        } else {
            flag = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            tank.fire();
        }
    }


    public void equalizer() {
        int direction = (int) tank.getRotation();
        int xx = (int)(tank.getX() / 25);
        int yy = (int)(tank.getY() / 25);

        switch(direction) {
            case 0:
                if ((tank.getY() % 25 != 0) && (tank.getY() < (yy * 25) + 25)) {
                    tank.addY(tankSpeed);
                } else {
                    flag = false;
                }

                break;
            case 90:
                if ((tank.getX() % 25 != 0) && (tank.getX() > (xx * 25) - 25)) {
                    tank.addX(-tankSpeed);
                } else {
                    flag = false;
                }

                break;
            case 180:
                if ((tank.getY() % 25 != 0) && (tank.getY() > (yy * 25) - 25)) {
                     tank.addY(-tankSpeed);
                } else {
                    flag = false;
                }

                break;
            case 270:
                if ((tank.getX() % 25 != 0) && (tank.getX() < (xx * 25) + 25)) {
                    tank.addX(tankSpeed);
                } else {
                    flag = false;
                }

                break;
        }
    }
}