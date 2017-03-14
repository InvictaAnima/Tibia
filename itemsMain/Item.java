package itemsMain;

import java.awt.Graphics;

public abstract class Item {
	protected int id;
	protected int x, y;
	protected String name;
	protected int weight;
	protected String type; // helmet, shield, armor, legs
	
	public Item(){
	
	}

	public void draw(Graphics g) {
		ItemsImages.draw(x, y, id, g);
	}

	public void draw(int inventoryX, int inventoryY, Graphics g) {
		ItemsImages.draw(inventoryX + x, inventoryY + y, id, g);
	}

	public String getType() {
		return type;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
