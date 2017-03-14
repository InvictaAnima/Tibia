package gameCharactersMain;
import java.awt.*;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Monster4 extends Monster {
	
  public Monster4(int row, int column){
    super(row,column);    
    availableSpells = new LinkedList<>();
    movementSpeed = 50;
    isMonster = true;
    totalAnimationFrames = 8;    

    updateTablePosition();
  }

  @Override
  public void draw(Graphics g, int mapX, int mapY){
    if(healthPoints >= 0){
      g.drawImage(image.getSubimage( animationFrame * 64, direction * 64, 64, 64), tmpColumn * 32 + tmpX - mapX, tmpRow * 32 + tmpY - mapY, null);
      g.setColor(Color.BLACK);      
      for(int i=0;i<2;i++){
    		for(int j=0;j<2;j++){
    			g.drawRect((destColumn + i)* 32 - mapX, (destRow + j)* 32 - mapY, 32, 32);
    		}
      }
      
    } else {
      g.drawImage(body.getSubimage( animationFrame * 64, 0, 64, 64), tmpColumn * 32 - mapX, tmpRow * 32 - mapY, null);
    }    
  }
  
  @Override
  protected void updateTablePosition(){  	
  	
  	for(int i=0;i<2;i++){
  		for(int j=0;j<2;j++){
  			GameObjects.clearField(row+i, column+j);  			
  		}
  	}
  	
  	for(int i=0;i<2;i++){
  		for(int j=0;j<2;j++){
  			GameObjects.addField(destRow+i, destColumn+j,this);  			
  		}
  	}	  
  	
  }
  
  @Override
  public boolean setDestRow(int destRow) {
  	boolean flag = true;
  	
  	for(int i=0;i<2;i++){
  		for(int j=0;j<2;j++){
  			if(!(GameObjects.getCharacterReference(destRow+i,column+j) == null || GameObjects.getCharacterReference(destRow+i,column+j) == this)){
  				flag = false;
  			}
  		}
  	}  	
  	
    if(flag == true && isMoving == false){
      if(isMonster){
      	for(int i=0;i<2;i++){
      		for(int j=0;j<2;j++){
      			GameObjects.markFieldAsTaken(destRow+i,column+j);
      		}
      	}
      }
      this.destRow = destRow;
      setTimerMove();
      isMoving = true;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean setDestColumn(int destColumn) {
  	boolean flag = true;
  	
  	for(int i=0;i<2;i++){
  		for(int j=0;j<2;j++){
  			if(!(GameObjects.getCharacterReference(row+i,destColumn+j) == null || GameObjects.getCharacterReference(row+i,destColumn+j) == this)){
  				flag = false;
  			}
  		}
  	}
  	
    if(flag && isMoving == false){
      if(isMonster){
      	for(int i=0;i<2;i++){
      		for(int j=0;j<2;j++){
      				GameObjects.markFieldAsTaken(row+i,destColumn+j);
      		}
      	}
      }
      this.destColumn = destColumn;
      setTimerMove();
      isMoving = true;
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public void setBodyDecomposing(){
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        animationFrame++;

        if(animationFrame > 2){
        	for(int i=0;i<2;i++){
        		for(int j=0;j<2;j++){
        			GameObjects.clearField(row+i,column+j);
        		}
        	}
          this.cancel();
        }
      }
    }, 2000, 2000);//60
  }
}

