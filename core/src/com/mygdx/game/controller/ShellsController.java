package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Shell;

import java.util.LinkedList;

/**
 * Created by Алексей on 14.04.2016.
 */

public class ShellsController {
    private static LinkedList<Shell> shells;


    public ShellsController() {
        this.shells = new LinkedList<>();
    }


    public static void drawShells(SpriteBatch batch) {
        for (int i = 0; i < shells.size(); i++) {
            if (shells.get(i).draw(batch) == false) {
                shells.remove(i);
            }
        }
    }


    public static void addShell(Shell newShell) {
        shells.add(newShell);
    }
}
