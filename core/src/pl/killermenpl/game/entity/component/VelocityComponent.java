package pl.killermenpl.game.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent implements Component {
	public Vector2 vel;
	
	
	public VelocityComponent(Vector2 vec) {
		this.vel = vec;
	}

}