package tibia;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import hud.Hud;
import itemsMain.ItemsMap;

public class MouseInput extends MouseAdapter{
	private Hud hudReference; 
	private ItemsMap itemsMapReference;
	
	private int savedRow,savedColumn;
	
	public MouseInput(Hud hud, ItemsMap itemsMap){
		this.hudReference = hud;
		this.itemsMapReference = itemsMap;
	}
	
	@Override
	public void mouseClicked(MouseEvent e){		
		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		String button = null;
		if(e.getButton() == MouseEvent.BUTTON1){
			button = "left";
		} else if(e.getButton() == MouseEvent.BUTTON3){
			button = "right";
		}
		
		itemsMapReference.checkMouseCollision((e.getY()+32)/32, (e.getX()+32)/32);			
		
		if(e.getX() >= 1120-32){
			hudReference.checkMouseCollision(e.getX()+32, e.getY()+32, button);
		}		
		
		savedRow = (e.getY()+32)/32;
		savedColumn = (e.getX()+32)/32;		
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		String button = null;
		if(e.getButton() == MouseEvent.BUTTON1){
			button = "left";
		} else if(e.getButton() == MouseEvent.BUTTON3){
			button = "right";
		}
		
		if((e.getX()+32 < 1120)){
			itemsMapReference.moveItem(savedRow, savedColumn, (e.getY()+32), (e.getX()+32));
		} else {
			hudReference.moveItem(savedRow, savedColumn, (e.getX()+32),(e.getY()+32));
		}
		hudReference.checkMouseReleased((e.getY()+32)/32, (e.getX()+32)/32, button);
	}
	
	@Override
	public void mouseDragged(MouseEvent e){
		
	}
}
