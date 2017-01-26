package pl.killermenpl.game.entity.component;

import com.badlogic.ashley.core.Component;

public class NameComponent implements Component {
	public String name;
	public NameComponent(String n){
		name = n;
	}
}
