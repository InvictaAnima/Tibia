package spellsMain;
import java.awt.image.BufferedImage;

import tibia.ImageLoader;

public class SpellImages {
  private static BufferedImage energy;
  private static BufferedImage fire;
  private static BufferedImage storm;
  private static BufferedImage san;
  private static BufferedImage san2;
  private static BufferedImage energy2;
  private static BufferedImage sd;
  private static BufferedImage stars;
  private static BufferedImage ice;

  public SpellImages(){

    loadImages();
  }

  private void loadImages(){
    energy = ImageLoader.loadImage("/img/energy.png");
    fire = ImageLoader.loadImage("/img/fire.png");
    storm = ImageLoader.loadImage("/img/storm.png");
    san = ImageLoader.loadImage("/img/san.png");
    san2 = ImageLoader.loadImage("/img/san2.png");
    energy2 = ImageLoader.loadImage("/img/energy2.png");
    sd = ImageLoader.loadImage("/img/sd.png");
    stars = ImageLoader.loadImage("/img/stars.png");
    ice = ImageLoader.loadImage("/img/ice.png");

  }

  public static BufferedImage getImage(String name){
    switch(name){
      case "energy":
        return energy;
      case "storm":
        return storm;
      case "san":
        return san;
      case "san2":
        return san2;
      case "fire":
        return fire;
      case "energy2":
        return energy2;
      case "sd":
        return sd;
      case "stars":
        return stars;
      case "ice":
        return ice;
      default:
        return null;
    }
  }
}
