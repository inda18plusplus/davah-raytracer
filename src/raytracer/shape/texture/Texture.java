package raytracer.shape.texture;

import raytracer.Color;
import raytracer.geometry.Vector3D;
import raytracer.shape.Shape;

public abstract class Texture {

  private double ambientRatio;
  private double diffuseRatio;
  private double reflectivity;
  private double fuzziness;
  private double specularRatio;
  private double specularExponent;

  Texture(double ambientRatio,
          double diffuseRatio,
          double reflectivity,
          double fuzziness,
          double specularRatio,
          double specularExponent) {
    this.ambientRatio = ambientRatio;
    this.diffuseRatio = diffuseRatio;
    this.reflectivity = reflectivity;
    this.fuzziness = fuzziness;
    this.specularRatio = specularRatio;
    this.specularExponent = specularExponent;
  }

  public abstract Color getColor(Vector3D position, Shape shape);

  public double getAmbientRatio() {
    return ambientRatio;
  }

  public double getDiffuseRatio() {
    return diffuseRatio;
  }

  public double getReflectivity() {
    return reflectivity;
  }

  public double getFuzziness() {
    return fuzziness;
  }

  public double getSpecularRatio() {
    return specularRatio;
  }

  public double getSpecularExponent() {
    return specularExponent;
  }
}
