package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BattleCity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Battle city";
		config.width = 650;
		config.height = 650;
		config.resizable = false;
		//config.fullscreen = true;
		new LwjglApplication(new BattleCity(), config);
	}
}
