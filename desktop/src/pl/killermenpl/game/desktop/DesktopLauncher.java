package pl.killermenpl.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import pl.killermenpl.game.UnnamedRPG;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 800;
		config.height = 600;

		config.fullscreen = false;

		config.resizable = false;
		
		//config.useGL30 = true;
		
		System.out.println("Starting game with default setting. The proper settings will load in a second");

		new LwjglApplication(new UnnamedRPG(), config);
	}
}
