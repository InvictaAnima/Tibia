package spells;

import java.awt.Color;
import java.util.Random;

import spellsMain.Spell;
import spellsMain.SpellImages;
import tibia.Point;

public class SpellSanBlast extends Spell{

  public SpellSanBlast(int casterRow, int casterColumn, int casterDirection, boolean isMonster){
    super(casterRow,casterColumn,casterDirection,isMonster);
    image = SpellImages.getImage("san2");

    points.add(new Point(0,0));
    points.add(new Point(0,1));
    points.add(new Point(0,-1));
    points.add(new Point(1,0));
    points.add(new Point(-1,0));

    assignPointsOnMap();
    spellDmg = new Random(System.nanoTime()).nextInt(80); 
    color = Color.yellow;
    onTarget = true;

    setAnimationFrame();    
  }
}
