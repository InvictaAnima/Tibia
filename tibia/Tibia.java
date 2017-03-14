package tibia;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Gargamel on 2016-07-03.
 */
import javax.swing.JFrame;

import gameCharactersMain.CharacterImages;
import gameCharactersMain.GameObjects;
import gameCharactersMain.Player;
import hud.Hud;
import itemsMain.ItemsImages;
import itemsMain.ItemsMap;
import players.Mage;
import specialEffects.SpecialEffects;
import spellsMain.CastedSpells;
import spellsMain.SpellImages;

public class Tibia extends Canvas {
  
	private static final long serialVersionUID = 1L;
	private static final int WIDTH=1280, HEIGHT=640; // 40x20
  private JFrame window;
  private static BufferStrategy bs;
  private static Graphics g;

  private Timer timerDraw;
  private Timer timerUpdate;

  private static Map map;
  private static GameObjects gameObjects;
  private static Hud hud;
  private Player player;
  
  private ItemsMap itemsMap;

  public Tibia(){
    new ImageLoader();
    new CharacterImages();
    new SpellImages();
    new ItemsImages();
    new CastedSpells();
    new SpecialEffects();
    setWindow(this);

    map = new Map("map.txt");

    player = new Mage(map,2,2);

    gameObjects = new GameObjects(player);    

    hud = new Hud(player);
    
    itemsMap = new ItemsMap();

    this.addKeyListener(new KeyInput(player));
    this.addMouseListener(new MouseInput(hud, itemsMap));
    setTimers();    
  }

  private void setTimers(){
    timerDraw = new Timer();
    timerDraw.schedule(new TimerTask(){
      @Override
      public void run(){
        draw();
      }
    }, 0, 60);

    timerUpdate = new Timer();
    timerUpdate.schedule(new TimerTask() {
      @Override
      public void run() {
      	//if(player.getHealthPoints()>0){
      		update();
      	//}
      }
    }, 0, 60);
  }
  private void setWindow(Tibia tibia){
    this.window = new JFrame("Tibia");
    this.window.setPreferredSize(new Dimension(WIDTH,HEIGHT));
    this.window.setMaximumSize(new Dimension(WIDTH, HEIGHT));
    this.window.setMinimumSize(new Dimension(WIDTH,HEIGHT));
    this.window.setResizable(false);
    this.window.setLocationRelativeTo(null);
    this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.window.add(tibia);
    this.window.setVisible(true);

    this.createBufferStrategy(2);
    bs = this.getBufferStrategy();
    g = bs.getDrawGraphics();
    g.translate(-32,-32); //do it later
    g.setFont(new Font("TimesRoman", Font.BOLD, 12)); //tmp

    requestFocus();
  }

  public static void main(String[] args) {  	
    new Tibia();    
  }

  private void draw(){
    map.draw(g);
    itemsMap.draw(g);
    player.draw(g);
    gameObjects.draw(g);
    CastedSpells.drawSpells(g);
    gameObjects.drawNames(g);
    gameObjects.drawHpBars(g);
    SpecialEffects.drawEffects(g);
    hud.draw(g);   
    bs.show();
  }

  private void update(){
    gameObjects.update();
    hud.update();
  }
}
