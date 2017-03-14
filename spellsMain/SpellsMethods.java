package spellsMain;

public class SpellsMethods extends Thread{
  private Spell spell;
  private String toDo;

  public SpellsMethods(String toDo,Spell spell){
    this.spell = spell;
    this.toDo = toDo;
  }

  @Override
  public void run(){
    if(toDo.equals("add")){
      CastedSpells.castSpell(spell);
    }

    if(toDo.equals("remove")){
      CastedSpells.removeSpell(spell);
    }
  }
}
