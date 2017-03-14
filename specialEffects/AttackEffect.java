package specialEffects;

import java.awt.Color;
import java.awt.Graphics;

import tibia.MapSuper;

public class AttackEffect extends Effect {
	int dmg;
	Color color;
	
	public AttackEffect(int row, int column, int dmg, Color color){
		super(row,column);
		this.dmg = dmg;
		this.color = color;		
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(color);
		//g.drawString("" + dmg, ((row - MapSuper.getInitRow()) * 32) - MapSuper.getTmpY(), ((column - MapSuper.getInitColumn()) * 32) - MapSuper.getTmpX() + tmpY);
		g.drawString("" + dmg, ((column - MapSuper.getInitColumn()) * 32) - MapSuper.getTmpX(), ((row - MapSuper.getInitRow()) * 32) - MapSuper.getTmpY() + tmpY) ; //lel xD
	}
	
	@Override
	public void update(){
		tmpY-=2;
		
		if(tmpY<-48){
			cancelEffect();
		}
	}
}
