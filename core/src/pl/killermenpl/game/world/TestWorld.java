package pl.killermenpl.game.world;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import pl.killermenpl.game.objects.NPC;
import pl.killermenpl.game.objects.PlayerObject;

public class TestWorld extends World {

	private NPC testNPC;
	
	@Override
	public TestWorld init() {
		this.mapName = "castle";
		super.init();
		return this;
	}

	@Override
	public void addObjects() {

		//this.objects.addObject(new StaticObject("TEMP_STATIC_OBJECT", new Vector2(10, 10)).setAssetCategory(AssetCategory.TEMP));

//		LivingObject tempLivingObject = new LivingObject("TEMP_STATIC_OBJECT", new Vector2(30, 30));
//		tempLivingObject.setAction(() -> {
//			Input in = Gdx.input;
//			if (in.isTouched()) {
//				tempLivingObject.setDest(new Vector2(mousePosition.x - 16, mousePosition.y - 16));
//			}
//		});
//		
//
//		objects.addObject(tempLivingObject);

		objects.addObject(new PlayerObject(new Vector2(800, 600)).setManager(objects));
		testNPC = new NPC("testNpc", new Vector2(850, 650));
//		testNPC.setStat("invincible", true);
		objects.addObject(testNPC);

		//NPC testNpc = new NPC("testNpc", new Vector2(600, 500));
		//testNpc.setDialogNames(new String[] { "dialog1" });
		//objects.addObject(testNpc);
		// objects.addObject(new NPC("testNpc", new Vector2(600, 400)));
		//dialogs.addNpc(testNpc);

	}

//	@Override
//	public void addGuiElements() {
//
//	}
	
	@Override
	public boolean keyDown(int key) {
//		System.out.println(key);
		if(key == Keys.NUMPAD_0){
			testNPC.setDest(new Vector2(800, 400));
		}
		return super.keyDown(key);
	}

	

}
