package pl.killermenpl.game.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TextureComponent implements Component {

	public Sprite sprite;
	
	public TextureComponent(Sprite sprite) {
		this.sprite = sprite;
	}

}
