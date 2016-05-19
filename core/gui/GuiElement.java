package pl.killermenpl.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import pl.killermenpl.game.assets.AssetManager;

public class GuiElement {
	@FunctionalInterface
	public interface IGuiAction {
		/**
		 * This is triggered when mouse clicks on the element.
		 */
		public void action();

	}

	protected String text;

	protected Vector2 pos;

	protected Rectangle box;
	protected float width, height;

	protected Sprite spriteSheet;

	protected TextureRegion[][] frames;

	/**
	 *
	 * @param pos
	 *            position in pixels
	 * @param width
	 *            width in tiles
	 * @param height
	 *            height in tiles
	 * @param action
	 *            what will hapen when somebody clicks on this
	 */
	public GuiElement(String txt, Vector2 pos, float width, float height) {
		this(txt, pos, width, height, 16, 16);
	}

	public GuiElement(String txt, Vector2 pos, float width, float height, int tileWidth, int tileHeight) {
		text = txt;
		this.pos = pos;
		this.width = width;
		this.height = height;
		box = new Rectangle(pos.x, pos.y, width * tileWidth, height * tileHeight);
	}

	public GuiElement init() {
		spriteSheet = AssetManager.get("BUTTON_DEFAUL").asSprite();
		frames = spriteSheet.split(16, 16);
		return this;
	}

	public void render(SpriteBatch batch, float dt, BitmapFont font, GlyphLayout layout) {
		Vector2 pos = new Vector2(this.pos);

		if (height == 1) {
			batch.draw(frames[0][3], pos.x, pos.y);
			pos.add(16, 0);
			for (int i = 0; i < width - 2; i++) {
				batch.draw(frames[1][3], pos.x, pos.y);
				pos.add(16, 0);
			}
			batch.draw(frames[2][3], pos.x, pos.y);
		} else {
			batch.draw(frames[2][0], pos.x, pos.y);
			pos.x += 16;
			for (int i = 0; i < width - 2; i++) {
				batch.draw(frames[2][1], pos.x, pos.y);
				pos.x += 16;
			}
			batch.draw(frames[2][2], pos.x, pos.y);
			pos.x = this.pos.x;
			pos.y += 16;
			for (int i = 0; i < height - 2; i++) {
				batch.draw(frames[1][0], pos.x, pos.y);
				pos.x += 16;
				for (int j = 0; j < width - 2; j++) {
					batch.draw(frames[1][1], pos.x, pos.y);
					pos.x += 16;
				}
				batch.draw(frames[1][2], pos.x, pos.y);
				pos.x = this.pos.x;
				pos.y += 16;
			}
			batch.draw(frames[0][0], pos.x, pos.y);
			pos.x += 16;
			for (int i = 0; i < width - 2; i++) {
				batch.draw(frames[0][1], pos.x, pos.y);
				pos.x += 16;
			}
			batch.draw(frames[0][2], pos.x, pos.y);
		}

		layout.setText(font, text);

		Vector2 textPos = new Vector2(this.pos);
		float boxWidth = box.width;
		float boxHeight = box.height;

		textPos.add(boxWidth / 2 - layout.width / 2, boxHeight / 2 - layout.height / 2 + 10);
		// textPos.scl(0.5f);
		font.setColor(Color.WHITE);
		font.draw(batch, text, textPos.x, textPos.y);
		// System.out.println(textPos);
	}
}
