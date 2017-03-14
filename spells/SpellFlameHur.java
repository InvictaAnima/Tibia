package spells;

import java.awt.Color;
import java.util.Random;

import spellsMain.Spell;
import spellsMain.SpellImages;
import tibia.Point;

public class SpellFlameHur extends Spell {

  public SpellFlameHur(int casterRow, int casterColumn, int casterDirection, boolean isMonster){
    super(casterRow,casterColumn,casterDirection,isMonster);
    image = SpellImages.getImage("fire");

    points.add(new Point(1,0));
    points.add(new Point(2,0));
    points.add(new Point(2,1));
    points.add(new Point(2,-1));
    points.add(new Point(3,0));
    points.add(new Point(3,1));
    points.add(new Point(3,-1));
    points.add(new Point(3,2));
    points.add(new Point(3,-2));

    assignPointsOnMap();
    spellDmg = new Random(System.nanoTime()).nextInt(80); 
    color = Color.pink;

    setAnimationFrame();
  }
}
