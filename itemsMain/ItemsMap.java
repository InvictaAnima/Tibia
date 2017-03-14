package itemsMain;

import java.awt.Graphics;

import items.Spellbook;
import tibia.MapSuper;

public class ItemsMap extends MapSuper {
	private static Item tab[][];

	public ItemsMap() {
		tab = new Item[rows][columns];
		tab[7][5] = new Spellbook();
	}

	public void draw(Graphics g) {
		for (int i = 0; i < 22; i++)
			for (int j = 0; j < 42; j++) {
				if (tab[initRow + i][initColumn + j] != null) {
					tab[initRow + i][initColumn + j].draw((initColumn + j) * 32 - (initColumn * 32 + tmpX),
							(initRow + i) * 32 - (initRow * 32 + tmpY), g);

				}
			}
	}

	public void checkMouseCollision(int row, int column) {
		

	}

	public void moveItem(int row, int column, int mouseX, int mouseY) {
		
		
		int destRow = mouseX/32;
		int destColumn = mouseY/32;		
		
		if (tab[initRow + row][initColumn + column] != null) {
			Item tmp = tab[initRow + row][initColumn + column];
			tab[initRow + row][initColumn + column] = null;
			tab[initRow + destRow][initColumn + destColumn] = tmp;
		}
	}
	
	public static void addItem(int row, int column, Item item){
		tab[initRow + row][initColumn + column] = item;
	}
	
	public static void removeItem(int row, int column){
		tab[initRow + row][initColumn + column] = null;
	}
	
	public static Item getItem(int row, int column){
		return tab[initRow + row][initColumn + column];
	}
	
}
