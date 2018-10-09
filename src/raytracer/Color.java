package raytracer;

import raytracer.geometry.Vector3D;

public class Color {

  private double red;
  private double green;
  private double blue;

  /**
   * Constructor for the color class.
   */
  public Color(double red, double green, double blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Constructor from 3D vector.
   */
  public Color(Vector3D vector3D) {
    Vector3D normalised = vector3D.normalize();
    this.red = 0.5 * (normalised.getX() + 1);
    this.green = 0.5 * (normalised.getY() + 1);
    this.blue = 0.5 * (normalised.getZ() + 1);
  }

  public Color scale(double factor) {
    return new Color(red * factor, green * factor, blue * factor);
  }

  public Color add(Color other) {
    return new Color(red + other.red, green + other.green, blue + other.blue);
  }

  public Color multiply(Color other) {
    return new Color(red * other.red, green * other.green, blue * other.blue);
  }

  /**
   * Returns a new color where each component is guaranteed to be in [0, 1].
   */
  public Color clip() {
    double newRed = Math.max(0, Math.min(1, red));
    double newGreen = Math.max(0, Math.min(1, green));
    double newBlue = Math.max(0, Math.min(1, blue));
    return new Color(newRed, newGreen, newBlue);
  }

  /**
   * Converts this to a java.awt.Color.
   */
  public java.awt.Color getColor() {
    int r = (int) (255 * red);
    int g = (int) (255 * green);
    int b = (int) (255 * blue);
    return new java.awt.Color(r, g, b);
  }
}
