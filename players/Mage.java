package players;
import java.awt.*;

import gameCharactersMain.CharacterImages;
import gameCharactersMain.Player;
import tibia.Map;

/**
 * Created by Gargamel on 2016-07-04.
 */
public class Mage extends Player {

  public Mage(Map map, int row, int column){
    super(map,row,column);

    name = "Saitusek";
    image = CharacterImages.getImage("mage");

    healthPerLevel = 5;
    maxHealthPoints = 185;
    healthPoints = 185;
    healthPointsRegeneration = 1;
    
    manaPerLevel = 30;
    maxManaPoints = 90;
    manaPoints = 90;
    manaPointsRegeneration = 2;
  }

  @Override
  public void draw(Graphics g){
    g.drawImage(image.getSubimage( animationFrame * 64, direction * 64, 64, 64), columnOnScreen * 32 + tmpX - 32, rowOnScreen * 32 + tmpY - 32, null);
    g.setColor(Color.BLACK);
    g.drawRect(columnOnScreen * 32, rowOnScreen * 32, 32, 32);

    playerDrawHpBar(g);
    playerDrawName(g);    
  }

  @Override
  public void update(){

  }
  
  @Override
  protected void setManaPerMagicLevel(){
  	manaPerMagicLevel = 
  			new int[] {1600,1760,1936,2130,2343,2577,2834,3118,3430,3773,
  								 4150,4565,5021,5524,6076,6684,7352,8087,8896,9785};
  }


}
