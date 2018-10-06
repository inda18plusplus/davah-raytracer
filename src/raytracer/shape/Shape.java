package raytracer.shape;

import java.awt.Color;
import java.util.List;
import raytracer.geometry.Ray;
import raytracer.geometry.Vector3D;

public abstract class Shape {

  private Color color;

  public Shape(Color color) {
    this.color = color;
  }

  public abstract List<Vector3D> getCollisions(Ray ray);

  public Color getColor() {
    return color;
  }
}
