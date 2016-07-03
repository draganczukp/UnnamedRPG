package pl.killermenpl.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.killermenpl.game.assets.AssetCategory;
import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.assets.Assets;
import pl.killermenpl.game.config.Config;
import pl.killermenpl.game.log.Log;
import pl.killermenpl.game.util.FontUtil;
import pl.killermenpl.game.world.Worlds;

public class LoadingScreen implements Screen {
	private int count = 0;

	private SpriteBatch batch = new SpriteBatch();
	private Texture bg = new Texture(Gdx.files.internal("img/loading.png"));

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bg, 0, 0);
		batch.end();
		switch (count) {
		case 0:
			try {
				Log.init();
				Config.init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 1:
			Assets.addAssets();
			break;
		case 2:
			AssetManager.setGroups(AssetCategory.TEMP);
			AssetManager.loadAll();
			break;
		case 3:
			FontUtil.init();
			break;
		case 4:
			Worlds.loadAll();
			break;
		case 20:
			((Game) Gdx.app.getApplicationListener()).setScreen(Screens.MAIN_MENU_SCREEN);
		}
		count++;
	}

	@Override
	public void resize(int width, int height) {

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
	public void dispose() {

	}

}
