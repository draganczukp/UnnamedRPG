/**
 * 
 */
package pl.killermenpl.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import pl.killermenpl.game.renderers.DebugShapeRenderer;

/**
 * @author killermenpl
 *
 */
public class MapCollisionObject extends GameObject {
	// private World w;

	public MapCollisionObject(Vector2 pos) {
		super("collision", pos);
		// this.w = w;
		// Log.log(LogLevel.DEBUG, "new MapCollisionObject");
	}

	public void init() {
		// setAssetCategory(AssetCategory.MAIN);
		super.init();
		int tilesY = (int) this.pos.y;
		int posY = tilesY * 32;
		//
		// // World w = PlayingScreen.world;
		// //System.out.println(w==null);
		// String s = w.mapName;
		// Asset a = AssetManager.get(s);
		// TiledMap m = a.asMap();
		//
		// //TiledMap map = AssetManager.get(w.mapName).asMap();
		// //System.out.println(map==null);
		//
		// MapLayers layers = m.getLayers();
		// TiledMapTileLayer l = (TiledMapTileLayer) layers.get(0);
		//
		//
		// int mapHeight = l.getHeight();

		int y = posY;

		this.pos.y = y;
		pos.x *= 32;

		box = new Rectangle(pos.x, pos.y, 32, 32);
	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		//super.render(batch, dt);
		DebugShapeRenderer.drawRectangle(box);
	}
}
