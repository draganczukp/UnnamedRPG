package pl.killermenpl.game.util;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;

public class GameUtils {

	public static final String GAME_DIR = Gdx.files.getLocalStoragePath();

	public static HashMap<String, String> jsonToMap(String t) throws JSONException {

		HashMap<String, String> map = new HashMap<String, String>();
		JSONObject jObject = new JSONObject(t);
		Iterator<?> keys = jObject.keys();

		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value;
			try {
				value = jObject.getString(key);
			} catch (JSONException e) {
				value = jObject.getJSONObject(key).toString();
			}
			map.put(key, value);

		}

		// System.out.println("json : "+jObject);
		// System.out.println("map : "+map);
		return map;
	}

}
