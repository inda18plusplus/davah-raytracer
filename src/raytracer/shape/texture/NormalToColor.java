package raytracer.shape.texture;

import raytracer.Color;
import raytracer.geometry.Vector3D;
import raytracer.shape.Shape;

public class NormalToColor extends Texture {

  public NormalToColor(double ambientRatio,
                       double diffuseRatio,
                       double reflectivity,
                       double fuzziness,
                       double specularRatio,
                       double specularExponent) {
    super(ambientRatio, diffuseRatio, reflectivity, fuzziness, specularRatio, specularExponent);
  }

  public Color getColor(Vector3D position, Shape shape) {
    return new Color(shape.getNormal(position).getDirection());
  }
}