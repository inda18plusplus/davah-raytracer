package raytracer.shape;

import java.awt.Color;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import raytracer.geometry.Ray;
import raytracer.geometry.Vector3D;

public class Plane extends Shape {

  private Vector3D origin;
  private Vector3D normal;

  /**
   * Creates a sphere by position, radius, and color.
   */
  public Plane(Vector3D origin, Vector3D normal, Color color) {
    super(color);
    this.origin = origin;
    this.normal = normal;
  }

  /**
   * Calculates the intersections of the plane and a ray.
   */
  public List<Vector3D> getCollisions(Ray ray) {
    AbstractList<Vector3D> collisions = new ArrayList<>();
    double t = normal.dot(origin.subtract(ray.getOrigin())) / normal.dot(ray.getDirection());
    if (t >= 0) {
      collisions.add(ray.getPoint(t));
    }
    return collisions;
  }
}