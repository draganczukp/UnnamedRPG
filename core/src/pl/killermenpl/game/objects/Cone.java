package pl.killermenpl.game.objects;

import java.util.Arrays;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import pl.killermenpl.game.objects.LivingObject.Direction;

public class Cone extends Polygon{
	
	protected LivingObject parent;
	public float range = 0;
	public Cone(LivingObject parent){
		this.parent = parent;
	}

	protected void updateCone(){
		setVertices(new float[]{parent.box.getCenter(Vector2.X).x, parent.box.getCenter(Vector2.Y).y, getConeX1(parent.facing),
				getConeY1(parent.facing),
				getConeX2(parent.facing), getConeY2(parent.facing) });
		System.out.println(Arrays.toString(getVertices()));
	}

	private float getConeX1(Direction dir){
		// System.out.println(inventory == null);
		switch(dir){
		case DOWN:
		case LEFT:
		case UP:
			return (float) (parent.box.getCenter(Vector2.X).x - parent.range);
		case RIGHT:
			return (float) (parent.box.getCenter(Vector2.X).x + parent.range);
		default:
			break;
		}
		return 0;

	}

	private float getConeX2(Direction dir){
		switch(dir){
		case DOWN:
		case RIGHT:
		case UP:
			return (float) (parent.box.getCenter(Vector2.X).x + parent.range);
		case LEFT:
			return (float) (parent.box.getCenter(Vector2.X).x - parent.range);
		default:
			break;
		}
		return 0;
	}

	private float getConeY1(Direction dir){
		switch(dir){
		case DOWN:
		case LEFT:
		case RIGHT:
			return (float) (parent.box.getCenter(Vector2.X).y - parent.range);
		case UP:
			return (float) (parent.box.getCenter(Vector2.X).y + parent.range);
		default:
			break;
		}
		return 0;
	}

	private float getConeY2(Direction dir){
		switch(dir){
		case DOWN:
			return (float) (parent.box.getCenter(Vector2.X).y - parent.range);
		case LEFT:
		case RIGHT:
		case UP:
			return (float) (parent.box.getCenter(Vector2.X).y + parent.range);
		default:
			break;
		}
		return 0;
	}

	/**
	 * @return the parent
	 */
	public final LivingObject getParent(){
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public final void setParent(LivingObject parent){
		this.parent = parent;
	}
}