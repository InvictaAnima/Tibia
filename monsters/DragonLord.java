package monsters;

import gameCharactersMain.CharacterImages;
import gameCharactersMain.Monster4;

public class DragonLord extends Monster4 {

  public DragonLord(int row, int column){
    super(row,column);
    name = "Dragon Lord";
    image = CharacterImages.getImage("dl");
    body = CharacterImages.getImage("dlBody");

    maxHealthPoints = 2000;
    healthPoints = 2000;
    experience = 2100;
    attackMin = 0;
    attackMax = 200;

    availableSpells.add("fireBomb");
    availableSpells.add("fireWave");
    availableSpells.add("storm");
  }

}
