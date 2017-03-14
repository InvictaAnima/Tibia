package gameCharactersMain;
import java.awt.*;

import monsters.BlankCharacter;
import monsters.DragonLord;
import monsters.Dwarf;
import monsters.DwarfGeomancer;
import monsters.DwarfGuard;
import monsters.GiantSpider;
import specialEffects.AttackEffect;
import specialEffects.EffectsMethods;
import tibia.MapSuper;
import tibia.Point;


public class GameObjects extends MapSuper {

  private static GameCharacter tab[][];
  private static Player playerReference;

  public GameObjects(Player player){
    tab = new GameCharacter[rows][columns];
    playerReference = player;

    //tab[5][10] = new GiantSpider(5,10);
//    tab[10][15] = new Dwarf(10,15);
//    tab[12][17] = new DragonLord(12,17);    
//    tab[15][20] = new DwarfGeomancer(15,20);
    tab[20][30] = new DwarfGuard(20,30);
    tab[5][2] = new DwarfGeomancer(5,2);
    tab[22][30] = new DwarfGuard(22,30);  
    tab[21][30] = new DwarfGuard(21,30);
//    tab[6][2] = new DwarfGeomancer(6,2);
//    tab[12][30] = new DwarfGuard(12,30); 
//    tab[9][2] = new DwarfGeomancer(9,2);

    
    setTarget();
  }

  public void draw(Graphics g){
    for(int i=0; i < 22; i++)
      for(int j=0; j < 42; j++){
        if(tab[initRow + i][initColumn + j] != null){
          tab[initRow + i][initColumn + j].draw(g,initColumn * 32 + tmpX,initRow * 32 + tmpY);
        }
      }
  }

  public void drawNames(Graphics g){
    for(int i=0; i < 22; i++)
      for(int j=0; j < 42; j++){
        if(tab[initRow + i][initColumn + j] != null){
          if(tab[initRow + i][initColumn + j].getHealthPoints() > 0){
            tab[initRow + i][initColumn + j].monsterDrawName(g,initColumn * 32 + tmpX,initRow * 32 + tmpY);
          }
        }
      }
  }

  public void drawHpBars(Graphics g){
    for(int i=0; i < 22; i++)
      for(int j=0; j < 42; j++){
        if(tab[initRow + i][initColumn + j] != null){
          if(tab[initRow + i][initColumn + j].getHealthPoints() > 0){
            tab[initRow + i][initColumn + j].monsterDrawHpBar(g,initColumn * 32 + tmpX,initRow * 32 + tmpY);
          }
        }
      }
  }

  public void update(){
    for(int i=0; i < 22; i++)
      for(int j=0; j < 42; j++){
        if(tab[initRow + i][initColumn + j] != null){
          if(tab[initRow + i][initColumn + j].getHealthPoints() > 0){
            tab[initRow + i][initColumn + j].update();
          }
        }
      }
  }

  public static void collision(int row, int column, int healthPoints, boolean isMonster, Color color){
    if(!isMonster && tab[column][row] != null && tab[column][row].getHealthPoints() > 0){
      tab[column][row].setHealthPoints(tab[column][row].getHealthPoints() - healthPoints);
      new EffectsMethods("add",new AttackEffect(column, row, healthPoints, color)).start(); // xD fix plz      
    }

    if(isMonster && row == (playerReference.getColumn()) && column == (playerReference.getRow())){  // xD fix plz(spells)
      playerReference.setHealthPoints(playerReference.getHealthPoints() - healthPoints); 
      new EffectsMethods("add",new AttackEffect(column, row, healthPoints, color)).start();
    }
    
    if(isMonster && column == (playerReference.getColumn()) && row == (playerReference.getRow())){  // xD fix plz(melee)
      playerReference.setHealthPoints(playerReference.getHealthPoints() - healthPoints);
      new EffectsMethods("add",new AttackEffect(row, column, healthPoints, color)).start();
    }    
  }
  
  //may casue errors
//  public static GameCharacter getCharacterReference(int row, int column){
//    return tab[row][column];
//  }
  
  public static GameCharacter getCharacterReference(int row, int column){
  	if(row == playerReference.getRow() && column == playerReference.getColumn()){
  		return playerReference;
  	} else {
  		return tab[row][column];
  	}
  }

  public static void setTarget(){
    for(int i=0;i<rows;i++)
      for(int j=0;j<columns;j++){
        if(tab[i][j] != null){
          tab[i][j].setTarget(playerReference);
        }
      }
  }

  public synchronized static void markFieldAsTaken(int row, int column){
    tab[row][column] = new BlankCharacter(row,column);
  }

  public synchronized static void changeTablePosition(int row, int column, int newRow, int newColumn, GameCharacter gameCharacter){
    tab[row][column] = null;
    tab[newRow][newColumn] = gameCharacter;
  }

  public synchronized static void clearField(int row, int column){
    tab[row][column] = null;
  }
  
  public synchronized static void addField(int row, int column, GameCharacter gameCharacter){
    tab[row][column] = gameCharacter;
  }
  
  public synchronized static boolean checkField(int row, int column, GameCharacter gameCharacter){ //true if available
  	if(row >=0 && column >=0 && row < rows && column < columns){ 
  		return (GameObjects.getCharacterReference(row,column) == null || GameObjects.getCharacterReference(row,column) == gameCharacter);
  	}
  	return false;
  }
  
  public synchronized static Point selectClosestToTargetField(int targetRow, int targetColumn, GameCharacter gameCharacter){  	
  	
  	int i=-1;
  	int j=-1;
  	int iMax=1;
  	int jMax=1;
  	
  	while(i<=iMax){
  		
  		while(j<=jMax){
  			if(checkField(targetRow+i,targetColumn+j,gameCharacter) == true){
  				return new Point(targetRow+i,targetColumn+j);
  			}  			
  			++j;
    	}
  		++i;  		
  		
  		if((i == iMax+1) && (j == jMax+1)){
				iMax++;
				jMax++;
				i = -1 * iMax;
				j = -1 * jMax;
			}
  		
  		j = -1 * jMax;
  	}
  	
  	return null;
  }
  
  public static void gainExperience(int experience){
  	playerReference.setExperience(playerReference.getExperience() + experience);
  }  

}
