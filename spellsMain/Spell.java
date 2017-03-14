package spellsMain;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import gameCharactersMain.GameObjects;
import tibia.MapSuper;
import tibia.Point;

public class Spell {
  protected BufferedImage image;
  protected LinkedList<Point> points;
  protected LinkedList<Point> pointsOnMap;
  protected int animationFrame;
  protected Timer timerSetAnimationFrame;
  protected int casterRow,casterColumn,casterDirection;
  protected boolean onTarget;
  protected boolean isMonster;

  protected int spellDmg;
  protected Color color;

  public Spell(int casterRow, int casterColumn, int casterDirection, boolean isMonster){
    points = new LinkedList<Point>();
    pointsOnMap = new LinkedList<Point>();
    animationFrame = 0;
    this.casterRow = casterRow;
    this.casterColumn = casterColumn;
    this.casterDirection = casterDirection;
    this.isMonster = isMonster;    
  }

  public void setAnimationFrame(){
    timerSetAnimationFrame = new Timer();
    timerSetAnimationFrame.schedule(new TimerTask() {
      @Override
      public void run() {
        animationFrame++;
      }
    }, 0,200);

    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {      	
        checkForCollision();
      }
    }, 100);

  }

  public void drawSpell(Graphics g){
    for(Point tmp: points){
      switch(casterDirection){
        case 0:
          g.drawImage(image.getSubimage( animationFrame * 32, 0, 32, 32), ((casterColumn - MapSuper.getInitColumn()) * 32) + (tmp.getColumn() * 32) - MapSuper.getTmpX(), ((casterRow - MapSuper.getInitRow()) * 32) - (tmp.getRow() * 32) - MapSuper.getTmpY() , null);
          break;
        case 1:
          g.drawImage(image.getSubimage( animationFrame * 32, 0, 32, 32), ((casterColumn - MapSuper.getInitColumn()) * 32) - (tmp.getRow() * 32) - MapSuper.getTmpX(), ((casterRow - MapSuper.getInitRow()) * 32) - (tmp.getColumn() * 32) - MapSuper.getTmpY(), null);
          break;
        case 2:
          g.drawImage(image.getSubimage( animationFrame * 32, 0, 32, 32), ((casterColumn - MapSuper.getInitColumn()) * 32) + (tmp.getRow() * 32) - MapSuper.getTmpX(), ((casterRow - MapSuper.getInitRow()) * 32) + (tmp.getColumn() * 32) - MapSuper.getTmpY(), null);
          break;
        case 3:
          g.drawImage(image.getSubimage( animationFrame * 32, 0, 32, 32), ((casterColumn - MapSuper.getInitColumn()) * 32) + (tmp.getColumn() * 32) - MapSuper.getTmpX(), ((casterRow - MapSuper.getInitRow()) * 32) + (tmp.getRow() * 32) - MapSuper.getTmpY() , null);
          break;
        default:
          break;
      }

      if(animationFrame > 5){
        timerSetAnimationFrame.cancel();
        new SpellsMethods("remove",this).start();
      }
    }
  }

  public void assignPointsOnMap(){
    if(casterDirection == 0){
      for(Point tmp: points){
        pointsOnMap.add(new Point(casterColumn + tmp.getColumn(), casterRow - tmp.getRow()));
      }
    } else if(casterDirection == 1){
      for(Point tmp: points){
        pointsOnMap.add(new Point(casterColumn - tmp.getRow(), casterRow - tmp.getColumn()));
      }
    } else if(casterDirection == 2){
      for(Point tmp: points){
        pointsOnMap.add(new Point(casterColumn + tmp.getRow(), casterRow + tmp.getColumn()));
      }
    } else if(casterDirection == 3){
      for(Point tmp: points){
        pointsOnMap.add(new Point(casterColumn + tmp.getColumn(), casterRow + tmp.getRow()));
      }
    }
  }

  public void checkForCollision(){
    for(Point tmp: pointsOnMap){
      GameObjects.collision(tmp.getRow(),tmp.getColumn(),spellDmg, isMonster, color);
    }
  }

  public boolean isOnTarget() {
    return onTarget;
  }

  public void setCasterRow(int casterRow) {
    this.casterRow = casterRow;
  }

  public void setCasterColumn(int casterColumn) {
    this.casterColumn = casterColumn;
  }

  public void setCasterDirection(int casterDirection) {
    this.casterDirection = casterDirection;
  }
}
