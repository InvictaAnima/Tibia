package spells;

import java.awt.Color;
import java.util.Random;

import spellsMain.Spell;
import spellsMain.SpellImages;
import tibia.Point;

public class SpellStorm extends Spell{

  public SpellStorm(int casterRow, int casterColumn, int casterDirection, boolean isMonster){
    super(casterRow,casterColumn,casterDirection,isMonster);
    image = SpellImages.getImage("storm");

    points.add(new Point(1,0));
    points.add(new Point(2,0));
    points.add(new Point(2,1));
    points.add(new Point(2,-1));
    points.add(new Point(3,0));
    points.add(new Point(1,1));
    points.add(new Point(1,-1));
    points.add(new Point(1,2));
    points.add(new Point(1,-2));
    points.add(new Point(-3,0));
    points.add(new Point(-2,0));
    points.add(new Point(-2,1));
    points.add(new Point(-2,-1));
    points.add(new Point(-1,0));
    points.add(new Point(-1,1));
    points.add(new Point(-1,-1));
    points.add(new Point(-1,2));
    points.add(new Point(-1,-2));
    points.add(new Point(0,1));
    points.add(new Point(0,-1));
    points.add(new Point(0,2));
    points.add(new Point(0,-2));
    points.add(new Point(0,3));
    points.add(new Point(0,-3));

    assignPointsOnMap();
    spellDmg = new Random(System.nanoTime()).nextInt(80); 
    color = Color.blue;

    setAnimationFrame();
  }
}
