package pl.killermenpl.game.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import pl.killermenpl.game.log.Log;
import pl.killermenpl.game.log.LogLevel;
import pl.killermenpl.game.objects.NPC;
import pl.killermenpl.game.util.GameUtils;

public class DialogWindow extends GuiElement {
	protected FileHandle jsonPath;

	protected HashMap<String, String> jsonMap;
	protected String jsonResponses;
	protected HashMap<String, String> textLines;
	protected HashMap<String, DialogResponse> responses;

	protected String currentDialog = "default";

	/**
	 * flag to tell if any windows is open;
	 */
	public static boolean isAnyOpen = false;

	public static DialogWindow open;
	public boolean isOpen = false;
	public boolean justClosed = false;

	public DialogWindow(String name, NPC npc) {
		super(name, new Vector2(10, 10), (Gdx.graphics.getWidth() - 16) / 16, 9, 16, 16);
		jsonPath = Gdx.files.internal("npc/" + npc.getName() + "_" + text + "_lines.json");
	}

	/**
	 * @return
	 */
	int a = 0;
	private HashMap<String, DialogResponse> getResponses() {
		System.out.println(a++);
		//System.out.println("getting responses");
		HashMap<String, DialogResponse> map = new HashMap<>();
		System.out.println(this.text);
		HashMap<String, String> responses = GameUtils.jsonToMap(jsonResponses);

		Set<String> keys = responses.keySet();
		//System.out.println(keys.toString());
		for (String key : keys) {
			System.out.println(key);
			//System.out.println(responses.get(key));
			map.put(key, DialogResponse.fromMap(responses, this));
		}

		return map;
	}

	@Override
	public DialogWindow init() {
		// GameUtils.JSON.
		super.init();
		if (!jsonPath.exists()) {
			if (!jsonPath.parent().exists())
				jsonPath.parent().mkdirs();
			try {
				jsonPath.file().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		jsonMap = GameUtils.jsonToMap(jsonPath.readString());
		jsonResponses = jsonMap.get("responses");
		//System.out.println(jsonResponses);
		textLines = GameUtils.jsonToMap(jsonMap.get("lines"));

		responses = getResponses();
		return this;
	}

	@Override
	public void render(SpriteBatch batch, float dt, BitmapFont font, GlyphLayout layout) {
		if (isOpen) {

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

			//String dialog = textLines.get(currentDialog);
//			if (dialog != null) {
//				font.draw(batch, dialog, this.pos.x + 16, pos.y);
//			}
			System.out.println(currentDialog);
			System.out.println(responses.toString());
			System.out.println(responses.containsKey(currentDialog));
			
			responses.get(currentDialog).render(batch, dt, font, layout);
//			System.out.println(this.pos);
		}
		justClosed = false;
	}

	public void close() {
		isAnyOpen = false;
		isOpen = false;
		open = null;
		Log.log(LogLevel.DEBUG, "Closing Dialog");
		justClosed = true;
	}

	public void open() {
		if (justClosed) {
			return;
		}
		if (isAnyOpen || isOpen) {
			Log.log(LogLevel.WARNING, "Tried to open dialog windows while another one was already open");
		} else {
			isOpen = true;
			isAnyOpen = true;
			Log.log(LogLevel.DEBUG, "Opening dialog windows: " + this.text);
			open = this;
		}
	}

}
