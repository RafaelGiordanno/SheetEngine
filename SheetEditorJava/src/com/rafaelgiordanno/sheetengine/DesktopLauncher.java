package com.rafaelgiordanno.sheetengine;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.title = "SheetEditor";
		config.resizable = false;
		config.width = 16 * 40;
		config.height = 9 * 40;
		new LwjglApplication(new SheetEditor(), config);
	}
}