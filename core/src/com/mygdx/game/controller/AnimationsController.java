package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.anima.Animation;

import java.util.ArrayList;

/**
 * Created by ������� on 24.04.2016.
 */

public class AnimationsController {
    private static ArrayList<Animation> list;


    public static void create() {
        list = new ArrayList<Animation>();
    }


    public static void draw(SpriteBatch batch) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).draw(batch);
        }
    }


    public static void update() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).update(0.2f);
        }

        for (int i = 0; i < list.size(); i++)
            if (list.get(i).isCompleted())
                list.remove(i);
    }


    public static void add(Animation a) {
        list.add(a);
    }
}
