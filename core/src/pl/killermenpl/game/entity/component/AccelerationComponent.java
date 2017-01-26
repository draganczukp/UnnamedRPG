package pl.killermenpl.game.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class AccelerationComponent implements Component{
	public Vector2 acc = new Vector2();

	public AccelerationComponent(Vector2 acc) {
		this.acc = acc;
	}

}

