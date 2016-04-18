/**
 *
 */
package pl.killermenpl.game.gui;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * @author killermenpl
 *
 */
public class DialogResponse extends GuiButton {
	protected DialogWindow dialog;

	protected DialogResponse(String txt, float pos, float height, String lineId) {
		// float width = Gdx.graphics.getWidth() - 10;
		this(txt, new Vector2(5, pos), Gdx.graphics.getWidth() - 10, height, () -> {
		});
	}

	public DialogResponse(String txt, Vector2 vector2, float width, float height, IGuiAction a) {
		super(txt, vector2, width, height, a);
	}

	public static DialogResponse fromMap(HashMap<String, String> map, DialogWindow dialog) {
		String txt = map.get("txt");
		System.out.println(txt);
		float pos = Float.parseFloat(map.get("pos"));
		// float width = Float.parseFloat(map.get("width"));
		float height = 0;
		String[] lines = txt.split(":");
		height = lines.length;
		String lineId = map.get("lineId");
		DialogResponse response = new DialogResponse(txt, pos, height, lineId);

		return response;
	}
}
