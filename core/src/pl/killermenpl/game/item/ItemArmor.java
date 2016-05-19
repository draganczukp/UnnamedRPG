package pl.killermenpl.game.item;

public class ItemArmor extends Item {
	public enum ArmorMaterial{
		LEATHER, IRON, FUR, STEEL;
	}

	public enum ArmorType{
		HEAD, BODY, LEGS, FEET, HANDS;
	}

	public float armorPoints;
	public ArmorMaterial material;
	public ArmorType type;

	public ItemArmor(String name, float armorPoints, ArmorMaterial material, ArmorType type){
		super();
		this.name = name;
		this.armorPoints = armorPoints;
		this.material = material;
		this.type = type;
	}

}
