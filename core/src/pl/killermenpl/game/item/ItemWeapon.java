package pl.killermenpl.game.item;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.TimeUtils;

import pl.killermenpl.game.log.Log;
import pl.killermenpl.game.log.LogLevel;
import pl.killermenpl.game.objects.Cone;
import pl.killermenpl.game.objects.GameObject;
import pl.killermenpl.game.objects.LivingObject;
import pl.killermenpl.game.objects.PlayerObject;
import pl.killermenpl.game.screens.PlayingScreen;

public class ItemWeapon extends Item {
	public enum WeaponType{
		FISTS, DAGGER, SHORTSWORD,
		// everything is balanced around that
		ARMINGSWORD, LONGSWORD, MACE, WARAXE, SPEAR, HALBERD, BOW, CROSSBOW;
	}

	public Circle circle = new Circle();
	public Cone cone;
	public float baseDamage;
	/** seconds between each attack */
	public float baseAttackDelay;

	public float baseRange;

	public float damage, attackDelay, range;

	public WeaponType type;
	public GameObject gameObject;

	public static long lastAttack = System.currentTimeMillis();

	public ItemWeapon(String name, float dmg, float delay, float range){
		this.name = name;
		this.damage = dmg;
		this.attackDelay = delay;
		this.range = range;
	}

	@Override
	public void update(float dt){
		circle.set(gameObject.center(), range);
		cone = ((LivingObject) gameObject).cone;
		cone.range = this.range;
		// System.out.println(range);
		super.update(dt);
	}

	public void attack(GameObject gameObject){
		long time = TimeUtils.millis();

		if(time - lastAttack > attackDelay){
			lastAttack = time;
			Log.log(LogLevel.DEBUG, "Attack!");

			for(GameObject ob : PlayingScreen.world.objects.get()){
				if(!(ob instanceof LivingObject) || ob instanceof PlayerObject) continue;
				LivingObject lObject = (LivingObject) ob;

				float[] rectVertices = new float[]{ob.box.x, ob.box.y, ob.box.x + ob.box.height,
						ob.box.x + ob.box.width, ob.box.x + ob.box.width, ob.box.y + ob.box.height };
				if(Intersector.overlaps(circle, lObject.box)
						&& Intersector.overlapConvexPolygons(cone.getVertices(), rectVertices, null)){
					Log.log(LogLevel.DEBUG, ob.getName());
					lObject.modStat("hp", -this.damage);
					System.out.println(lObject.getStat("hp"));
				}
			}
		}
	}

}
