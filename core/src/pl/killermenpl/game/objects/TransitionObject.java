package pl.killermenpl.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.screens.PlayingScreen;
import pl.killermenpl.game.world.Worlds;

public class TransitionObject extends StaticObject {

	public MapProperties props;
	public Vector2 targetPos;

	public TransitionObject(MapProperties props) {
		super("transition", new Vector2(props.get("x", Float.class), props.get("y", Float.class)));
		this.props = props;
		this.targetPos = new Vector2(Float.parseFloat(props.get("posX", String.class)),
				Integer.parseInt(props.get("mapheight", String.class)) * 32 - Float.parseFloat(props.get("posY", String.class)));
	}

	@Override
	public void init() {
		// super.init();
		// float tilesY = this.pos.y;
		// float posY = tilesY * 32f;

		// this.pos.y = posY;
		// pos.x *= 32f;

		this.sprite = AssetManager.get("emptyPixel").asSprite();

		box = new Rectangle(pos.x, pos.y, props.get("width", Float.class), props.get("height", Float.class));

		if (!props.containsKey("posX"))
			props.put("posX", "0");
		if (!props.containsKey("posY"))
			props.put("posY", "0");
	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		if (box.contains(GameObjectManager.getPlayerObject().box)
				|| box.overlaps(GameObjectManager.getPlayerObject().box)) {
			// GameObjectManager.getPlayerObject().setPosition(targetPos);
			// System.out.println(mapProperties.get("posY")+" :
			// "+GameObjectManager.getPlayerObject().pos.y);
			PlayingScreen.setWorld(Worlds.get(props.get("map", String.class)).setPos(targetPos));
		}
	}

	@Override
	public String toString() {
		return "TransitionObject [pos=" + pos + ", box=" + box + "]";
	}
}
