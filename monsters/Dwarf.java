package monsters;

import gameCharactersMain.CharacterImages;
import gameCharactersMain.Monster;

public class Dwarf extends Monster {

  public Dwarf(int row, int column){
    super(row,column);
    name = "Dwarf";
    image = CharacterImages.getImage("dwarf");
    body = CharacterImages.getImage("dwarfBody");


    maxHealthPoints = 20;
    healthPoints = 20;
    experience = 45;
    attackMin = 0;
    attackMax = 25;

//    availableSpells.add("fireBomb");
//    availableSpells.add("fireWave");
  }

}
