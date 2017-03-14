package specialEffects;

import java.awt.Graphics;
import java.util.LinkedList;

public class SpecialEffects {

	private static LinkedList <Effect> effects;
	
	public SpecialEffects(){
		effects = new LinkedList<>();
	}
	
	public synchronized static void drawEffects(Graphics g){
		for(Effect tmp : effects){
			tmp.draw(g);
		}
	}
	
	public synchronized static void addEffect(Effect effect){
		effects.add(effect);
	}
	
	public synchronized static void removeEffect(Effect effect){
		effects.remove(effect);
	}
	
}
