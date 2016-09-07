package pl.killermenpl.game.world;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import pl.killermenpl.game.objects.NPC;
import pl.killermenpl.game.objects.PlayerObject;

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
	}

	@Override
	public boolean keyDown(int key) {
		// System.out.println(key);
		if (key == Keys.NUMPAD_0) {
			testNPC.setDest(new Vector2(800, 400));
		}
		return super.keyDown(key);
	}

}
