package com.mygdx.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.controller.*;
import com.mygdx.game.controller.II.IIPlayer;
import com.mygdx.game.controller.II.TanksGenerator;
import com.mygdx.game.controller.input.InputController;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.anima.AnimImages;
import com.mygdx.game.model.anima.Animation;
import com.mygdx.game.view.GameInfoView;
import com.mygdx.game.view.GameOverView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by ������� on 01.03.2016.
 */

public class GameScreen extends AbstractScreen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private Game game;

    private Texture brick;
    private Texture block;
    private Texture emblem;
    private Texture grass;
    private Texture water;

    private Tank player;

    private int stage;
    private byte[][] arr;

    //long start = 0;
    //long frames = 0;

    private boolean isEnd;
    private boolean nextScreen;
    private boolean winGame;

    public GameScreen(Game game, int stage) {
        this.game = game;
        this.stage = stage;
    }


    @Override
    public void show() {
        //start = System.currentTimeMillis();

        IIPlayer.create();

        camera = new OrthographicCamera(800, 680);
        camera.position.set(new Vector3(385, 325, 0));

        batch = new SpriteBatch();
        renderer = new ShapeRenderer();

        brick = new Texture("covering/kir.png");
        block = new Texture("covering/block.png");
        emblem = new Texture("covering/flag.png");
        grass = new Texture("covering/trawa.png");
        water = new Texture("covering/voda.png");

        arr = new byte[26][26];
        isEnd = false;
        nextScreen = false;
        winGame = false;

        try {
            FileInputStream fileReader = new FileInputStream("maps/map" + Integer.toString(stage));
            Scanner scanner = new Scanner(fileReader);

            for (int i = 0; i < 26; i++)
                for (int j = 0; j < 26; j++)
                    arr[25 -i][j] = scanner.nextByte();

        } catch(IOException ioe) {
            System.out.println("File not found");
        }

        player = new Tank(0, 225, 0, TanksGenerator.getTexture(0, true));
        player.isPlayer(true);

        createControllers();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderer.setProjectionMatrix(camera.combined);

        PowerupsController.actions();
        InputController.inputProcessing();
        IIPlayer.actions();


        batch.begin();
        TanksController.drawTanks(batch);

        drawMap();

        AnimationsController.draw(batch);
        AnimationsController.update();

        ShellsController.drawShells(batch);
        TanksController.update();
        PowerupsController.draw(batch);

        batch.end();


        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GRAY);
        renderer.rect(-15, 0, 15, 800);
        renderer.rect(650, 0, 135, 800);
        renderer.rect(-15, -15, 800, 15);
        renderer.rect(-15, 650, 800, 15);
        renderer.end();

        batch.begin();
        GameInfoView.draw(batch);
        checkGameOver();
        batch.end();

        //frames++;
        //long time = System.currentTimeMillis() - start;
        //System.out.println(1000 * ((double)frames / (double) time));
    }


    public static int getMapNumber() {
        return 0;
    }


    private void createControllers() {
        GameOverView.create();
        GameInfoView.create();
        WorldController.create(arr, 650);
        TanksController.create(player);
        InputController.create(player, game);
        AnimationsController.create();
        PowerupsController.create();
        ScoreController.create();
        ShellsController.create();
    }


    private void drawMap() {
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
    }


    private void checkGameOver() {
        if (!isEnd) {
            if (WorldController.isEnd() || TanksController.isEnd()) {
                InputController.setCanMove(false);
                IIPlayer.setStopActions(true);
                isEnd = true;

                if (WorldController.isEnd()) {
                    emblem = new Texture("other/homeDest.png");
                    AnimationsController.add(new Animation(AnimImages.getBigBang(), 2, 1.5f, 280, -20));
                }

                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        nextScreen = true;
                    }
                }, 3000);
            } else if (IIPlayer.getTanksLeftCount() == 0) {
                winGame = true;
                isEnd = true;

                (new Timer()).schedule(new TimerTask() {
                    @Override
                    public void run() {
                        nextScreen = true;
                    }
                }, 3000);
            }
        } else {
            if (!winGame) {
                GameOverView.draw(batch);
            }

            if (nextScreen) {
                IIPlayer.dispose();
                PowerupsController.dispose();

                game.setScreen(new ResultScreen(game));
            }
        }
    }
}