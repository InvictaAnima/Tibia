package spellsMain;
import java.awt.*;
import java.util.*;

public class CastedSpells {
  private static LinkedList<Spell> castedSpells;

  public CastedSpells(){
    castedSpells = new LinkedList<>();
  }

  public synchronized static void castSpell(Spell spell){
        castedSpells.add(spell);
  }

  public synchronized static void removeSpell(Spell spell){
      castedSpells.remove(spell);
  }

  public synchronized static void drawSpells(Graphics g){
    for(Spell tmp : castedSpells){
      tmp.drawSpell(g);
    }
  }
}
