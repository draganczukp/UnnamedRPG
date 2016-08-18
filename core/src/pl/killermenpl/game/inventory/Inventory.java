package pl.killermenpl.game.inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;

import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.item.Item;
import pl.killermenpl.game.item.ItemDisplay;
import pl.killermenpl.game.screens.PlayingScreen;

public class Inventory extends Table {

	private ButtonGroup<ItemDisplay> items;

	
	public Sprite bg;
	
	public Inventory(){
		// padRight(10);
		// setWidth(100);
		items = new ButtonGroup<>();
		items.setMaxCheckCount(1);
		setVisible(false);
		// addItem(new ItemArmor("test", 1, ArmorMaterial.IRON,
		// ArmorType.BODY));
		
		bg = AssetManager.get("INV_BG").as(TextureAtlas.class).createSprite("inv_bg");
		
	}

	@Override
	public void act(float delta){
		if(items.getButtons().random() == null){
			addItem(Item.placeholder);
		}
		super.act(delta);
	}
	
	@Override
	protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
		bg.setBounds(this.getX(), 0, this.getWidth(), 10000);
		bg.draw(batch, 0.5f);
	}

	public Inventory addItem(Item item){

		ItemDisplay itemDisplay = new ItemDisplay(item);
		if(item == Item.placeholder){
			itemDisplay.setHeight(0);
			items.add(itemDisplay);
			add(itemDisplay).height(0);
		}else{
			items.add(itemDisplay);
			add(itemDisplay).expandX().width(Value.percentWidth(1f, this));
		}
		row();
		return this;
	}

	@Override
	public void layout(){
		super.layout();
		center();
	}

	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
		PlayingScreen.details.setVisible(visible);
	}

	public Item getSelected(){
		return items.getChecked() != null ? items.getChecked().getItem() : Item.placeholder;
	}

	public ButtonGroup<ItemDisplay> getItems() {
		return items;
	}

}
