package pl.killermenpl.game.item;

import com.badlogic.gdx.graphics.g2d.Sprite;

import pl.killermenpl.game.assets.AssetManager;
import pl.killermenpl.game.item.ItemArmor.ArmorMaterial;
import pl.killermenpl.game.item.ItemArmor.ArmorType;

/**
 * 
 * @author killermenpl
 *
 *         Override this class to make more item types (weapons, armors etc.)
 */
public abstract class Item {

	public static final Item placeholder = new Item() {
		{
			setBasePrice(0);
			setId(15);
			setWeight(0);
			name = "";
		}
	};

	// Items. Hardcoded, unchangeable.
	public static Item testBodyArmor = new ItemArmor("body", 100, ArmorMaterial.IRON, ArmorType.BODY);
	public static Item testHeadArmor = new ItemArmor("head", 100, ArmorMaterial.IRON, ArmorType.HEAD);
	public static Item testLegsArmor = new ItemArmor("legs", 100, ArmorMaterial.IRON, ArmorType.LEGS);
	public static Item testHandArmor = new ItemArmor("hand", 100, ArmorMaterial.IRON, ArmorType.HANDS);
	public static Item testFeetArmor = new ItemArmor("feet", 100, ArmorMaterial.IRON, ArmorType.FEET);

	public static Item testDaggerWeapon = new ItemWeapon("dagger", 10, 2, 25);
	public static Item fistWeapon = new ItemWeapon("fist", 2, 1000, 10);

	protected int id;
	protected String name;
	protected float weight;

	public float basePrice;

	public Sprite sprite;

	public Item setSprite(Sprite s){
		this.sprite = s;
		return this;
	}

	public Item setSprite(String s){
		setSprite(AssetManager.get(s).asSprite());
		return this;
	}

	public void update(float dt){

	}

	/**
	 * @return the id
	 */
	public final int getId(){
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(int id){
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName(){
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name){
		this.name = name;
	}

	/**
	 * @return the weight
	 */
	public final float getWeight(){
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public final void setWeight(float weight){
		this.weight = weight;
	}

	/**
	 * @return the basePrice
	 */
	public final float getBasePrice(){
		return basePrice;
	}

	/**
	 * @param basePrice
	 *            the basePrice to set
	 */
	public final void setBasePrice(float basePrice){
		this.basePrice = basePrice;
	}
}
