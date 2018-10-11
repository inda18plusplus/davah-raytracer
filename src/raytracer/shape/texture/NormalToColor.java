package raytracer.shape.texture;

import raytracer.Color;
import raytracer.geometry.Vector3D;
import raytracer.shape.Shape;

public class NormalToColor extends Texture {

  public Color getColor(Vector3D position, Shape shape) {
    return new Color(shape.getNormal(position).getDirection());
  }
}