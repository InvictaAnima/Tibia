package hud;

import java.awt.Color;
import java.awt.Graphics;

import gameCharactersMain.Player;
import tibia.ImageLoader;

public class CharacterProfile extends HudPart {
	private Player playerReference;
	
	public CharacterProfile(int x,int y, Player player){
  	super(x,y);
  	this.playerReference = player;
  	this.yEnd = this.y + 213;
  	loadImages();
  }

  private void loadImages(){    
  	tabOpen = ImageLoader.loadImage("/img/characterProfileOpen.png");
  	tabClosed = ImageLoader.loadImage("/img/characterProfileClosed.png");
  }

  public void draw(Graphics g){
  	
  	if(isMaximized){
  		int gap = 30;
  		int gapStrap = 4;
  		int firstX = x + 40;
  		int firstY = y + 100;
  		g.drawImage(tabOpen,x,y,null);
  	
  		g.setColor(Color.white);  		  		  		
  		g.drawString("Health: " + playerReference.getHealthPoints(), firstX, firstY - 2 * gap);
	  		g.setColor(new Color(255,230,102));
	  		g.fillRect(firstX, firstY - 2 * gap + gapStrap, 100, 6);
	  		//g.setColor(new Color(0,153,0));  	
	  		g.setColor(Color.red); 
	  		g.fillRect(firstX, firstY - 2 * gap + gapStrap,(int)(100 * ((float)(playerReference.getHealthPoints())/(playerReference.getMaxHealthPoints()))), 6);
	  		g.setColor(Color.BLACK);
	  		g.drawRect(firstX, firstY - 2 * gap + gapStrap, 100, 6);
	  	g.setColor(Color.white);  		  		  		
	  	g.drawString("Mana: " + playerReference.getManaPoints(), firstX, firstY - 1 * gap);
		  	g.setColor(new Color(255,230,102));
		  	g.fillRect(firstX, firstY - 1 * gap + gapStrap, 100, 6);
		  	//g.setColor(new Color(0,153,0));  	 
		  	g.setColor(Color.blue);
		  	g.fillRect(firstX, firstY - 1 * gap + gapStrap,(int)(100 * ((float)(playerReference.getManaPoints())/(playerReference.getMaxManaPoints()))), 6);
		  	g.setColor(Color.BLACK);
		  	g.drawRect(firstX, firstY - 1 * gap + gapStrap, 100, 6);
	  	g.setColor(Color.white);
  		g.drawString("Level: " + playerReference.getLevel(), firstX, firstY);
  	  	g.setColor(new Color(255,230,102));
  	  	g.fillRect(firstX, firstY + gapStrap, 100, 6);
  	  	g.setColor(new Color(0,153,0));  	    
  	  	g.fillRect(firstX, firstY + gapStrap,(int)(100 * ((float)(playerReference.getExperience()-playerReference.getExperienceTableElement(playerReference.getLevel()-1))/(playerReference.getExperienceTableElement(playerReference.getLevel())-playerReference.getExperienceTableElement(playerReference.getLevel()-1)))), 6);
  	  	g.setColor(Color.BLACK);
  	  	g.drawRect(firstX, firstY + gapStrap, 100, 6);
  	  g.setColor(Color.white);
  		g.drawString("MagicLvl: " + playerReference.getMagicLevel(), firstX, firstY + gap);
  	  	g.setColor(new Color(255,230,102));
  	  	g.fillRect(firstX, firstY + 1 * gap + gapStrap, 100, 6);
  	  	g.setColor(new Color(0,153,0));  	    
  	  	g.fillRect(firstX, firstY + 1 * gap + gapStrap,(int)(100 * ((float)playerReference.getUsedMana()/playerReference.getManaPerMagicLevelTableElement(playerReference.getMagicLevel()))), 6);
  	  	g.setColor(Color.BLACK);
  	  	g.drawRect(firstX, firstY + 1 * gap  + gapStrap, 100, 6);
  	  g.setColor(Color.white);
  		g.drawString("Attack: 8", firstX, firstY + 2 * gap);
  	  	g.setColor(new Color(255,230,102));
  	  	g.fillRect(firstX, firstY + 2 * gap + gapStrap, 100, 6);
  	  	g.setColor(new Color(0,153,0));  	    
  	  	g.fillRect(firstX, firstY + 2 * gap + gapStrap,(int)(100 * ((float)1/2)), 6);
  	  	g.setColor(Color.BLACK);
  	  	g.drawRect(firstX, firstY + 2 * gap  + gapStrap, 100, 6);
  	  g.setColor(Color.white);
  		g.drawString("Shielding: 8", firstX, firstY + 3 * gap);
  		  g.setColor(new Color(255,230,102));
  	    g.fillRect(firstX, firstY + 3 * gap + gapStrap, 100, 6);
  	    g.setColor(new Color(0,153,0));  	    
  	    g.fillRect(firstX, firstY + 3 * gap + gapStrap,(int)(100 * ((float)1/2)), 6);
  	    g.setColor(Color.BLACK);
  	  	g.drawRect(firstX, firstY + 3 * gap  + gapStrap, 100, 6);
  		
  	} else {
  		g.drawImage(tabClosed,x,y,null);
  	}
  }
  
  @Override
  public void checkMouseCollision(int mouseX, int mouseY){ 
  	
  	//minimalize/maximalize
  	if(mouseX >= (this.x+151) && mouseX <= this.x+151+16  
  			&& mouseY >= (this.y+4) && mouseY <= (this.y)+16){
  		this.resize();
  	}
	}
}
