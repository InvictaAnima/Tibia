package hud;

import java.awt.Graphics;

import itemsMain.BackPack;
import itemsMain.Equipment;
import itemsMain.Item;
import tibia.ImageLoader;

public class Container extends HudPart {

	private BackPack bp;
	private Equipment eq;

	public Container(int x, int y, BackPack bp, Equipment eq) {
		super(x, y);
		yEnd = this.y + 213;
		isMaximized = true;
		this.bp = bp;
		this.eq = eq;
		loadImages();
	}

	private void loadImages() {
		tabOpen = ImageLoader.loadImage("/img/bagOpen.png");
		tabClosed = ImageLoader.loadImage("/img/inventoryClosed.png");
	}

	public void draw(Graphics g) {
		if (isMaximized) {
			g.drawImage(tabOpen, x, y, null);
			bp.drawItems(x + 14, y + 32, g);
		} else {
			g.drawImage(tabClosed, x, y, null);
		}
	}

	@Override
	public void checkMouseCollision(int mouseX, int mouseY) {
	}
	
	public void checkMouseCollision(int mouseX, int mouseY, String button) {

		// minimalize/maximalize
		if (mouseX >= (this.x + 151) && mouseX <= this.x + 151 + 16 && mouseY >= (this.y + 4) && mouseY <= (this.y) + 16) {
			this.resize();
		}

		bp.setSelectedItem((mouseX - (x + 14)) / 32, (mouseY - (y + 32)) / 32);		
		
		
		
	}

	public void checkMouseReleased(int mouseX, int mouseY, String button) {
		if(mouseX<=15 && button.equals("left")){
			bp.putItemOnTheGround(mouseX, mouseY);
		}
		
		if(bp.getSelectedItem()!= null && button.equals("right")){					
			eq.equip(bp.getSelectedItem());
			bp.removeSelectedItem();
		}
	}
	
	public void addItem(Item item){
		bp.add(item);
	}

}
