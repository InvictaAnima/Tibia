package monsters;

import gameCharactersMain.CharacterImages;
import gameCharactersMain.Monster4;

public class GiantSpider extends Monster4{
  public GiantSpider(int row, int column){
    super(row,column);
    name = "\0    Giant Spider";
    image = CharacterImages.getImage("gs");
    body = CharacterImages.getImage("gsBody");

    maxHealthPoints = 2000;
    healthPoints = 2000;
    experience = 900;
    attackMin = 0;
    attackMax = 250;
    
    
    totalAnimationFrames = 3;

//    availableSpells.add("fireBomb");
//    availableSpells.add("fireWave");
  }
}
