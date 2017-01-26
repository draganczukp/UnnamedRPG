/**
 * 
 */
package pl.killermenpl.game.world;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import pl.killermenpl.game.log.Log;
import pl.killermenpl.game.log.LogLevel;

public class Worlds {

	public static final World IN_CITY_1 = new TestWorld();

	public static HashMap<String, World> worldMap = new HashMap<>();

	static {
		worldMap.put("in_city_1", IN_CITY_1);
		
	}

	public static World get(String worldName) {
		if (worldMap.get(worldName) != null){
			return worldMap.get(worldName);
		}
		Log.log(LogLevel.CRITICAL, "No map with name "+worldName+"exists! Aborting");
		Gdx.app.exit();
		return IN_CITY_1;
	}

	public static void loadAll(){
		Json json = new Json();

		FileHandle dir = Gdx.files.local("worlds/");
//		System.out.println(dir.file().getAbsolutePath());
		System.out.println(Gdx.files.getLocalStoragePath());
		for(FileHandle file : dir.list("json")){
			WorldData worldData = json.fromJson(WorldData.class, file.readString());
			Log.log(LogLevel.DEBUG, "Adding world: " + file.nameWithoutExtension()+"\n"+worldData.toString());
			worldMap.put(file.nameWithoutExtension(), worldData.toWorld());
		}

	}
}
