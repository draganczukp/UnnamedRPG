package pl.killermenpl.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GuiManager {

	private Array<GuiElement> elements = new Array<>();
	public static BitmapFont font = new BitmapFont();
	public static GlyphLayout layout = new GlyphLayout();

	public void initAll() {
		for (GuiElement e : elements) {
			e.init();
		}
	}

	public void render(SpriteBatch batch, float dt) {

		Vector2 mousePos = new Vector2();

		if (Gdx.input.justTouched()) {
			mousePos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		}

		for (GuiElement e : elements) {
			e.render(batch, dt, font, layout);
			if (!(e instanceof IClickable))
				continue;
			else if (e.box.contains(mousePos)) {
				((IClickable) e).getAction().action();
			}
		}
	}

	public void addElement(GuiElement element) {
		elements.add(element);
	}

}
