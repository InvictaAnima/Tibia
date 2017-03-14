package itemsMain;

import java.awt.Graphics;
import java.util.LinkedList;

import items.Spellbook;

public class BackPack extends Item {

	protected LinkedList<Item> items;
	protected int slots;
	protected Item selectedItem;

	public BackPack() {
		items = new LinkedList<>();
	}

	public void add(Item item) {
		if (items.size() < 20) {
			items.addFirst(item);
		}
	}

	public void drawItems(int x, int y, Graphics g) { // x,y are beginning of the
																										// first slot

		for (int i = 0; i < items.size(); i++) {
			if(items.get(i)!=null){
				items.get(i).draw(x + (i % 4 * 36), y + (i / 4 * 36), g);
			}
		}
	}

	public void removeSelectedItem() {
		if (selectedItem != null) {			
			items.remove(selectedItem);	
			removeNulls();
			selectedItem = null;
		}
		
	}

	public void setSelectedItem(int row, int column) {
		int index = row + 4 * column;

		if (index >= 0 && index < items.size()) {
			selectedItem = items.get(row + 4 * column);
		}

	}

	public void putItemOnTheGround(int row, int column) {
		if (selectedItem != null) {
			ItemsMap.addItem(row, column, selectedItem);
			removeSelectedItem();
		}
	}	
	
	public Item getSelectedItem() {
		return selectedItem;
	}
	
	public void removeNulls(){
		for(Item tmp : items){
			if(tmp == null){
				items.remove(tmp);
			}
		}
	}
	
	public void printItems(){
		for(Item tmp : items){
			if(tmp == null) System.out.println("null");
			else System.out.println("gad");
		}
	}

}
