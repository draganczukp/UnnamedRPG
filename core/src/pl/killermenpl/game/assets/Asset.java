package pl.killermenpl.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Asset {

	public static enum AssetType {
		SPRITE, MUSIC, SOUND, MAP;
	}

	private Object object;

	public Asset(AssetType type, String path) {
		switch (type) {
		case MUSIC:
			object = Gdx.audio.newMusic(Gdx.files.internal(path));
			break;
		case SOUND:
			object = Gdx.audio.newSound(Gdx.files.internal(path));
			break;
		case SPRITE:
			object = new Sprite(new Texture(Gdx.files.internal(path)));
			break;
		case MAP:
			object = new TmxMapLoader().load(path);
			break;
		default:
			break;
		}
	}

	public Sprite asSprite() {
		return (Sprite) object;
	}

	public Sound asSound() {
		return (Sound) object;
	}

	public Music asMusic() {
		return (Music) object;
	}

	public TiledMap asMap() {
		return (TiledMap) object;
	}
}
