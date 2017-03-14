package itemsMain;

import java.awt.Graphics;

import items.LeatherBoots;
import items.Spellbook;

public class Equipment {
	private Item helmet;
	private Item armor;
	private Item legs;
	private Item boots;
	private Item shield;
	private Item weapon;
	private BackPack bp; //backpack

	public void equip(Item item) {
		switch (item.getType()) {
		case "helmet":
			helmet = item;
			helmet.setX(80);
			helmet.setY(32);
			break;
		case "armor":
			armor = item;
			armor.setX(76);
			armor.setY(68);
			break;
		case "legs":
			legs = item;
			legs.setX(78);
			legs.setY(106);
			break;
		case "boots":
			boots = item;
			boots.setX(78);
			boots.setY(144);
			break;
		case "shield":
			shield = item;
			shield.setX(127);
			shield.setY(83);
			break;
		case "weapon":
			weapon = item;
			weapon.setX(29);
			weapon.setY(84);
			break;
		case "backpack":
			bp = (BackPack)item;			
			bp.setX(122);
			bp.setY(38);			
			break;
		default:
			System.out.println("Equipment error. Could not equip.");
		}
	}
	
	public void takeOff(String item) {
		switch (item) {
		case "helmet":	
			helmet.setX(0);
			helmet.setY(0);
			bp.add(helmet);			
			helmet = null;			
			break;
		case "armor":
			armor.setX(0);
			armor.setY(0);
			bp.add(armor);
			armor = null;			
			break;
		case "legs":
			legs.setX(0);
			legs.setY(0);
			bp.add(legs);
			legs = null;			
			break;
		case "boots":
			boots.setX(0);
			boots.setY(0);
			bp.add(boots);
			boots = null;			
			break;
		case "shield":
			shield.setX(0);
			shield.setY(0);
			bp.add(shield);
			shield = null;			
			break;
		case "weapon":
			weapon.setX(0);
			weapon.setY(0);
			bp.add(weapon);
			weapon = null;			
			break;
		case "backpack":
			bp.setX(0);
			bp.setY(0);
			bp.add(bp);
			bp = null;				
			break;
		default:
			System.out.println("TakeOff error. Could not take off.");
		}
		
	}

	public void draw(int inventoryX, int inventoryY, Graphics g) {
		
		if (helmet != null)
			helmet.draw(inventoryX, inventoryY, g);
		if (armor != null)
			armor.draw(inventoryX, inventoryY, g);
		if (legs != null)
			legs.draw(inventoryX, inventoryY, g);
		if (boots != null)
			boots.draw(inventoryX, inventoryY, g);
		if (shield != null)
			shield.draw(inventoryX, inventoryY, g);
		if (weapon != null)
			weapon.draw(inventoryX, inventoryY, g);
		if (bp != null)
			bp.draw(inventoryX, inventoryY, g);
		 
	}
	
	public BackPack getBp() {
		return bp;
	}

}
