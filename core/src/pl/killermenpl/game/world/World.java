package pl.killermenpl.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.killermenpl.game.assets.Asset.AssetType;
import pl.killermenpl.game.assets.AssetCategory;
import pl.killermenpl.game.assets.AssetDescriptor;
import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.item.Item;
import pl.killermenpl.game.map.MapRenderer;
import pl.killermenpl.game.objects.GameObjectManager;
import pl.killermenpl.game.objects.MapCollisionObject;
import pl.killermenpl.game.objects.PlayerObject;
import pl.killermenpl.game.objects.TransitionObject;
import pl.killermenpl.game.renderers.DebugShapeRenderer;
import pl.killermenpl.game.save.SaveManager;
import pl.killermenpl.game.screens.Screens;

public abstract class World extends InputAdapter {

	
	public GameObjectManager objects = new GameObjectManager();
	

	protected SpriteBatch batch;
	public static OrthographicCamera cam;
	public static Viewport camViewport;

	public MapRenderer mapRenderer;

	// private Array<MapCollisionObject> collisions;

	public static Vector2 mousePosition = new Vector2();

	protected Matrix4 normalProjection = new Matrix4();

	// protected GuiManager gui = new GuiManager();
	// protected DialogWindowManager dialogs = new DialogWindowManager();

	public String mapName;

	private Vector2 targetPos;

	public World init() {
		if (objects == null)
			objects = new GameObjectManager();

		// Gdx.input.setInputProcessor(this);

		objects.drop();

		AssetManager.setGroups(AssetCategory.CITY1, AssetCategory.GUI, AssetCategory.MAIN, AssetCategory.TEMP);
		// AssetManager.loadAll();

		batch = new SpriteBatch();

		cam = new OrthographicCamera();
		camViewport = new FitViewport(720, 405, cam);
		// cam.zoom = 2.5f;
		AssetManager.load(
				new AssetDescriptor(AssetType.MAP, this.mapName, AssetCategory.MAIN, "maps/" + this.mapName + ".tmx"));

		mapRenderer = new MapRenderer(AssetManager.get(this.mapName).asMap());

		addObjects();
		addCollsionObjects();
		addTransitionObjects();
		objects.initAll();

		// normalProjection.setToOrtho2D(0, 0, Gdx.graphics.getWidth(),
		// Gdx.graphics.getHeight());

		// addGuiElements();
		// gui.initAll();
		// GuiRenderer.setGui(gui);

		// this.dialogs.initAll();
		// System.out.println(mapName);
		return this;
	}

	private void addTransitionObjects() {
		TiledMap map = AssetManager.get(mapName).asMap();
		map.getLayers().get("transitions").getObjects().forEach((MapObject object) -> {
			MapProperties props = object.getProperties();

			objects.addObject(new TransitionObject(props));
		});
		;
	}

	public void addObjects() {
		TiledMap map = AssetManager.get(mapName).asMap();
		MapProperties props = map.getLayers().get("Player").getObjects().get("Player").getProperties();
		// PlayerObject player = (PlayerObject) new PlayerObject(
		// new Vector2(props.get("x", Float.class),
		// props.get("y", Float.class))).setManager(objects);
		if (targetPos == null) {
			float x = props.get("x", float.class);
			float y = props.get("y", float.class);
			targetPos = new Vector2(x, y);
		}
		objects.addObject(new PlayerObject(targetPos).setManager(objects));
	}

	public void render(float delta) {
		// cam.normalizeUp();
		// cam.lookAt(100, 100, 0);
		cam.position.set((GameObjectManager.getPlayerObject()).getPosition3());
		// cam.update();
		camViewport.apply();
		mousePosition.set(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
		objects.setMousePosition(mousePosition);
		DebugShapeRenderer.setCamera(cam);

		mapRenderer.setView(cam);
		mapRenderer.render(objects, delta);

		// mapRenderer.render1
		// objects.render
		// mapRenderer.render1
		try {
			batch.getColor();
		} catch (NullPointerException e) {
			batch = new SpriteBatch();
		}
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		// batch.draw(bg, 0, 0);
		// objects.render(batch, delta);

		// batch.setProjectionMatrix(normalProjection);

		batch.end();

		DebugShapeRenderer.drawAll();

		// GuiRenderer.setGui(dialogs);
		// GuiRenderer.render(delta);
		//
		// GuiRenderer.setGui(gui);
		// GuiRenderer.render(delta);

		// System.out.println(objects.getPlayerObject().getPosition());
	}

	////// public abstract void addGuiElements();

	public void addCollsionObjects() {

		TiledMap map = AssetManager.get(mapName).asMap();

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Collision");
		for (int x = 0; x < layer.getWidth(); x++) {
			for (int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					Vector2 p = new Vector2(x, y);
					// System.out.println(p);
					objects.addObject(new MapCollisionObject(p));
				}
			}
		}
		
		MapLayer player2 = map.getLayers().get("Collision2");
		
		if(player2==null)
			return;
		
		player2.getObjects().forEach(mapObject->{
			MapProperties mapProperties = mapObject.getProperties();
			objects.addObject(new MapCollisionObject(new Vector2(mapProperties.get("x", float.class), mapProperties.get("y", float.class))).setBox(mapProperties.get("width", float.class), mapProperties.get("height", float.class)));
		});

	}

	public void resize(int width, int height) {
		// float ratio = height/ width;
		// System.out.println(height);
		// cam.viewportHeight = 250*
		if (camViewport == null)
			return;
		camViewport.update(width, height);
	}

	public void dispose() {
		// objects.dispose();
		batch.dispose();
	}

	/**
	 * 
	 */
	public void close() {
		
		
		
		
		dispose();
		this.batch = null;
		// this.dialogs = null;
		// this.font = null;
		// this.gui = null;
		this.mapRenderer = null;
		this.normalProjection = null;
		this.objects = null;
		System.gc();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		// Left click
		if (button == Buttons.LEFT) {
			GameObjectManager.getPlayerObject().attack();
		}
		return true;
	}

	@Override
	public boolean keyDown(int key) {

		if (key == Keys.ESCAPE)
			Screens.setScreen(Screens.MAIN_MENU_SCREEN);

		if (key == Keys.P)
			GameObjectManager.getPlayerObject().inventory.addItem(Item.testBodyArmor);
		if (key == Keys.L)
			GameObjectManager.getPlayerObject().inventory.addItem(Item.testDaggerWeapon);
		if (key == Keys.F5)
			Screens.setScreen(Screens.PLAY_SCREEN);
		if (key == Keys.I)
			GameObjectManager.getPlayerObject().inventory
					.setVisible(!GameObjectManager.getPlayerObject().inventory.isVisible());

		if (key == Keys.F1){
			SaveManager.get().save(this);
			SaveManager.get().save(GameObjectManager.getPlayerObject().inventory);
			System.out.println(new Json(OutputType.json).prettyPrint(SaveManager.get().saveAll()));
		}
		return true;
	}

	public World setPos(Vector2 targetPos) {
		this.targetPos = targetPos;
		return this;
	}

}
