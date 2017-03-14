package spells;

import java.awt.Color;
import java.util.Random;

import spellsMain.Spell;
import spellsMain.SpellImages;
import tibia.Point;

public class SpellFireBomb extends Spell {

  public SpellFireBomb(int casterRow, int casterColumn, int casterDirection, boolean isMonster){
    super(casterRow,casterColumn,casterDirection,isMonster);
    image = SpellImages.getImage("fire");

    points.add(new Point(0,0));
    points.add(new Point(0,1));
    points.add(new Point(0,-1));
    points.add(new Point(1,1));
    points.add(new Point(1,0));
    points.add(new Point(1,-1));
    points.add(new Point(-1,1));
    points.add(new Point(-1,0));
    points.add(new Point(-1,-1));

    assignPointsOnMap();
    spellDmg = new Random(System.nanoTime()).nextInt(80); 
    color = Color.pink;
    onTarget = true;

    setAnimationFrame();
  }
}
