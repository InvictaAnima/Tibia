package hud;

import java.awt.image.BufferedImage;

public class HudPart {
	protected int x,y; // location on the screen
	protected int yEnd;
	protected boolean isMaximized;
	
	protected BufferedImage tabOpen;
  protected BufferedImage tabClosed;
	
	public HudPart(int x,int y){
		this.x = x;
		this.y = y;
		isMaximized = true;
	}	
	
	public void checkMouseCollision(int mouseX, int mouseY){
	
	}
	
	public void resize(){
		if(isMaximized){
			isMaximized = false;
			yEnd = this.y + 27;
		} else {
			isMaximized = true;
			yEnd = this.y + 213;
		}		
	}
	
	public int getyEnd() {
		return yEnd;
	}	
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}

}
