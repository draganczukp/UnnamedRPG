/**
 *
 */
package pl.killermenpl.game.gui;

import com.badlogic.gdx.math.Vector2;

/**
 * @author killermenpl
 *
 */
public class GuiButton extends GuiElement implements IClickable {

	protected IGuiAction action;

	public GuiButton(String txt, Vector2 pos, float width, float height, IGuiAction action) {
		super(txt, pos, width, height);
		this.action = action;
	}

	public GuiButton(String txt, Vector2 pos, float width, float height, int tileWidth, int tileHeight,
			IGuiAction action2) {
		super(txt, pos, width, height, tileWidth, tileHeight);
		action = action2;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see pl.killermenpl.game.gui.IClickable#getAction()
	 */
	@Override
	public IGuiAction getAction() {
		return action;
	}

}
