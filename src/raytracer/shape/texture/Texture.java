package raytracer.shape.texture;

import raytracer.Color;
import raytracer.geometry.Vector3D;
import raytracer.shape.Shape;

public abstract class Texture {

  public abstract Color getColor(Vector3D position, Shape shape);
}
