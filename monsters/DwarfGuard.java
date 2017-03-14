package monsters;

import gameCharactersMain.CharacterImages;
import gameCharactersMain.Monster;

public class DwarfGuard extends Monster {
  public DwarfGuard(int row, int column){
    super(row,column);
    name = "Dwarf Guard";
    image = CharacterImages.getImage("dwarfGuard");
    body = CharacterImages.getImage("dwarfGuardBody");

    maxHealthPoints = 20;
    healthPoints = 20;
    experience = 165;
    attackMin = 0;
    attackMax = 120;

//    availableSpells.add("fireBomb");
//    availableSpells.add("fireWave");
  }
}
