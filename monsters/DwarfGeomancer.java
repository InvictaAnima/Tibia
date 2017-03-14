package monsters;

import gameCharactersMain.CharacterImages;
import gameCharactersMain.Monster;

public class DwarfGeomancer extends Monster {
  public DwarfGeomancer(int row, int column){
    super(row,column);
    name = "Dwarf Geomancer";
    image = CharacterImages.getImage("dwarfGeomancer");
    body = CharacterImages.getImage("dwarfGeomancerBody");

    maxHealthPoints = 20;
    healthPoints = 20;
    experience = 265;
    attackMin = 0;
    attackMax = 40;

    availableSpells.add("sanBlast");
   // availableSpells.add("fireWave");
  }
}
