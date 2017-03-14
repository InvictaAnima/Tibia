package specialEffects;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class Effect {
	protected int row,column; //location on the screen
	protected int tmpY;
	Timer timerUpdate;
	
	public Effect(int x, int y){
		this.row = x;
		this.column = y;
		
		timerUpdate = new Timer();
		timerUpdate.schedule(new TimerTask(){
      @Override
      public void run(){
        update();
      }
    }, 0, 60);
	}
	
	
	public void draw(Graphics g){
		
	}
	
	public void update(){
		
	}
	
	protected void cancelEffect(){
		timerUpdate.cancel();
		SpecialEffects.removeEffect(this);
	}
}
