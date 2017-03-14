package gameCharactersMain;
import java.awt.image.BufferedImage;

import tibia.ImageLoader;

public class CharacterImages {
  private static BufferedImage mage;
  private static BufferedImage dl; //dragon lord
  private static BufferedImage dlBody; //dragon lord
  private static BufferedImage dwarf;
  private static BufferedImage dwarfBody;
  private static BufferedImage dwarfGuard;
  private static BufferedImage dwarfGuardBody;
  private static BufferedImage dwarfGeomancer;
  private static BufferedImage dwarfGeomancerBody;
  private static BufferedImage gs; //giant spider
  private static BufferedImage gsBody; //giant spider

  public CharacterImages(){

    loadImages();
  }

  private void loadImages(){
    mage = ImageLoader.loadImage("/img/mage.png");
    dl = ImageLoader.loadImage("/img/dl.png");
    dlBody = ImageLoader.loadImage("/img/dlBody.png");
    dwarf = ImageLoader.loadImage("/img/dwarf.png");
    dwarfBody = ImageLoader.loadImage("/img/dwarfBody.png");
    dwarfGuard = ImageLoader.loadImage("/img/dwarfGuard.png");
    dwarfGuardBody = ImageLoader.loadImage("/img/dwarfGuardBody.png");
    dwarfGeomancer = ImageLoader.loadImage("/img/dwarfGeomancer.png");
    dwarfGeomancerBody = ImageLoader.loadImage("/img/dwarfGeomancerBody.png");
    gs = ImageLoader.loadImage("/img/gs.png");
    gsBody = ImageLoader.loadImage("/img/gsBody.png");

  }

  public static BufferedImage getImage(String name){
    switch(name){
      case "mage":
        return mage;
      case "dl":
        return dl;
      case "dlBody":
        return dlBody;
      case "dwarf":
        return dwarf;
      case "dwarfBody":
        return dwarfBody;
      case "dwarfGuard":
        return dwarfGuard;
      case "dwarfGuardBody":
        return dwarfGuardBody;
      case "dwarfGeomancer":
        return dwarfGeomancer;
      case "dwarfGeomancerBody":
        return dwarfGeomancerBody;
      case "gs":
        return gs;
      case "gsBody":
        return gsBody;
      default:
        return null;
    }
  }
}
