package pl.killermenpl.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.item.Item;
import pl.killermenpl.game.objects.GameObjectManager;
import pl.killermenpl.game.objects.MapCollisionObject;
import pl.killermenpl.game.renderers.DebugShapeRenderer;
import pl.killermenpl.game.screens.Screens;

public abstract class World extends InputAdapter {
	public GameObjectManager objects = new GameObjectManager();

	protected SpriteBatch batch;
	public static OrthographicCamera cam;
	public TiledMapRenderer mapRenderer;

	// private Array<MapCollisionObject> collisions;

	public static Vector2 mousePosition = new Vector2();

	protected Matrix4 normalProjection = new Matrix4();

	// protected GuiManager gui = new GuiManager();
	// protected DialogWindowManager dialogs = new DialogWindowManager();

	public String mapName;

	public World init(){
		// System.out.println(mapName);

		// Gdx.input.setInputProcessor(this);

		objects.drop();

		batch = new SpriteBatch();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, 250, 250 * (Gdx.graphics.getHeight() / Gdx.graphics.getHeight()));
		cam.zoom = 2.5f;

		mapRenderer = new OrthogonalTiledMapRenderer(AssetManager.get(this.mapName).asMap());

		addObjects();
		addCollsionObjects();

		objects.initAll();

		// normalProjection.setToOrtho2D(0, 0, Gdx.graphics.getWidth(),
		// Gdx.graphics.getHeight());

		// addGuiElements();
		// gui.initAll();
		// GuiRenderer.setGui(gui);

		// this.dialogs.initAll();

		return this;
	}

	abstract void addObjects();

	public void render(float delta){
		cam.normalizeUp();
		// cam.lookAt(100, 100, 0);
		cam.position.set((GameObjectManager.getPlayerObject()).getPosition3());
		cam.update();
		mousePosition.set(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
		objects.setMousePosition(mousePosition);
		DebugShapeRenderer.setCamera(cam);

		mapRenderer.setView(cam);
		mapRenderer.render();

		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		// batch.draw(bg, 0, 0);
		objects.render(batch, delta);

		batch.setProjectionMatrix(normalProjection);

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

	public void addCollsionObjects(){

		TiledMap map = AssetManager.get(mapName).asMap();

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Collision");
		for(int x = 0; x < layer.getWidth(); x++){
			for(int y = 0; y < layer.getHeight(); y++){
				Cell cell = layer.getCell(x, y);
				if(cell != null){
					Vector2 p = new Vector2(x, y);
					// System.out.println(p);
					objects.addObject(new MapCollisionObject(p));
				}
			}
		}

	}

	public void resize(int width, int height){
		// float ratio = height/ width;
		// System.out.println(height);
		// cam.viewportHeight = 250*
		if(cam == null) return;
		cam.setToOrtho(false, 250, 250 * ((float) height / (float) width));
		cam.update();
	}

	public void dispose(){
		objects.dispose();
		batch.dispose();
	}



	/**
	 * 
	 */
	public void close(){
		dispose();
		this.batch = null;
		// this.dialogs = null;
		// this.font = null;
		// this.gui = null;
		this.mapRenderer = null;
		World.mousePosition = null;
		this.normalProjection = null;
		this.objects = null;
		System.gc();
	}

	@Override
	public boolean keyDown(int key){

		if(key == Keys.ESCAPE)
			Screens.setScreen(Screens.MAIN_MENU_SCREEN);

		if(key == Keys.P) GameObjectManager.getPlayerObject().inventory.addItem(Item.testBodyArmor);
		if(key == Keys.L) GameObjectManager.getPlayerObject().inventory.addItem(Item.testDaggerWeapon);
		if(key == Keys.F5) Screens.setScreen(Screens.PLAY_SCREEN);
		if(key == Keys.I) GameObjectManager.getPlayerObject().inventory
				.setVisible(!GameObjectManager.getPlayerObject().inventory.isVisible());
		return super.keyDown(key);
	}

}
