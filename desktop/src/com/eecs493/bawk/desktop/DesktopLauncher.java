package com.eecs493.bawk.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.eecs493.bawk.BawkGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Bawk";
        config.width = 480;
        config.height = 800;
		new LwjglApplication(new BawkGame(), config);
	}
}
