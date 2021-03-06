package pl.killermenpl.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import pl.killermenpl.game.objects.GameObjectManager;

public class MapRenderer extends OrthogonalTiledMapRenderer {

	public MapRenderer(TiledMap map){
		super(map);
	}

	public void render(GameObjectManager objects, float dt){
		// super.render();
		beginRender();
		for(int i = 0; i < map.getLayers().getCount(); i++){
			MapLayer layer = map.getLayers().get(i);
			if(layer.isVisible()){
				if(layer.getName().equals("gameObjectLayer")){
					objects.render((SpriteBatch) this.batch, dt);
					continue;
				}
				if(layer instanceof TiledMapTileLayer){
					renderTileLayer((TiledMapTileLayer) layer);
				}

				if(layer instanceof TiledMapImageLayer){
					renderImageLayer((TiledMapImageLayer) layer);
				}else{
					renderObjects(layer);
				}
			}
		}
		endRender();

	}
}
