package pl.killermenpl.game.objects;

import com.badlogic.gdx.math.Vector2;

public class StaticObject extends GameObject {

	public StaticObject(String name, Vector2 pos) {
		super(name, pos);
	}

	@Override
	public void init() {
		pos = new Vector2();
		super.init();
	}

}
