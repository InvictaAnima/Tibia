package tibia;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
//import java.util.Arrays;

public class Map extends MapSuper{
  private Timer timerMove;
  private boolean isMoving;
  private char [][]tab;
  private BufferedImage grass;  //g
  private BufferedImage ground; //z

  public Map(String path){
    readTabFromFile(path);
    loadImages();

    isMoving = false;
  }

  private void readTabFromFile(String path){
    try{
      BufferedReader br = new BufferedReader(new FileReader(path));

      rows = Integer.parseInt(br.readLine());
      columns = Integer.parseInt(br.readLine());
      tab = new char[rows][columns];

      String row;
      for(int i=0; i < rows; i++){
        row = br.readLine();
        for(int j=0; j < columns; j++){
          tab[i][j] = row.charAt(j);
        }
      }
      //System.out.println(Arrays.deepToString(tab));
      br.close();
    } catch (IOException e){
      System.out.print("Exception while reading Map .txt from a file: " + path + ".");
    }
  }

  private void loadImages(){
    grass = ImageLoader.loadImage("/img/grass.png");
    ground = ImageLoader.loadImage("/img/ground.png");

  }

  public void draw(Graphics g){
    for(int i=0; i < 22; i++)
      for(int j=0; j < 42; j++){
        switch(tab[initRow + i][initColumn + j]){
          case 'g' :
            g.drawImage(grass, (j * 32 - tmpX), (i * 32 - tmpY), null);
            break;
          case 'z' :
            g.drawImage(ground, (j * 32 - tmpX), (i * 32 - tmpY), null);
            break;
          default :
            break;
        }
      }

  }

  protected void setTimerMove(){
    timerMove = new Timer();
    timerMove.schedule(new TimerTask() {
      @Override
      public void run() {
        move();
      }
    }, 0, 10);//60

  }

  protected void move(){

    if(newInitRow < initRow){
      tmpY--;
    } else if(newInitRow > initRow){
      tmpY++;
    }

    if(newInitColumn < initColumn){
      tmpX--;
    } else if(newInitColumn > initColumn){
      tmpX++;
    }

    if(((newInitRow * 32) == (initRow * 32 + tmpY)) && ((newInitColumn * 32) == (initColumn * 32  + tmpX))){
      initRow = newInitRow;
      initColumn = newInitColumn;
      tmpX = tmpY = 0;
      timerMove.cancel();
      isMoving = false;
    }
  }

  public void setNewInitRow(int newInitRow) {
    if(isMoving == false && newInitRow >= 0 && newInitRow <= (rows - 22)){
      Map.newInitRow = newInitRow;
      setTimerMove();
      isMoving = true;
    }
  }

  public void setNewInitColumn(int newInitColumn) {
    if(isMoving == false && newInitColumn >= 0 && newInitColumn <= (columns - 42)){
    	Map.newInitColumn = newInitColumn;
      setTimerMove();
      isMoving = true;
    }
  }

}
