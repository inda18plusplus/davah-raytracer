package raytracer.shape;

import java.util.ArrayList;
import java.util.List;

import raytracer.geometry.Ray;
import raytracer.geometry.Vector3D;
import raytracer.shape.texture.Texture;

public class Triangle extends Shape {

  private Vector3D vertex1;
  private Vector3D vertex2;
  private Vector3D vertex3;

  private Vector3D edge1;
  private Vector3D edge2;
  private Vector3D edge3;

  private Vector3D normal;

  /**
   * Creates a triangle by its three vertices.
   */
  public Triangle(Texture texture, Vector3D vertex1, Vector3D vertex2, Vector3D vertex3) {
    super(texture);
    this.vertex1 = vertex1;
    this.vertex2 = vertex2;
    this.vertex3 = vertex3;
    this.edge1 = vertex2.subtract(vertex1);
    this.edge2 = vertex3.subtract(vertex2);
    this.edge3 = vertex1.subtract(vertex3);
    this.normal = edge1.cross(edge2).normalize();
  }

  /**
   * Calculates the intersections of the triangle and a ray.
   */
  public List<Vector3D> getCollisions(Ray ray) {
    List<Vector3D> collisions = new ArrayList<>();
    double denominator = normal.dot(ray.getDirection());
    if (Math.abs(denominator) < 0.0001) {
      return collisions;
    }
    double t = normal.dot(vertex1.subtract(ray.getOrigin())) / denominator;
    if (t < 0) {
      return collisions;
    }
    if (this.contains(ray.getPoint(t))) {
      collisions.add(ray.getPoint(t));
    }
    return collisions;
  }

  public Ray getNormal(Vector3D position) {
    return new Ray(position, normal);
  }

  private boolean contains(Vector3D p) {
    Vector3D from1 = p.subtract(vertex1);
    Vector3D from2 = p.subtract(vertex2);
    Vector3D from3 = p.subtract(vertex3);
    if (normal.dot(edge1.cross(from1)) < 0) {
      return false;
    }
    if (normal.dot(edge2.cross(from2)) < 0) {
      return false;
    }
    return normal.dot(edge3.cross(from3)) >= 0;
  }
}