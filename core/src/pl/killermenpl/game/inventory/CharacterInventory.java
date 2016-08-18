package pl.killermenpl.game.inventory;

import pl.killermenpl.game.item.Item;
import pl.killermenpl.game.item.ItemArmor;
import pl.killermenpl.game.item.ItemDisplay;
import pl.killermenpl.game.item.ItemWeapon;

public class CharacterInventory extends Inventory {

	public ItemArmor head, body, legs, feet, arms;
	public ItemWeapon weapon;

	public CharacterInventory() {
		weapon = (ItemWeapon) Item.testDaggerWeapon;

	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (head != null)
			head.update(delta);
		if (body != null)
			body.update(delta);
		if (legs != null)
			legs.update(delta);
		if (feet != null)
			feet.update(delta);
		if (arms != null)
			arms.update(delta);
		if (weapon != null)
			weapon.update(delta);
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
