package pl.killermenpl.game.world;

import java.util.ArrayList;

import pl.killermenpl.game.objects.GameObjectData;

public class WorldData{

	private String mapName;
	private ArrayList<GameObjectData> gameObjects = new ArrayList<>();
	

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public ArrayList<GameObjectData> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ArrayList<GameObjectData> objects) {
		this.gameObjects = objects;
	}

	public World toWorld(){
		World w = new World() {
			{
				this.mapName = WorldData.this.mapName;
			}
			@Override
			public void addObjects(){
				super.addObjects();
				for(GameObjectData data : gameObjects){
					this.objects.addObject(data.toGameObject());
				}
			}
		};
		return w;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldData [mapName=").append(mapName).append(", gameObjects=").append(gameObjects).append("]");
		return builder.toString();
	}
	

}
