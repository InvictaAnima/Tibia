package tibia;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

  public ImageLoader(){

  }

  public static BufferedImage loadImage(String path){
    try{
      return ImageIO.read(ImageLoader.class.getResource(path));
    } catch (IOException e){
      System.out.println("Error while loading image from :" + path + ".");
      return null;
    }
  }

}
