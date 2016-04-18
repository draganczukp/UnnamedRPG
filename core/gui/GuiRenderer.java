package pl.killermenpl.game.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.killermenpl.game.gui.GuiManager;

public class GuiRenderer {
	private static SpriteBatch batch = new SpriteBatch();

	private static GuiManager mngr;

	public static void setGui(GuiManager gui) {
		mngr = gui;
	}

	public static void render(float dt) {
		batch.begin();

		mngr.render(batch, dt);

		batch.end();
	}

}
