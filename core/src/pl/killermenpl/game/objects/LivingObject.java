package pl.killermenpl.game.objects;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.inventory.CharacterInventory;
import pl.killermenpl.game.renderers.DebugShapeRenderer;
import pl.killermenpl.game.shapes.Cone;

public class LivingObject extends GameObject {
	public static enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	protected HashMap<String, Object> stats = new HashMap<String, Object>();

	protected float speed = 50;
	protected Vector2 dest;
	public Vector2 dir;
	protected Vector2 vel;
	protected Vector2 mov;
	public Direction facing = Direction.UP;

	protected ITempAI action;

	protected static NinePatchDrawable hpBar, blackBar;

	public CharacterInventory inventory;

	public Cone cone = new Cone(this);

	protected TextureRegion[][] frames;
	protected Animation upAnim, downAnim, leftAnim, rightAnim;
	protected TextureRegion up, down, left, right;
	protected float stateTime = 0;

	protected boolean isMovingX, isMovingY = false;

	public LivingObject(String name, Vector2 pos) {
		super(name, pos);
		dest = pos;
		dir = new Vector2();
		vel = new Vector2();
		mov = new Vector2();
	}

	public LivingObject setAction(ITempAI action) {
		this.action = action;
		return this;
	}

	@Override
	public void init() {
		super.init();

		stats.put("hp", 100f);
		stats.put("maxhp", 100f);
		stats.put("invincible", false);
		if (hpBar == null) {
			hpBar = new NinePatchDrawable(new NinePatch(AssetManager.get("hpBar").asSprite(), 0, 0, 0, 0));
			blackBar = new NinePatchDrawable(new NinePatch(AssetManager.get("blackBar").asSprite(), 0, 0, 0, 0));
		}
		// cone = new Polygon();

		frames = sprite.split(32, 64);

		upAnim = new Animation(0.1f, frames[1][1], frames[1][2], frames[1][3]);
		downAnim = new Animation(0.1f, frames[0][1], frames[0][2], frames[0][3]);
		leftAnim = new Animation(0.1f, frames[0][5], frames[0][6], frames[0][7]);
		rightAnim = new Animation(0.1f, frames[1][5], frames[1][6], frames[1][7]);

		upAnim.setPlayMode(PlayMode.LOOP);
		downAnim.setPlayMode(PlayMode.LOOP);
		leftAnim.setPlayMode(PlayMode.LOOP);
		rightAnim.setPlayMode(PlayMode.LOOP);

		up = frames[1][1];
		down = frames[0][1];
		left = frames[0][5];
		right = frames[1][5];

	}

	public void drawAnimations(SpriteBatch batch, float dt) {
		if (isMovingX || isMovingY) {
			stateTime += dt;
			switch (facing) {
			case DOWN:
				batch.draw(downAnim.getKeyFrame(stateTime), pos.x, pos.y);
				break;
			case LEFT:
				batch.draw(leftAnim.getKeyFrame(stateTime), pos.x, pos.y);
				break;
			case RIGHT:
				batch.draw(rightAnim.getKeyFrame(stateTime), pos.x, pos.y);
				break;
			case UP:
				batch.draw(upAnim.getKeyFrame(stateTime), pos.x, pos.y);
				break;
			default:
				break;

			}
		} else {
			switch (facing) {
			case DOWN:
				batch.draw(down, pos.x, pos.y);
				break;
			case LEFT:
				batch.draw(left, pos.x, pos.y);
				break;
			case RIGHT:
				batch.draw(right, pos.x, pos.y);
				break;
			case UP:
				batch.draw(up, pos.x, pos.y);
				break;
			default:
				break;

			}
		}

	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		// super.render(batch, dt);
		if (action != null)
			action.action();
		move(dt);

		box.setPosition(pos);
		box.y += 7;
		box.x += 7;

		cone.updateCone();
		// System.out.println(cone.getVertices());
		DebugShapeRenderer.drawShape(cone);
		DebugShapeRenderer.drawRectangle(box);
		drawAnimations(batch, dt);
		blackBar.draw(batch, box.getCenter(Vector2.X).x - (0.5f * this.<Float>getStat("maxhp")) / 2, pos.y + 50,
				0.5f * this.<Float>getStat("maxhp"), 3);
		hpBar.draw(batch, box.getCenter(Vector2.X).x - (0.5f * this.<Float>getStat("maxhp")) / 2, pos.y + 50,
				0.5f * this.<Float>getStat("hp"), 3);
	}

	public void setDest(Vector2 destination) {
		dest = destination;
	}

	public void move(float dt) {
		if (dest.dst(pos) <= 1)
			return;
		dir.set(dest).sub(pos).nor();
		vel = new Vector2(dir).scl(speed);
		mov.set(vel).scl(dt);
		pos.add(mov);
	}

	@SuppressWarnings("unchecked")
	public <T> T getStat(String name) {
		return (T) stats.get(name);
	}

	public void setStat(String name, Object value) {
		stats.put(name, value);
	}

	public void modStat(String statname, float amount) {
		Object tmp1;
		if ((tmp1 = stats.get("max" + statname)) != null) {
			float tmp2 = (float) tmp1;
			float curr = (float) stats.get(statname);
			if (curr + amount >= tmp2) {
				stats.put(statname, (float) tmp2);
				return;
			}
			if (curr + amount <= 0) {
				stats.put(statname, 0f);
				return;
			}
		}

		float stat = (float) stats.get(statname);
		stat += amount;
		stats.put(statname, stat);
	}

	public void damage(float cDamage) {
		// System.out.println(cDamage);
		if (!this.<Boolean>getStat("invincible"))
			this.modStat("hp", -cDamage);
	}

	public HashMap<String, Object> getStats() {
		return this.stats;
	}
}
