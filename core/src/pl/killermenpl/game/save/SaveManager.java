package pl.killermenpl.game.save;

import java.io.StringWriter;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import pl.killermenpl.game.inventory.Inventory;
import pl.killermenpl.game.inventory.InventoryData;
import pl.killermenpl.game.world.World;
import pl.killermenpl.game.world.WorldData;

public class SaveManager {

	private static SaveManager instance;

	private Json json;
	private Array<WorldData> worlds;
	private Array<InventoryData> inventories;
	
	
	
	public SaveManager(){
		json  = new Json(OutputType.json);
		worlds = new Array<>();
		inventories = new Array<InventoryData>();
	}
	
	
	public static SaveManager get(){
		if(instance==null){
			instance = new SaveManager();
		}
		
		return instance;
	}
	
	
	public void save(World w){
		WorldData data = WorldData.fromWorld(w);
		
		worlds.add(data);
	}
	
	public void save(Inventory i){
		InventoryData inv = InventoryData.fromInventory(i);
		inventories.add(inv);
	}
	
	
	public String saveAll(){
		StringWriter stringWriter = new StringWriter();
		json.setWriter(stringWriter);
		json.writeObjectStart();
		json.writeFields(worlds);
		json.writeFields(inventories);
		json.writeObjectEnd();
		worlds.clear();
		inventories.clear();
		return stringWriter.toString();
	}
}
