package raytracer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

class Picture {

  private BufferedImage image;

  Picture(int width, int height) {
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  void setPixel(int x, int y, Color color) {
    image.setRGB(x, y, color.getRGB());
  }

  void write(String filename) {
    try {
      String path = "img/" + filename;
      ImageIO.write(image, "bmp", new File(path));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
