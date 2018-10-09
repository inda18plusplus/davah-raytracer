package raytracer.shape.texture;

import raytracer.Color;
import raytracer.geometry.Vector3D;
import raytracer.shape.Shape;

public class Pigment extends Texture {

  private Color color;

  public Pigment(double ambientRatio,
                 double diffuseRatio,
                 double reflectivity,
                 double fuzziness,
                 double specularRatio,
                 double specularExponent,
                 Color color) {
    super(ambientRatio, diffuseRatio, reflectivity, fuzziness, specularRatio, specularExponent);
    this.color = color;
  }

  public Color getColor(Vector3D vector3D, Shape shape) {
    return color;
  }
}
