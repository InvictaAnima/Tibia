package itemsMain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import tibia.ImageLoader;

public class ItemsImages {
	private static LinkedList<BufferedImage> images; // Leather Legs
	// 0 - Leather Legs
	// 1 - Magic Robe
	// 2 - Mage Hat
	// 3 - Leather Boots
	// 4 - Spellbook
	// 5 - Snakebite Rod
	// 6 - Brocade Backpack

	public ItemsImages() {
		images = new LinkedList<>();
		loadImages();
	}

	private void loadImages() {
		images.add(ImageLoader.loadImage("/img/leatherLegs.png"));
		images.add(ImageLoader.loadImage("/img/magicRobe.png"));
		images.add(ImageLoader.loadImage("/img/mageHat.png"));
		images.add(ImageLoader.loadImage("/img/leatherBoots.png"));
		images.add(ImageLoader.loadImage("/img/spellbook.png"));
		images.add(ImageLoader.loadImage("/img/snakebiteRod.png"));
		images.add(ImageLoader.loadImage("/img/brocadeBackpack.png"));

	}

	public static void draw(int x, int y, int id, Graphics g) {
		g.drawImage(images.get(id), x, y, null);		
	}
}
