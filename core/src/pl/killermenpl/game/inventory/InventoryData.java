package pl.killermenpl.game.inventory;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;

import pl.killermenpl.game.item.ItemData;

public class InventoryData {
	
	
	public String inventoryName;
	public Array<ItemData> items;
	public Map<String, ItemData> eq;
	
	public static InventoryData fromInventory(Inventory i) {
		InventoryData inv = new InventoryData();
		inv.inventoryName = i.getName();
		inv.items = new Array<ItemData>();
		
		i.getItems().getButtons().forEach((display)->{
			inv.items.add(ItemData.fromItem(display.getItem()));
		});
		
		if(i instanceof CharacterInventory){
			inv.eq = new HashMap<>();
			CharacterInventory ci = (CharacterInventory) i;
			inv.eq.put("head", 		ItemData.fromItem(ci.head));
			inv.eq.put("weapon",	ItemData.fromItem(ci.weapon));
			inv.eq.put("body", 		ItemData.fromItem(ci.body));
			inv.eq.put("arms", 		ItemData.fromItem(ci.arms));
			inv.eq.put("legs", 		ItemData.fromItem(ci.legs));
		}
		
		return inv;
	}
	
	
	
	
}
