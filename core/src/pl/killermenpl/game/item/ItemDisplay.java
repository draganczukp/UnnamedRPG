package pl.killermenpl.game.item;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.killermenpl.game.objects.GameObjectManager;
import pl.killermenpl.game.screens.PlayingScreen;
import pl.killermenpl.game.util.FontUtil;

public class ItemDisplay extends TextButton {

	private Item item;
	private int ammount;
	boolean hold = false;

	public static Skin transparentSkin = new Skin(new TextureAtlas("select.pack"));
	private static TextButtonStyle transparent = new TextButtonStyle();

	private ClickListener itemClickListener = new ClickListener(-1) {
		public void clicked(InputEvent event, float x, float y){
			if(getPressedButton() == Buttons.RIGHT || hold){
				onRightClick(x, y);
			}

			if(getTapCount() >= 2){
				GameObjectManager.getPlayerObject().inventory.equip(ItemDisplay.this);
			}
		}
	};

	private EventListener gestureListener = new ActorGestureListener() {
		@Override
		public boolean longPress(Actor actor, float x, float y){
			hold = true;
			return true;
		}
	};

	static{
		transparent.checked = transparentSkin.getDrawable("checked");
		transparent.up = transparentSkin.getDrawable("up");
		transparent.font = FontUtil.generateFont(Color.WHITE, 20);
	}

	public ItemDisplay(Item item){
		this(item, 1);
	}

	protected void onRightClick(float x, float y){
		this.setChecked(true);
		System.out.println("Right click");
	}

	public ItemDisplay(Item item, int ammount){
		super(item.name, transparent);
		this.item = item;
		this.ammount = ammount;
		this.pad(new Value.Fixed(8), Value.zero, new Value.Fixed(8),
				Value.zero);
		this.getBackground().setMinWidth(250);
		this.addListener(gestureListener);
		this.addListener(itemClickListener);
	}

	@Override
	public void layout(){
		super.layout();
	}

	@Override
	public void act(float delta){
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
	}

	/**
	 * @return the item
	 */
	public final Item getItem(){
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public final void setItem(Item item){
		this.item = item;
	}

	/**
	 * @return the ammount
	 */
	public final int getAmmount(){
		return ammount;
	}

	/**
	 * @param ammount
	 *            the ammount to set
	 */
	public final void setAmmount(int ammount){
		this.ammount = ammount;
	}

}
