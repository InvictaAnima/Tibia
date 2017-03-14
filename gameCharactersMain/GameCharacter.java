package gameCharactersMain;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import aStar.AStar;
import tibia.Point;



public abstract class GameCharacter {
  protected int row,column; //indexes in the array
  protected int tmpRow,tmpColumn; //used while moving
  protected int destRow,destColumn; //destination
  protected int tmpX,tmpY; //used to draw moving character
  protected boolean isMoving; //flag determines if character is already moving, false - no, true - yes
  protected int direction; // 0 - up, 1 - left, 2 - right, 3 - down
  protected int animationFrame;
  protected int movementSpeed; //less is more
  protected String name;
  protected BufferedImage image;
  protected Timer timerMove;
  protected boolean isMonster;
  protected int totalAnimationFrames;

  protected int maxHealthPoints;
  protected int healthPoints; 
  protected int healthPointsRegeneration; //how mych hp per 2s
  
  protected boolean spellCooldown;
  protected boolean attackCooldown;
  protected GameCharacter target;
  protected LinkedList<Point> wayPoints;

  protected Point previousPoint;

  public GameCharacter(int row,int column){
    this.row = this.tmpRow = this.destRow = row;
    this.column = this.tmpColumn =  this.destColumn = column;
    tmpX = tmpY = 0;
    isMoving = false;
    direction = 1;
    animationFrame = 0;
    totalAnimationFrames = 8;
    
    wayPoints = new LinkedList<>();
    
    new Timer().schedule(new TimerTask(){
      @Override
      public void run(){
        regenerateHP();
      }
    }, 0, 2000);
   }

  protected void setTimerMove(){
    tmpRow = row;
    tmpColumn = column;
    timerMove = new Timer();
    timerMove.schedule(new TimerTask() {
     @Override
     public void run() {
       move();
     }
    }, 0, movementSpeed);//60

  }

  public void draw(Graphics g){

  }

  public void draw(Graphics g, int mapX, int mapY){

  }

  public void monsterDrawHpBar(Graphics g,int mapX, int mapY){
  }

  public void monsterDrawName(Graphics g,int mapX, int mapY){
  }

  public void update(){

  }
  
  protected void regenerateHP(){
  	healthPoints += healthPointsRegeneration;
  	if(healthPoints > maxHealthPoints){
  		healthPoints = maxHealthPoints;
  	}
  }

  protected void move(){
    if(destRow < tmpRow){
      direction = 0;
    } else if(destRow > tmpRow){
      direction = 3;
    } else if(destColumn > tmpColumn){
      direction = 2;
    } else {
      direction = 1;
    }

    if(destRow < tmpRow){
      tmpY--;
    } else if(destRow > tmpRow){
      tmpY++;
    }

    if(destColumn < tmpColumn){
      tmpX--;
    } else if(destColumn > tmpColumn){
      tmpX++;
    }

    if((tmpX % 2 == 0) && (tmpY % 2 == 0)){
      animationFrame++;
      animationFrame = animationFrame % totalAnimationFrames;
    }

    if(((destRow * 32) == (tmpRow * 32 + 2 * tmpY)) && ((destColumn * 32) == (tmpColumn * 32  + 2 * tmpX))){
      if(isMonster){
      	updateTablePosition();        
      }

      row = destRow;
      column = destColumn;
    }

    if(tmpY >= 32 || tmpX >= 32 || tmpY <= -32 || tmpX <= -32){
      tmpRow = destRow;
      tmpColumn = destColumn;
      tmpX = tmpY = 0;
      timerMove.cancel();
      isMoving = false;
    }
  }
  
  protected void updateTablePosition(){
  	GameObjects.changeTablePosition(row,column,destRow,destColumn,this);
  }

  public void attack(){
    if(this.isMoving == false && target != null && ((Math.abs(row - target.row) > 1) || (Math.abs(column - target.column) > 1))){
    	Point goal = GameObjects.selectClosestToTargetField(target.row,target.column,this);
    	if(goal != null){
    	 wayPoints = new AStar().AStarProcedure(new Point(row, column), goal);
    	}
    	 if(wayPoints != null && !wayPoints.isEmpty()){
    		Point nextPoint = wayPoints.getLast();
    	 	setNextPoint(nextPoint.getRow(),nextPoint.getColumn());
    	 	wayPoints.removeLast();
    	 }
    }    
    
    if(attackCooldown == false){
    	if(Math.abs(target.row - row) <= 1 && Math.abs(target.column - column) <= 1){
    		maleeAttack();
    		setAttackCooldown(2);
    	}
    }
  }
  
  protected void maleeAttack(){
  	
  }

  public void setSpellCooldown(int delay){
    spellCooldown = true;
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        spellCooldown = false;
      }
    }, delay * 1000); // s delay
  }
  
  public void setAttackCooldown(int delay){
  	attackCooldown = true;
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
      	attackCooldown = false;
      }
    }, delay * 1000); // s delay
  }

  public boolean setDestRow(int destRow) {
    if(isMoving == false && (GameObjects.getCharacterReference(destRow,column) == null || GameObjects.getCharacterReference(destRow,column) == this)){
      if(isMonster){
        GameObjects.markFieldAsTaken(destRow,column);
      }
      this.destRow = destRow;
      setTimerMove();
      isMoving = true;
      return true;
    } else {
      return false;
    }
  }

  public boolean setDestColumn(int destColumn) {
    if(isMoving == false && (GameObjects.getCharacterReference(row,destColumn) == null || GameObjects.getCharacterReference(row,destColumn) == this)){
      if(isMonster){
        GameObjects.markFieldAsTaken(row,destColumn);
      }
      this.destColumn = destColumn;
      setTimerMove();
      isMoving = true;
      return true;
    } else {
      return false;
    }
  }
  
  public boolean setNextPoint(int destRow,int destColumn){
  	if(isMoving == false && (GameObjects.getCharacterReference(destRow,destColumn) == null || GameObjects.getCharacterReference(destRow,destColumn) == this)){
      if(isMonster){
        GameObjects.markFieldAsTaken(destRow,destColumn);
      }
      this.destRow = destRow;
      this.destColumn = destColumn;
      setTimerMove();
      isMoving = true;
      return true;
    } else {
      return false;
    }
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public int getDirection() {
    return direction;
  }

  public boolean isMoving() {
    return isMoving;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public boolean isSpellCooldown() {
    return spellCooldown;
  }

  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }
  
  public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

  public int getHealthPoints() {
    return healthPoints;
  }

  public void setTarget(GameCharacter target) {
    this.target = target;
  }
}
