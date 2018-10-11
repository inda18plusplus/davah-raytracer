package raytracer.shape;

import java.util.ArrayList;
import java.util.List;

import raytracer.geometry.Ray;
import raytracer.geometry.Vector3D;
import raytracer.shape.texture.Texture;

public class Plane extends Shape {

  private Vector3D origin;
  private Vector3D normal;

  /**
   * Creates a plane by a point on the plane and a normal.
   */
  public Plane(Vector3D origin, Vector3D normal, Texture texture) {
    super(texture);
    this.origin = origin;
    this.normal = normal.normalize();
  }

  /**
   * Calculates the intersections of the plane and a ray.
   */
  public List<Vector3D> getCollisions(Ray ray) {
    List<Vector3D> collisions = new ArrayList<>();
    double denominator = normal.dot(ray.getDirection());
    if (Math.abs(denominator) < 0.0001) {
      return collisions;
    }
    double t = normal.dot(origin.subtract(ray.getOrigin())) / denominator;
    if (t >= 0) {
      collisions.add(ray.getPoint(t));
    }
    return collisions;
  }

  public Ray getNormal(Vector3D position) {
    return new Ray(position, normal);
  }
}
