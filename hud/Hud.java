package hud;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameCharactersMain.Player;
import itemsMain.ItemsMap;
import tibia.ImageLoader;

public class Hud {
	private Player playerReference;
	private BufferedImage background;
	// private LinkedList <HudPart> hudParts;
	private Inventory inventory;
	private CharacterProfile characterProfile;
	private Container container;

	public Hud(Player player) {
		this.playerReference = player;
		background = ImageLoader.loadImage("/img/background.png");
		// hudParts = new LinkedList<>();
		inventory = new Inventory(1120, 32, playerReference.getEq());
		characterProfile = new CharacterProfile(1120, inventory.getyEnd() + 1, playerReference);
		container = new Container(1120, characterProfile.getyEnd() + 1, playerReference.getEq().getBp(),playerReference.getEq());
	}

	public void draw(Graphics g) {
		g.drawImage(background, 1120, 32, null);
		inventory.draw(g);
		characterProfile.draw(g);
		container.draw(g);
	}

	public void update() {
		characterProfile.setY(inventory.getyEnd() + 1);
		container.setY(characterProfile.getyEnd() + 1);
	}

	public void checkMouseCollision(int mouseX, int mouseY, String button) {
		inventory.checkMouseCollision(mouseX, mouseY,button);
		characterProfile.checkMouseCollision(mouseX, mouseY);
		container.checkMouseCollision(mouseX, mouseY, button);		
	}

	public void checkMouseReleased(int mouseX, int mouseY, String button) {
		container.checkMouseReleased(mouseX, mouseY, button);
	}

	public void moveItem(int savedRow, int savedColumn, int mouseX, int mouseY) {

		if (mouseY >= container.getY() && mouseY <= container.getyEnd()) {
			container.addItem(ItemsMap.getItem(savedRow, savedColumn));
			ItemsMap.removeItem(savedRow, savedColumn);
		}

	}
}
