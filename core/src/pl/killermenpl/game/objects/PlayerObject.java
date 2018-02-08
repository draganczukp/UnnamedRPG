package pl.killermenpl.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import pl.killermenpl.game.config.Config;
import pl.killermenpl.game.inventory.CharacterInventory;
import pl.killermenpl.game.item.Item;
import pl.killermenpl.game.item.ItemWeapon;
import pl.killermenpl.game.log.Log;
import pl.killermenpl.game.log.LogLevel;
import pl.killermenpl.game.renderers.DebugShapeRenderer;

public class PlayerObject extends LivingObject {
	private Rectangle facingBox = new Rectangle();

	/**
	 * Easy acces for Collision detection and interaction
	 */
	private GameObjectManager manager;
	// public Inventory inventory;

	public PlayerObject(Vector2 pos) {
		super("PLAYER_SPRITE_SHEET", pos);
	}

	public GameObject setManager(GameObjectManager manager) {
		this.manager = manager;
		return this;
	}

	@Override
	public void init() {
		// setAssetCategory(AssetCategory.MAIN);

		super.init();

		if (inventory == null)
			inventory = new CharacterInventory();

		// TODO: Read from save
		stats.put("maxhp", 100f);
		stats.put("maxmp", 100f);

		stats.put("hp", 75f);
		stats.put("mp", 75f);

		inventory.weapon = (ItemWeapon) Item.testDaggerWeapon;
		inventory.weapon.gameObject = this;
	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		box.setPosition(pos);
		box.y += 7;
		box.x += 7;
		// box.height -= 20;
		Input in = Gdx.input;
		processInput(in);
		drawAnimations(batch, dt);
		move(dt);
		cone.updateCone();
		DebugShapeRenderer.drawShape(cone);
		DebugShapeRenderer.drawRectangle(box);
		DebugShapeRenderer.drawCircle(inventory.weapon.circle);
		// System.out.println(dir.angle());
	}

	public void processInput(Input in) {
		if (inventory.isVisible())
			return;

		Vector2 tmp = Vector2.X.setZero();

		if (in.isKeyPressed(Config.keyLeft)) {
			tmp.x = -1;
			facing = Direction.LEFT;
			isMovingX = true;
			dir.setAngle(0);
		} else if (in.isKeyPressed(Config.keyRight)) {
			tmp.x = 1;
			facing = Direction.RIGHT;
			isMovingX = true;
			dir.setAngle(180);
		} else {
			tmp.x = 0;
			isMovingX = false;
		}

		if (in.isKeyPressed(Config.keyUp)) {
			tmp.y = 1;
			facing = Direction.UP;
			isMovingY = true;
			dir.setAngle(90);
		} else if (in.isKeyPressed(Config.keyDown)) {
			tmp.y = -1;
			facing = Direction.DOWN;
			isMovingY = true;
			dir.setAngle(270);
		} else {
			tmp.y = 0;
			isMovingY = false;
		}
		if (in.isKeyPressed(Keys.SHIFT_LEFT))
			tmp.scl(2);
		moveBy(tmp);

		if (in.isKeyJustPressed(Config.keyInterract)) {
			interact();
		}
		if (in.isKeyJustPressed(Keys.B))
			System.out.println("Player: " + pos.toString());

	}

	public void moveBy(Vector2 dest) {
		if (manager == null) {
			Log.log(LogLevel.CRITICAL, "PlayerObject doesn't know about other Objects!");
			Gdx.app.exit();
		}

		Rectangle tmpX = new Rectangle(box);
		tmpX.x += dest.x;

		Rectangle tmpY = new Rectangle(box);
		tmpY.y += dest.y;

		for (GameObject o : manager.get()) {
			if (o instanceof PlayerObject || o instanceof TransitionObject)
				continue;
			if (tmpX.overlaps(o.box) || tmpX.contains(o.box)) {
				dest.x = 0;
			}
			if (tmpY.overlaps(o.box) || tmpY.contains(o.box)) {
				dest.y = 0;
			}
		}

		if (isMovingX) {
			facingBox = tmpX;
			facingBox.x += (dest.x < 0 ? -10 : 10);
		} else if (isMovingY) {
			facingBox = tmpY;
			facingBox.y += (dest.y < 0 ? -10 : 10);
		}

		setDest(pos.add(dest));
	}

	public Vector3 getPosition3() {
		Vector2 tmp1 = pos.cpy();
		Vector3 tmp2 = new Vector3();

		tmp2.x = tmp1.x + 16;
		tmp2.y = tmp1.y + 34;

		return tmp2;
	}

	public void interact() {
		for (GameObject o : manager.get()) {
			if (o instanceof PlayerObject || !(o instanceof IInteractable))
				continue;

			if (facingBox.overlaps(o.box)) {
				((IInteractable) o).interact();
			}
		}
	}

	/**
	 * @return
	 */
	public Direction getDirection() {
		return this.facing;
	}

	public void attack() {
		if (inventory.weapon != null)
			inventory.weapon.attack();
	}

}
