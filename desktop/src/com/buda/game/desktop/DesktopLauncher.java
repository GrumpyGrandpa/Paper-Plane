package com.buda.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.buda.game.PapePlanes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PapePlanes.WIDTH;
		config.height=PapePlanes.HEIGHT;
		config.title=PapePlanes.TITLE;
		new LwjglApplication(new PapePlanes(), config);
	}
}
