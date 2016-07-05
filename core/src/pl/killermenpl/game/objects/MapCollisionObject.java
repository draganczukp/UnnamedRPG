/**
 * 
 */
package pl.killermenpl.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.renderers.DebugShapeRenderer;

/**
 * @author killermenpl
 *
 */
public class MapCollisionObject extends GameObject {

	public MapCollisionObject(Vector2 pos) {
		super("collision", pos);
	}

	public void init() {
		//super.init();
		int tilesY = (int) this.pos.y;
		int posY = tilesY * 32;
		
		this.pos.y = posY;
		pos.x *= 32;

		this.sprite = AssetManager.get("emptyPixel").asSprite();
		
		box = new Rectangle(pos.x, pos.y, 32, 32);
	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		DebugShapeRenderer.drawRectangle(box);
//		batch.draw(AssetManager.get("TEMP_STATIC_OBJECT").asSprite(), box.x, box.y, box.width, box.height);
	}
}
