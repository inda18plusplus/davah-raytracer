package raytracer.light;

import raytracer.Color;
import raytracer.geometry.Vector3D;

public class Light {

  private Vector3D position;
  private Color color;
  private double intensity;

  /**
   * Constructor for the light class.
   */
  public Light(Vector3D position, Color color, double intensity) {
    this.position = position;
    this.color = color;
    this.intensity = intensity;
  }

  public Vector3D getPosition() {
    return position;
  }

  public Color getColor() {
    return color;
  }

  public double getIntensity() {
    return intensity;
  }
}
