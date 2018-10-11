package raytracer.shape.texture;

import raytracer.Color;
import raytracer.geometry.Vector3D;
import raytracer.shape.Shape;

public class Checkered extends Texture {

  private Color color1;
  private Color color2;

  public Checkered(Color color1, Color color2) {
    this.color1 = color1;
    this.color2 = color2;
  }

  public Color getColor(Vector3D position, Shape shape) {
    int coordinateSum = (int)(Math.floor(position.getX()))
            + (int)(Math.floor(position.getZ()));
    return coordinateSum % 2 == 0 ? color1 : color2;
  }
}
