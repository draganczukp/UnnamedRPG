package pl.killermenpl.game.inventory;

import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import pl.killermenpl.game.item.Item;
import pl.killermenpl.game.item.ItemArmor;
import pl.killermenpl.game.item.ItemWeapon;
import pl.killermenpl.game.objects.GameObjectManager;

public class InventoryDetails extends Table {

	private Item item;

	// generic
	private Label itemName, itemPrice, itemWeight;
	// armor
	private Label armorPoints;
	// weapon
	private Label damage, delay;

	private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

	{
		setSkin(skin);
		Drawable back = skin.getDrawable("default-scroll");

		setBackground(back);

	}

	public void setItem(Item item){
		this.item = item;
		setItemName(item.getName());
		setItemPrice(Float.toString(item.getBasePrice()));
		setItemWeight(Float.toString(item.getWeight()));

		clear();
		add(itemName).center().top().expand().colspan(2);
		row();

		if(item instanceof ItemArmor){
			ItemArmor itemArmor = (ItemArmor) item;
			setArmorPoints(Float.toString(itemArmor.armorPoints));

			add(armorPoints).left().colspan(2);
		}else if(item instanceof ItemWeapon){
			ItemWeapon itemWeapon = (ItemWeapon) item;
			setDamage(Float.toString(itemWeapon.damage));
			setDelay(Float.toString(itemWeapon.attackDelay));

			add(damage).left();
			add(delay).right();
		}else{
			add();
		}
		row();
		add(itemPrice).left();
		add(itemWeight).right();
	}


	@Override
	public void draw(Batch batch, float parentAlpha){
		// System.out.println(item.getName());
		// skin.getDrawable("default-round-large").draw(batch, getX(), getY(),
		// 300, -200);
		setItem(GameObjectManager.getPlayerObject().inventory.getSelected());
		if(this.item == Item.placeholder) return;
		super.draw(batch, parentAlpha * 0.8f);
	}

	@Override
	public void setVisible(boolean visible){
		// System.out.println("v");
		super.setVisible(visible);
	}

	/**
	 * @return the itemName
	 */
	public final Label getItemName(){
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public final void setItemName(String itemName){
		itemName = itemName.toUpperCase(Locale.ROOT);
		if(this.itemName == null) this.itemName = new Label(itemName, skin);
		else this.itemName.setText(itemName);
		this.itemName.setAlignment(0);
	}

	/**
	 * @return the itemPrice
	 */
	public final Label getItemPrice(){
		return itemPrice;
	}

	/**
	 * @param itemPrice
	 *            the itemPrice to set
	 */
	public final void setItemPrice(String itemPrice){
		if(this.itemPrice == null) this.itemPrice = new Label(itemPrice + "g", skin);
		else this.itemPrice.setText(itemPrice + "g");
	}

	/**
	 * @return the itemWeight
	 */
	public final Label getItemWeight(){
		return itemWeight;
	}

	/**
	 * @param itemWeight
	 *            the itemWeight to set
	 */
	public final void setItemWeight(String itemWeight){
		if(this.itemWeight == null) this.itemWeight = new Label(itemWeight + "kg", skin);
		else this.itemWeight.setText(itemWeight + "kg");
	}

	/**
	 * @return the armorPoints
	 */
	public final Label getArmorPoints(){
		return armorPoints;
	}

	/**
	 * @param armorPoints
	 *            the armorPoints to set
	 */
	public final void setArmorPoints(String armorPoints){
		if(this.armorPoints == null) this.armorPoints = new Label(armorPoints + " armor", skin);
		else this.armorPoints.setText(armorPoints + " armor");
	}

	public final void setDamage(String damage){
		if(this.damage == null) this.damage = new Label(damage + " dmg", skin);
		else this.damage.setText(damage + " dmg");
	}

	public final void setDelay(String delay){
		if(this.delay == null) this.delay = new Label(delay + " s", skin);
		else this.delay.setText(delay + " s");
	}

	/**
	 * @return the item
	 */
	public final Item getItem(){
		return item;
	}
}
