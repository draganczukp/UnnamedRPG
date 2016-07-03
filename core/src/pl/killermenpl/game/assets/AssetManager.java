package pl.killermenpl.game.assets;

import java.util.HashMap;

import com.badlogic.gdx.utils.Array;

import pl.killermenpl.game.assets.Asset.AssetType;
import pl.killermenpl.game.log.Log;
import pl.killermenpl.game.log.LogLevel;

public class AssetManager {


	private static HashMap<String, Asset> assets = new HashMap<>();

	private static HashMap<AssetCategory, Array<AssetDescriptor>> descriptors = new HashMap<>();

	private static Array<AssetCategory> loadGroups;

	public static void loadAll(){
		// Old Code. Does not work properly.
		// for (AssetDescriptor asset : descriptors.values()) {
		// if (assets.containsKey(asset.name)) {
		// Log.log(LogLevel.CRITICAL, "Asset with name " + asset.name + " is
		// allready loaded! Aborting!");
		// System.exit(0);
		// } else
		// Log.log(LogLevel.INFO, "Loading Asset " + asset.name);
		// assets.get(asset.group).put(asset.name, new Asset(asset.type,
		// asset.path));
		// }

		assets.clear();

		for(AssetCategory group : loadGroups){
			for(AssetDescriptor asset : descriptors.get(group)){
				// should work without
				// if (!assets.containsKey(group))
				// assets.put(group, new HashMap<>());

				// HashMap<String, Asset> map = assets.get(group);

				if(assets.containsKey(asset.name)){
					Log.log(LogLevel.INFO, "Asset with name " + asset.name + " is already loaded! Skipping!");
					continue;
				}

				assets.put(asset.name, new Asset(asset.type, asset.path));
			}
		}
		assets.put("emptyPixel", new Asset(AssetType.SPRITE, "emptyPixel"));
	}

	public static void addToLoad(AssetDescriptor asset){

		if(descriptors.get(asset.group) == null) descriptors.put(asset.group, new Array<>());

		descriptors.get(asset.group).add(asset);

		Log.log(LogLevel.INFO, "Adding Asset to load. Asset: " + asset.toString());
		// toLoad.add(asset);
	}

	public static void setGroups(AssetCategory... groups){
		loadGroups = new Array<>(groups);
		loadGroups.add(AssetCategory.MAIN);
		loadGroups.add(AssetCategory.GUI);
	}
	// public static void addToLoad(AssetType type, String name, String path) {
	// toLoad.add(new AssetDescriptor(type, name, path));
	// }

	/**
	 * 
	 * @param name
	 *            Asset.name
	 * @return Asset
	 */
	public static Asset get(String name){
		return assets.get(name);
	}

	public static void load(AssetDescriptor descriptor){
		Log.log(LogLevel.DEBUG, "Loading on-demand asset: "+descriptor.toString());
		Asset asset = new Asset(descriptor.type, descriptor.path);
		assets.put(descriptor.name, asset);
	}

}
