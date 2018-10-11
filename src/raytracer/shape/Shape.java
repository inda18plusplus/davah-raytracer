package raytracer.shape;

import java.util.List;

import raytracer.geometry.Ray;
import raytracer.geometry.Vector3D;
import raytracer.shape.material.Material;
import raytracer.shape.texture.Texture;

public abstract class Shape {

  private Material material;
  private Texture texture;

  Shape(Material material, Texture texture) {
    this.material = material;
    this.texture = texture;
  }

  public abstract List<Vector3D> getCollisions(Ray ray);

  public abstract Ray getNormal(Vector3D position);

  public Material getMaterial() {
    return material;
  }

  public Texture getTexture() {
    return texture;
  }
}
