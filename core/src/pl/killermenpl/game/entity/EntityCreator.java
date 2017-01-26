package pl.killermenpl.game.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.entity.component.AccelerationComponent;
import pl.killermenpl.game.entity.component.NameComponent;
import pl.killermenpl.game.entity.component.PositionComponent;
import pl.killermenpl.game.entity.component.TextureComponent;
import pl.killermenpl.game.entity.component.VelocityComponent;
import pl.killermenpl.game.log.Log;
import pl.killermenpl.game.log.LogLevel;

public class EntityCreator {
	// public static String filename = "";

	public static Entity createEntity(String file) {
		Entity ent = new Entity();

//		Json json = new Json();

		JsonValue jsonVal = new JsonReader().parse(Gdx.files.internal(file).read());

		ent.add(new NameComponent(jsonVal.get("name").asString()));

		JsonValue components = jsonVal.get("components");

		for (JsonValue comp : components.iterator()) {
			switch (comp.name) {
			case "vel":
				Vector2 vel = new Vector2();
				vel.x = comp.getFloat(0);
				vel.y = comp.getFloat(1);
				ent.add(new VelocityComponent(vel));
				break;
			case "pos":
				Vector2 pos = new Vector2();
				pos.x = comp.getFloat(0);
				pos.y = comp.getFloat(1);
				ent.add(new PositionComponent(pos));
				break;
			case "acc":
				Vector2 acc = new Vector2();
				acc.x = comp.getFloat(0);
				acc.y = comp.getFloat(1);
				ent.add(new AccelerationComponent(acc));
				break;
			case "texture":
				String name = comp.asString();
				Sprite sprite = AssetManager.get(name).asSprite();
				ent.add(new TextureComponent(sprite));
				break;
			default:
				Log.log(LogLevel.INFO, "No component with name: " + comp.name);
				break;
			}
		}
		return ent;
	}
}
