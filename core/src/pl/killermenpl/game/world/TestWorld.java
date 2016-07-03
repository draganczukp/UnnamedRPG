package pl.killermenpl.game.world;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;

import pl.killermenpl.game.objects.GameObjectData;
import pl.killermenpl.game.objects.GameObjectData.GameObjectType;
import pl.killermenpl.game.objects.LivingObject;
import pl.killermenpl.game.objects.NPC;
import pl.killermenpl.game.objects.PlayerObject;
import pl.killermenpl.game.objects.StaticObject;

public class TestWorld extends World {

	private NPC testNPC;

	@Override
	public TestWorld init() {
		this.mapName = "castle2";
		super.init();
		return this;
	}

	@Override
	public void addObjects() {

		objects.addObject(new PlayerObject(new Vector2(1500, 600)).setManager(objects));
		testNPC = new NPC("testNpc", new Vector2(850, 650));
		objects.addObject(testNPC);

//		WorldData test = new WorldData();
//		test.setMapName(this.mapName);
//		ArrayList<GameObjectData> objects = new ArrayList<>();
//		this.objects.get().forEach((ob) -> {
//			GameObjectData tmp = new GameObjectData();
//
//			tmp.setName(ob.getName());
//			tmp.setPos(ob.getPos());
//			if (ob instanceof PlayerObject)
//				return;
//			else if (ob instanceof LivingObject)
//				tmp.setType(GameObjectType.LIVING);
//			else if (ob instanceof StaticObject)
//				tmp.setType(GameObjectType.STATIC);
//			else
//				tmp.setType(GameObjectType.BASE);
//
//			objects.add(tmp);
//		});
//
//		test.setGameObjects(objects);
//		Json json = new Json();
//
//		FileHandle file = Gdx.files.local("maptest.json");
//
//		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file.file()))) {
//			writer.write(json.prettyPrint(test));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	// @Override
	// public void addGuiElements() {
	//
	// }

	@Override
	public boolean keyDown(int key) {
		// System.out.println(key);
		if (key == Keys.NUMPAD_0) {
			testNPC.setDest(new Vector2(800, 400));
		}
		return super.keyDown(key);
	}

}
