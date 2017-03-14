package hud;

import java.awt.Graphics;

import itemsMain.Equipment;
import tibia.ImageLoader;

public class Inventory extends HudPart {

	private Equipment eq;

	public Inventory(int x, int y, Equipment eq) {
		super(x, y);
		yEnd = this.y + 213;
		this.eq = eq;
		loadImages();
	}

	private void loadImages() {
		tabOpen = ImageLoader.loadImage("/img/inventoryOpen.png");
		tabClosed = ImageLoader.loadImage("/img/inventoryClosed.png");
	}

	public void draw(Graphics g) {
		if (isMaximized) {
			g.drawImage(tabOpen, x, y, null);
			eq.draw(x, y, g);
		} else {
			g.drawImage(tabClosed, x, y, null);
		}
	}
	
	@Override
	public void checkMouseCollision(int mouseX, int mouseY){
	}
	
	public void checkMouseCollision(int mouseX, int mouseY, String button) {
		// minimalize/maximalize
		if (mouseX >= (this.x + 151) && mouseX <= this.x + 151 + 16 && mouseY >= (this.y + 4) && mouseY <= (this.y) + 16) {
			this.resize();
		}

		if (button.equals("right")) {
			if (collision(mouseX, mouseY, x + 80, y + 32, x + 80 + 32, y + 32 + 32)) {
				eq.takeOff("helmet");
			} else if (collision(mouseX, mouseY, x + 76, y + 68, x + 76 + 32, y + 68 + 32)) {
				eq.takeOff("armor");
			} else if (collision(mouseX, mouseY, x + 78, y + 106, x + 78 + 32, y + 106 + 32)) {
				eq.takeOff("legs");
			} else if (collision(mouseX, mouseY, x + 78, y + 144, x + 144 + 32, y + 144 + 32)) {
				eq.takeOff("boots");
			} else if (collision(mouseX, mouseY, x + 127, y + 83, x + 127 + 32, y + 83 + 32)) {
				eq.takeOff("shield");
			} else if (collision(mouseX, mouseY, x + 26, y + 83, x + 26 + 32, y + 83 + 32)) {
				eq.takeOff("weapon");
			}
		}

	}

	private boolean collision(int mouseX, int mouseY, int x, int y, int xEnd, int yEnd) {
		if (mouseX >= x && mouseX <= xEnd && mouseY >= y && mouseY <= yEnd) {
			return true;
		} else {
			return false;
		}

	}

}
