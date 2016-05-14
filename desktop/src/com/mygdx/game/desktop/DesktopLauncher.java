package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BattleCity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Battle city";
		config.width = 800;
		config.height = 680;
		config.resizable = false;
		config.fullscreen = false;
		config.x = 300;
		config.y = 15;
		new LwjglApplication(new BattleCity(), config);
	}
}
