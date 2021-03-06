package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Shell;

import java.util.LinkedList;

/**
 * Created by ������� on 14.04.2016.
 */

public class ShellsController {
    private static LinkedList<Shell> shells;


    public static void create() {
        shells = new LinkedList<Shell>();
    }


    public static void drawShells(SpriteBatch batch) {
        checkShellsCollision();

        for (int i = 0; i < shells.size(); i++) {
            if (shells.get(i).draw(batch) == false) {
                shells.remove(i);
            }
        }
    }


    public static void addShell(Shell newShell) {
        shells.add(newShell);
    }


    private static void checkShellsCollision() {
        for (int i = 0; i < shells.size(); i++) {
            if (shells.get(i).getOwner().equals(TanksController.getPlayer())) {
                Shell myShell = shells.get(i);

                for (int j = 0; j < shells.size(); j++) {
                    if ((int)(shells.get(j).getX()/25) == (int)(myShell.getX()/25))
                        if ((int)(shells.get(j).getY()/25) == (int)(myShell.getY()/25))
                            if (!myShell.getOwner().equals(shells.get(j)))
                                if (((myShell.getDir() + 2) % 4) == shells.get(j).getDir()) {
                                    shells.remove(Math.max(i, j));
                                    shells.remove(Math.min(i, j));
                                }
                }
            }
        }
    }

}
