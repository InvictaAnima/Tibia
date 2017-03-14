package tibia;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import gameCharactersMain.GameObjects;
import gameCharactersMain.Player;
import spells.SpellEnergyBeam;
import spells.SpellFlameHur;
import spells.SpellStorm;
import spellsMain.SpellsMethods;

public class KeyInput extends KeyAdapter {
  private Player player;
  private boolean[] keyDown = new boolean[4]; // 0 - up, 1 - left, 2 - right, 3 - down
  private final Set<Integer> pressed = new HashSet<Integer>();
  private boolean ctrlPushed;

  public KeyInput(Player player){
    this.player = player;

//    for(boolean tmp : keyDown){
//      tmp = false;
//    }

    ctrlPushed = false;
  }

  @Override
  public void keyPressed(KeyEvent e){
    pressed.add(e.getKeyCode());

    for(int i : pressed){
      switch(i){
        case KeyEvent.VK_UP:
          if(ctrlPushed){
            player.setDirection(0);
          } else {
            if(player.getRowOnScreen() > 4){
              player.setDestRow(player.getRow() - 1 );
            } else {
              if(player.isMoving() == false && GameObjects.getCharacterReference(player.getRow() - 1, player.getColumn()) == null){
                player.getMapReference().setNewInitRow(MapSuper.getInitRow() - 1);
                player.setTimerMoveMap();
              }
            }
          }
         keyDown[0] = true;
         break;
        case KeyEvent.VK_DOWN:
          if(ctrlPushed){
            player.setDirection(3);
          } else {
            if(player.getRowOnScreen() < 14){
              player.setDestRow(player.getRow() + 1 );
            } else {
              if(player.isMoving() == false && GameObjects.getCharacterReference(player.getRow() + 1, player.getColumn()) == null){
                player.getMapReference().setNewInitRow(MapSuper.getInitRow() + 1);
                player.setTimerMoveMap();
              }
            }
          }
          keyDown[3] = true;
          break;
        case KeyEvent.VK_LEFT:
          if(ctrlPushed){
            player.setDirection(1);;
          } else {
            if(player.getColumnOnScreen() > 5){
              player.setDestColumn(player.getColumn() - 1);
            } else {
              if(player.isMoving() == false && GameObjects.getCharacterReference(player.getRow(), player.getColumn() - 1) == null){
                player.getMapReference().setNewInitColumn(MapSuper.getInitColumn() - 1);
                player.setTimerMoveMap();
              }
            }
          }
         keyDown[1] = true;
         break;
        case KeyEvent.VK_RIGHT:
          if(ctrlPushed){
            player.setDirection(2);
          } else {
            if(player.getColumnOnScreen() < 34){
              player.setDestColumn(player.getColumn() + 1 );
            } else {
              if(player.isMoving() == false && GameObjects.getCharacterReference(player.getRow(), player.getColumn() + 1) == null){
                player.getMapReference().setNewInitColumn(MapSuper.getInitColumn() + 1);
                player.setTimerMoveMap();
              }
            }
          }
          keyDown[2] = true;
          break;
        case KeyEvent.VK_CONTROL :
          ctrlPushed = true;
          break;
        case KeyEvent.VK_Q:
         if(player.isMoving() == false){
           if(player.isSpellCooldown() == false && player.getManaPoints() >= 40){
             new SpellsMethods("add",new SpellEnergyBeam(player.getRow(), player.getColumn(), player.getDirection(),false)).start();
             //player.setManaPoints(player.getManaPoints() - 40 );
             player.setUsedMana(player.getUsedMana() + 40);
             player.setSpellCooldown(2);
           }
         }
         break;
        case KeyEvent.VK_W:
          if(player.isSpellCooldown() == false && player.getManaPoints() >= 60){
            new SpellsMethods("add",new SpellFlameHur(player.getRow(), player.getColumn(), player.getDirection(),false)).start();
           // player.setManaPoints(player.getManaPoints() - 60 );
            player.setUsedMana(player.getUsedMana() + 60);
            player.setSpellCooldown(2);
          }
          break;
        case KeyEvent.VK_E:
          if(player.isSpellCooldown() == false && player.getManaPoints() >= 80){
            new SpellsMethods("add",new SpellStorm(player.getRow(), player.getColumn(), player.getDirection(),false)).start();
           //player.setManaPoints(player.getManaPoints() - 80);
            player.setUsedMana(player.getUsedMana() + 80);
            player.setSpellCooldown(2);
          }
          break;
        default:
          break;
     }
    }
  }

  @Override
  public void keyReleased(KeyEvent e){
    switch (e.getKeyCode()){
      case KeyEvent.VK_UP:
        keyDown[0] = false;
        break;
      case KeyEvent.VK_DOWN:
        keyDown[3] = false;
        break;
      case KeyEvent.VK_LEFT:
        keyDown[1] = false;
        break;
      case KeyEvent.VK_RIGHT:
        keyDown[2] = false;
        break;
      case KeyEvent.VK_CONTROL:
        ctrlPushed = false;
      default:
        break;
    }

    pressed.remove(e.getKeyCode());
  }
}
