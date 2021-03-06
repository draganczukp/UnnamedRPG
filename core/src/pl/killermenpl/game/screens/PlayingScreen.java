package pl.killermenpl.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.killermenpl.game.config.Config;
import pl.killermenpl.game.inventory.InventoryDetails;
import pl.killermenpl.game.log.Log;
import pl.killermenpl.game.log.LogLevel;
import pl.killermenpl.game.objects.GameObjectManager;
import pl.killermenpl.game.world.World;
import pl.killermenpl.game.world.Worlds;

public class PlayingScreen implements Screen {
	public static World world;

	protected static InputMultiplexer inputs;

	protected BitmapFont font = new BitmapFont();
	protected static Table table = new Table();
	protected Stage stage;
	protected Skin skin;

	private ProgressBar HPBar;

	private ProgressBar MPBar;

	public static InventoryDetails details = new InventoryDetails();

	public static void setWorld(World w) {
		Log.log(LogLevel.DEBUG, "Closing world: " + world.mapName);
		if (world != null)
			world.close();
		Log.log(LogLevel.DEBUG, "Loading world: " + w.mapName);
		Log.log(LogLevel.DEBUG, "Loaded world: " + w.mapName);
		world = w;
		Screens.PLAY_SCREEN.show();
	}

	@Override
	public void show() {
		if (world == null)
			world = Worlds.get("maptest");

		Log.log(LogLevel.DEBUG, "Initing world: " + world.mapName);
		world.init();

		setupUI();

		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	private void setupUI() {
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		Skin bars = new Skin(new TextureAtlas(Gdx.files.internal("bars.pack")));
		Table playerStats = new Table();
		ProgressBarStyle HPBarsStyle = new ProgressBarStyle();
		ProgressBarStyle MPBarsStyle = new ProgressBarStyle();
		Label HPLabel = new Label("", skin);
		Label MPLabel = new Label("", skin);
		Table left = new Table();
		ScrollPane inventory = new ScrollPane(GameObjectManager.getPlayerObject().inventory);
		Stack HPStack = new Stack();
		Stack MPStack = new Stack();

		
		table.clear();
		table.setSkin(skin);
		HPBarsStyle.background = bars.getDrawable("bar-Background");
		HPBarsStyle.knobBefore = bars.getDrawable("bar-Red");
		HPBarsStyle.knob = bars.getDrawable("bar-Empty");
//		HPBar.setStyle(HPBarsStyle);
		
		HPBar = new ProgressBar(0, 100, .2f, false, HPBarsStyle);
		HPBar.addAction(Actions.forever(new Action() {
			@Override
			public boolean act(float delta) {
				HPBar.setValue((float) GameObjectManager.getPlayerObject().getStat("hp"));
				return true;
			}
		}));

		MPBarsStyle.background = bars.getDrawable("bar-Background");
		// HPBar.setDisabled(true);
		MPBarsStyle.knobBefore = bars.getDrawable("bar-Blue");
		MPBarsStyle.knob = bars.getDrawable("bar-Empty");
//		MPBar.setStyle(MPBarsStyle);
		
		MPBar = new ProgressBar(0, 100, .2f, false, MPBarsStyle);
		MPBar.addAction(Actions.forever(new Action() {
			@Override
			public boolean act(float delta) {
				MPBar.setValue((float) GameObjectManager.getPlayerObject().getStat("mp"));
				return true;
			}
		}));

		HPLabel.addAction(Actions.forever(new Action() {
			@Override
			public boolean act(float delta) {
				float curr = (Float) GameObjectManager.getPlayerObject().getStat("hp");
				float max = (float) GameObjectManager.getPlayerObject().getStat("maxhp");
				HPLabel.setText(Float.toString(Math.round(curr)) + "/" + Float.toString(max));
				// HPLabel.invalidate();
				// System.out.println(HPLabel.getText());
				return true;
			}
		}));


		MPLabel.addAction(Actions.forever(new Action() {
			@Override
			public boolean act(float delta) {
				float curr = (float) GameObjectManager.getPlayerObject().getStat("mp");
				float max = (float) GameObjectManager.getPlayerObject().getStat("maxmp");
				MPLabel.setText(Float.toString(Math.round(curr)) + "/" + Float.toString(max));
				return true;
			}
		}));

		HPStack.add(HPBar);
		HPStack.add(new Container<Label>(HPLabel).right());

		MPStack.add(MPBar);
		MPStack.add(new Container<Label>(MPLabel).right());

		// HPStack.add(backgroundBar);
		// HPStack.add(HPBar);

		playerStats.add(new Label("HP", skin));
		playerStats.add(HPStack);
		playerStats.row();
		playerStats.add(new Label("MP", skin));
		playerStats.add(MPStack);

		Button button = new Button(skin);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Screens.setScreen(Screens.PLAY_SCREEN);
			}
		});
		playerStats.row();
		playerStats.add(button);
		playerStats.left().top();

		details.addAction(Actions.forever(new Action() {
			@Override
			public boolean act(float delta) {
				details.setItem(GameObjectManager.getPlayerObject().inventory.getSelected());
				return false;
			}
		}));
		// TODO: griadient background
		inventory.addAction(Actions.forever(new Action() {
			@Override
			public boolean act(float delta) {
				if (!GameObjectManager.getPlayerObject().inventory.isVisible()) {
					inventory.cancel();
				}
				return false;
			}
		}));
		// inventory.pack();
		inventory.setWidth(300);
		left.setName("left");
		left.add(playerStats).expand().center().left().top().pad(0).maxHeight(Value.percentHeight(0.5f, table));
		left.row();
		left.add(details).expand().right().height(Value.percentHeight(0.2f, table))
				.width(Value.percentWidth(0.4f, table));

		// System.out.println(details.getParent().getName());
		table.add(left).expand().left().fill();
		table.add(inventory).center().expandY().padRight(Value.percentWidth(0.05f, table));
		table.setFillParent(true);
		if (Config.debug)
			table.setDebug(true, true);
		stage.addActor(table.top().left());
		// stage.addActor(GameObjectManager.getPlayerObject().inventory);

		inputs = new InputMultiplexer();
		inputs.addProcessor(0, stage);
		Gdx.input.setInputProcessor(inputs);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		inputs.addProcessor(1, world);

		world.render(delta);

		if (Gdx.input.isKeyPressed(Keys.LEFT))
			GameObjectManager.getPlayerObject().modStat("hp", -.2f);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			GameObjectManager.getPlayerObject().modStat("hp", .2f);

		stage.getViewport().apply();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void resize(int width, int height) {
		world.resize(width, height);
		if (stage != null)
			stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose() {

	}

	public static Table getTable() {
		return table;
	}

}
