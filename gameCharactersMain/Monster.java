package gameCharactersMain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import spells.SpellFireBomb;
import spells.SpellFlameHur;
import spells.SpellSanBlast;
import spells.SpellStorm;
import spellsMain.SpellsMethods;


public class Monster extends GameCharacter {

  protected LinkedList<String> availableSpells;
  protected int mapX,mapY; //to draw hp and name
  protected BufferedImage body;
  
  protected int experience;
  protected int attackMin;
  protected int attackMax;

  public Monster(int row, int column){
    super(row,column);
    availableSpells = new LinkedList<>();
    movementSpeed = 50;
    isMonster = true;
    totalAnimationFrames = 3;
  }

  @Override
  public void draw(Graphics g){

  }

  @Override
  public void draw(Graphics g, int mapX, int mapY){
    if(healthPoints >= 0){
      this.mapX = mapX;
      this.mapY = mapY;
      g.drawImage(image.getSubimage( animationFrame * 32, direction * 32, 32, 32), tmpColumn * 32 + tmpX - mapX, tmpRow * 32 + tmpY - mapY, null);
      g.setColor(Color.BLACK);
      g.drawRect(destColumn * 32 - mapX, destRow * 32 - mapY, 32 , 32 );
    } else {
      g.drawImage(body.getSubimage( animationFrame * 32, 0, 32, 32), tmpColumn * 32 - mapX, tmpRow * 32 - mapY, null);
    }
  }


  @Override
  public void monsterDrawHpBar(Graphics g,int mapX, int mapY){
    g.setColor(Color.red);
    g.fillRect(tmpColumn * 32 + tmpX - mapX, tmpRow * 32 + tmpY - mapY - 16, 32, 4);
    g.setColor(Color.green);
    g.fillRect(tmpColumn * 32 + tmpX - mapX, tmpRow * 32 + tmpY - mapY - 16, (int)(32 * ((float)healthPoints/(float)maxHealthPoints)), 4);
    g.setColor(Color.black);
    g.drawRect(tmpColumn * 32 + tmpX - mapX, tmpRow * 32 + tmpY - mapY - 16, 32, 4);
  }

  @Override
  public void monsterDrawName(Graphics g,int mapX, int mapY){
    g.setColor(Color.green);
    g.drawString(name,tmpColumn * 32 + tmpX - mapX + 16 - (name.length() * 3), tmpRow * 32 + tmpY - mapY - 20);
  }

  @Override
  public void update(){
    useSpell();
    attack();
  }

  protected void useSpell(){
    if(availableSpells.isEmpty() == false && spellCooldown == false){
      Random random = new Random(System.currentTimeMillis());
      String spellTmp = availableSpells.get(random.nextInt(availableSpells.size()));

      switch(spellTmp){
        case "fireBomb" :
          new SpellsMethods("add",new SpellFireBomb(target.getRow(), target.getColumn(),target.getDirection(),true)).start();
          break;
        case "fireWave" :
          new SpellsMethods("add",new SpellFlameHur(getRow(), getColumn(),getDirection(),true)).start();
          break;
        case "sanBlast" :
          new SpellsMethods("add",new SpellSanBlast(target.getRow(), target.getColumn(),target.getDirection(),true)).start();
          break;
        case "storm" :
          new SpellsMethods("add",new SpellStorm(target.getRow(), target.getColumn(),target.getDirection(),true)).start();
          break;
        default:
          break;
      }
      setSpellCooldown(random.nextInt(6) + 2);
    }
  }

  public void setBodyDecomposing(){
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        animationFrame++;
        
        if(animationFrame > 2){
          GameObjects.clearField(row,column);
          this.cancel();
        }
      }
    }, 2000, 2000);//60
  }

  @Override
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
    if(healthPoints <= 0){
    	GameObjects.gainExperience(experience);
      timerMove.cancel();
      GameObjects.changeTablePosition(row,column,destRow,destColumn,this);
      row = tmpRow  = destRow;
      column = tmpColumn = destColumn;
      animationFrame = 0;
      this.setBodyDecomposing();
    }
  }
  
  @Override
  protected void maleeAttack(){
  	Random random = new Random(System.nanoTime());
  	int dmg = random.nextInt(attackMax);
  	//SpecialEffects.addEffect(new AttackEffect(target.getRow(),target.getColumn(),dmg, Color.red));
  	//new EffectsMethods("add",new AttackEffect(target.getRow(),target.getColumn(),dmg, Color.red)).start();
  	GameObjects.collision(target.getRow(), target.getColumn(), dmg, isMonster, Color.red);  	
  }  

}
