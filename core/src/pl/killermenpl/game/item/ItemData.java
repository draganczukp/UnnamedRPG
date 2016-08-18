package pl.killermenpl.game.item;

import pl.killermenpl.game.item.ItemArmor.ArmorMaterial;
import pl.killermenpl.game.item.ItemArmor.ArmorType;
import pl.killermenpl.game.item.ItemWeapon.WeaponType;

public class ItemData {

	public String name;
	public float weight;

	// a: armor, w: weapon, i: generic
	public char type;

	// Armor only
	public ArmorMaterial armorMaterial;
	public ArmorType armorType;
	public float armorPoints;

	// Weapon only
	public WeaponType weaponType;
	public float baseDamage, baseAttackDelay, baseRange, damage, attackDelay, range;

	public Item toItem() {
		Item item = new Item();

		item.setName(name);
		item.setWeight(weight);

		switch (type) {
		case 'a':
			ItemArmor armor = (ItemArmor) item;
			armor.material = armorMaterial;
			armor.type = armorType;
			armor.armorPoints = armorPoints;
			return armor;
		case 'w':
			ItemWeapon weapon = (ItemWeapon) item;
			weapon.attackDelay = attackDelay;
			weapon.baseAttackDelay = baseAttackDelay;
			weapon.baseDamage = baseDamage;
			weapon.baseRange = baseRange;
			weapon.damage = damage;
			weapon.range = range;
			weapon.type = weaponType;
			return weapon;
		}

		return item;
	}

	public static ItemData fromItem(Item item) {
		if(item ==null)
			return null;
		ItemData data = new ItemData();

		data.name = item.name;
		data.weight = item.weight;

		if (item instanceof ItemArmor) {
			ItemArmor armor = (ItemArmor) item;
			data.armorMaterial = armor.material;
			data.armorPoints = armor.armorPoints;
			data.armorType = armor.type;
			data.type = 'a';
		} else if (item instanceof ItemWeapon) {
			ItemWeapon weapon = (ItemWeapon) item;

			data.attackDelay = weapon.attackDelay;
			data.baseAttackDelay = weapon.baseAttackDelay;
			data.baseDamage = weapon.baseDamage;
			data.baseRange = weapon.baseRange;
			data.damage = weapon.damage;
			data.range = weapon.range;
			data.weaponType = weapon.type;
			data.type = 'w';
		}

		return data;
	}
}
