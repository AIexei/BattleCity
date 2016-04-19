package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.II.IIPlayer;
import com.mygdx.game.controller.II.MoveController;
import com.mygdx.game.controller.ShellsController;
import com.mygdx.game.controller.TanksController;
import com.mygdx.game.controller.WorldController;
import com.mygdx.game.controller.input.InputController;
import com.mygdx.game.model.Shell;
import com.mygdx.game.model.Tank;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Алексей on 01.03.2016.
 */

public class BattleCity extends Game {
    Timer myTimer = new Timer();
    int dir;
    int dir1;


    SpriteBatch batch;
    Texture[] tanks;
    Texture[] enemies;
    Texture brick;
    Texture block;
    Texture emblem;
    Texture grass;
    Texture water;

    WorldController worldController;
    ShellsController shellsController;
    TanksController tanksController;
    InputController inputController;

    Tank tank;
    Tank enemy;
    Tank enemy1;

    byte[][] arr;

    //long start = 0;
    //long frames = 0;

    @Override
    public void create() {
        //start = System.currentTimeMillis();

        tanks = new Texture[3];
        enemies = new Texture[3];

        tanks[0] = new Texture("tank1.png");
        tanks[1] = new Texture("tank2.png");
        tanks[2] = new Texture("tank3.png");
        enemies[0] = new Texture("enemy1.png");
        enemies[1] = new Texture("enemy2.png");
        enemies[2] = new Texture("enemy3.png");

        batch = new SpriteBatch();
        brick = new Texture("kir.png");
        block = new Texture("block.png");
        emblem = new Texture("flag.png");
        grass = new Texture("trawa.png");
        water = new Texture("voda.png");

        Shell.setSpeed(10f);
        Shell.loadTexture(new Texture("shell.png"));

        arr = new byte[26][26];

        try (FileInputStream fileReader = new FileInputStream("maps/map" + Integer.toString(1))) {
            Scanner scanner = new Scanner(fileReader);

            for (int i = 0; i < 26; i++)
                for (int j = 0; j < 26; j++)
                    arr[25 -i][j] = scanner.nextByte();

        } catch(IOException ioe) {

            System.out.println("File not found");
        }

        tank = new Tank(tanks);
        enemy = new Tank(200, 0, enemies);
        enemy1 = new Tank(200, 600, enemies);

        worldController = new WorldController(arr, 650);
        shellsController = new ShellsController();
        tanksController = new TanksController(tank);
        inputController = new InputController(tank);

        tanksController.addTank(tank);
        tanksController.addTank(enemy);
        tanksController.addTank(enemy1);

        /*myTimer.schedule((new TimerTask() {
            @Override
            public void run() {
                dir = IIPlayer.randomDir();
                dir1 = IIPlayer.randomDir();
            }
        }), 0, 3000);
        */
    }


    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);


        InputController.inputProcessing();
        //MoveController.move(enemy, dir);
        //MoveController.move(enemy1, dir1);

        batch.begin();
        TanksController.drawTanks(batch);

        enemy.fire();
        enemy1.fire();


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
        ShellsController.drawShells(batch);
        TanksController.update();

        batch.end();

        if (TanksController.isEnd())
            dispose();

        //frames++;
        //long time = System.currentTimeMillis() - start;
        //System.out.println(1000 * ((double)frames / (double) time));
    }


    @Override
    public void dispose() {
        System.out.println("End game");
        System.exit(0);
    }
}