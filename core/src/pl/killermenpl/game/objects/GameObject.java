package pl.killermenpl.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.renderers.DebugShapeRenderer;

public abstract class GameObject {

	/**
	 * Used to get Sprite from AssetManager.
	 */
	private String name;
	/**
	 * The position to draw it. I wish SpriteBatch accepted Vectors
	 */
	protected Vector2 pos;

	/**
	 * It will get value from AssetManager in init(). It is just easier to call
	 * batch.draw(sprite, x, y) than
	 * batch.draw(AssetManager.get("asset").asSprite() every time
	 */
	protected Sprite sprite;
	
	// protected AssetCategory assetCategory;
	
	public Rectangle box;
	public static Vector2 mousePosition;

	// protected Inventory inventory;

	public GameObject(String name, Vector2 pos) {
		setName(name);
		this.pos = pos;
	}

	public void init() {
		sprite = AssetManager.get(getName()).asSprite();
		box = new Rectangle(pos.x, pos.y, 18, 30);
		// inventory = new Inventory();
	}

	public void render(SpriteBatch batch, float dt) {
		batch.draw(sprite, pos.x, pos.y);
		DebugShapeRenderer.drawRectangle(box);
		if(GameObjectManager.getPlayerObject().inventory.isVisible()) return;
	}

	public GameObject setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public GameObject setPosition(Vector2 pos) {
		this.pos = pos;
		return this;
	}

	public void interract() {
		System.out.println("Player Interract");
	}

	public void setMousePosition(Vector2 mousePosition2){
		mousePosition = mousePosition2;
	}

	public void dispose() {
		box = null;
		mousePosition = null;
		setName(null);
		pos = null;
		sprite = null;
	}

	public Vector2 center(){
		return this.box.getCenter(Vector2.X);
	}

	public Vector2 getPos() {
		// TODO Auto-generated method stub
		return this.pos;
	}

}
