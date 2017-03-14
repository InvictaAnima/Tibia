package specialEffects;

public class EffectsMethods extends Thread {
	private Effect effect;
  private String toDo;

  public EffectsMethods(String toDo,Effect effect){
    this.effect = effect;
    this.toDo = toDo;
  }

  @Override
  public void run(){
    if(toDo.equals("add")){
    	SpecialEffects.addEffect(effect);
    }

    if(toDo.equals("remove")){
    	SpecialEffects.removeEffect(effect);
    }

  }
}
