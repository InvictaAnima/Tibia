package tibia;
/**
 * Created by Gargamel on 2016-07-09.
 */
public class MapSuper {
  protected static int rows;
  protected static int columns;
  protected static int initRow;    // first drawn map fragment
  protected static int initColumn; // first drawn map fragment
  protected static int newInitRow;
  protected static int newInitColumn;
  protected static int tmpX,tmpY;


  public MapSuper(){
    initRow = 0;
    initColumn = 0;
    newInitRow = 0;
    newInitColumn = 0;
  }


  public static int getTmpX() {
    return tmpX;
  }

  public static int getTmpY() {
    return tmpY;
  }

  public static int getInitRow() {
    return initRow;
  }

  public static int getInitColumn() {
    return initColumn;
  }

  public static int getNewInitRow() {
    return newInitRow;
  }

  public static int getNewInitColumn() {
    return newInitColumn;
  }
}
