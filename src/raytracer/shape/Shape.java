package raytracer.shape;

import java.util.List;

import raytracer.geometry.Ray;
import raytracer.geometry.Vector3D;
import raytracer.shape.texture.Texture;

public abstract class Shape {

  private Texture texture;

  Shape(Texture texture) {
    this.texture = texture;
  }

  public abstract List<Vector3D> getCollisions(Ray ray);

  public abstract Ray getNormal(Vector3D position);

  public Texture getTexture() {
    return texture;
  }
}
