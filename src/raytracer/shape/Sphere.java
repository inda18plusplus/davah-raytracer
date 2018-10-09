package raytracer.shape;

import java.util.ArrayList;
import java.util.List;

import raytracer.geometry.Ray;
import raytracer.geometry.Vector3D;
import raytracer.shape.texture.Texture;

public class Sphere extends Shape {

  private Vector3D center;
  private double radius;

  /**
   * Creates a sphere by position, radius, and color.
   */
  public Sphere(Vector3D center, double radius, Texture texture) {
    super(texture);
    this.center = center;
    this.radius = radius;
  }

  /**
   * Calculates the intersections of the sphere and a ray. Assumes ray.getDirection().length() == 1.
   */
  public List<Vector3D> getCollisions(Ray ray) {
    Vector3D translatedOrigin = ray.getOrigin().subtract(center);
    double thing = ray.getDirection().dot(translatedOrigin); //TODO: Does this mean anything?
    double discriminant = radius * radius + thing * thing - translatedOrigin.lengthSquared();
    List<Vector3D> collisions = new ArrayList<>();
    if (discriminant < 0) {
      return collisions;
    }
    double t1 = - thing - Math.sqrt(discriminant);
    if (t1 >= 0) {
      collisions.add(ray.getPoint(t1));
    }
    double t2 = - thing + Math.sqrt(discriminant);
    if (t2 >= 0) {
      collisions.add(ray.getPoint(t2));
    }
    return collisions;
  }

  public Ray getNormal(Vector3D position) {
    return new Ray(position, position.subtract(center));
  }
}
