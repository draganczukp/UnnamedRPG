package pl.killermenpl.game.inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;

import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.item.Item;
import pl.killermenpl.game.item.ItemArmor;
import pl.killermenpl.game.item.ItemDisplay;
import pl.killermenpl.game.item.ItemWeapon;
import pl.killermenpl.game.screens.PlayingScreen;

public class Inventory extends Table {

	private ButtonGroup<ItemDisplay> group;

	public ItemArmor head, body, legs, feet, arms;
	public ItemWeapon weapon;
	
	public Sprite bg;
	
	public Inventory(){
		// padRight(10);
		// setWidth(100);
		group = new ButtonGroup<>();
		group.setMaxCheckCount(1);
		setVisible(false);
		// addItem(new ItemArmor("test", 1, ArmorMaterial.IRON,
		// ArmorType.BODY));
		weapon = (ItemWeapon) Item.testDaggerWeapon;
		bg = AssetManager.get("INV_BG").as(TextureAtlas.class).createSprite("inv_bg");
		
	}

	@Override
	public void act(float delta){
		if(group.getButtons().random() == null){
			addItem(Item.placeholder);
		}
		if(head != null)
		head.update(delta);
		if(body != null)
		body.update(delta);
		if(legs != null)
		legs.update(delta);
		if(feet != null)
		feet.update(delta);
		if(arms != null)
		arms.update(delta);
		if(weapon != null)
		weapon.update(delta);

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
			group.add(itemDisplay);
			add(itemDisplay).height(0);
		}else{
			group.add(itemDisplay);
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
		return group.getChecked() != null ? group.getChecked().getItem() : Item.placeholder;
	}

	public void equip(ItemDisplay itemDisplay){
		Item item = itemDisplay.getItem();
		System.out.println("Equip " + item.getName());
		if(item instanceof ItemArmor){
			ItemArmor armor = (ItemArmor) item;
			switch(armor.type){
			case BODY:
				this.body = armor;
				break;
			case FEET:
				this.feet = armor;
				break;
			case HANDS:
				this.arms = armor;
				break;
			case HEAD:
				this.head = armor;
				break;
			case LEGS:
				this.legs = armor;
				break;
			default:
				break;

			}
		}else if(item instanceof ItemWeapon){
			weapon = (ItemWeapon) item;
		}

	}

}
