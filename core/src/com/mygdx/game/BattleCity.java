package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Алексей on 01.03.2016.
 */

public class BattleCity extends Game {
    SpriteBatch batch;
    Texture img;
    Texture k;
    Texture b;
    Texture f;
    Texture t;
    ShapeRenderer renderer;
    ArrayList<Tank> arrayList;

    World world;
    Tank tank;

    final float tankSpeed = 2.5f;
    boolean flag = false;
    byte[][] arr;

    //long start = 0;
    //long frames = 0;

    @Override
    public void create() {
        //start = System.currentTimeMillis();

        batch = new SpriteBatch();
        img = new Texture("tank1.png");
        k = new Texture("kir.png");
        b = new Texture("block.png");
        f = new Texture("flag.png");
        t = new Texture("trawa.png");
        renderer = new ShapeRenderer();

        arr = new byte[26][26];

        try (FileInputStream fileReader = new FileInputStream("map1.m")) {
            Scanner scanner = new Scanner(fileReader);

            for (int i = 0; i < 26; i++)
                for (int j = 0; j < 26; j++)
                    arr[j][25 - i] = scanner.nextByte();

        } catch(IOException ioe) {
            System.out.println("File not found");
        }


        tank = new Tank(img, 650);
        arrayList = new ArrayList<Tank>();
        arrayList.add(tank);

        world = new World(arr, arrayList);
    }


    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        /*
        renderer.setColor(com.badlogic.gdx.graphics.Color.GOLD);
        for (int i = 0; i <= 650; i += 25) {
            renderer.line(i, 0, i, 650);
            renderer.line(0, i, 650, i);
        }

        renderer.setColor(com.badlogic.gdx.graphics.Color.BLUE);
        for (int i = 0; i <= 650; i += 50) {
            renderer.line(0, i, 650, i);
            renderer.line(i, 0, i, 650);
        }



        renderer.setColor(Color.GREEN);
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (arr[i][j] == 1)
                renderer.rect(i*25, j*25, 25, 25);
            }
        }
        */



        renderer.end();

        if (flag) {
            equalizer();
        } else {
            inputControl();
        }

        batch.begin();
        tank.draw(batch);

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (arr[i][j] == 1) {
                    batch.draw(k, i * 25, j * 25);
                } else if (arr[i][j] == 2) {
                    batch.draw(b, i * 25, j * 25);
                }
            }
        }

        batch.draw(f, 12 * 25, 0);


        batch.end();

        /*
        frames++;
        long time = System.currentTimeMillis() - start;
        System.out.println(1000 * ((double)frames / (double) time));
        */
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
