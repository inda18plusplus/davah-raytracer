package raytracer.geometry;

/**
 * Implements a position / vector in 3D space.
 */
public class Vector3D {

  private double x;
  private double y;
  private double z;

  /**
   * Creates a 3D vector.
   *
   * @param x The x coordinate.
   * @param y The y coordinate.
   * @param z The z coordinate.
   */
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getZ() {
    return z;
  }

  public Vector3D add(Vector3D otherVector) {
    return new Vector3D(x + otherVector.x, y + otherVector.y, z + otherVector.z);
  }

  public Vector3D subtract(Vector3D otherVector) {
    return new Vector3D(x - otherVector.x, y - otherVector.y, z - otherVector.z);
  }

  public Vector3D multiply(double factor) {
    return new Vector3D(x * factor, y * factor, z * factor);
  }

  public double length() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  public Vector3D normalize() {
    return this.multiply(1 / this.length());
  }

  public Vector3D scale(double newLength) {
    return this.multiply(newLength / this.length());
  }

  /**
   * Checks whether this vector is closer to origin than otherVector.
   *
   * @param otherVector The vector to compare against.
   * @param origin The vector to calculate distance from.
   * @return True if this vector is closer.
   */
  public boolean closerThan(Vector3D otherVector, Vector3D origin) {
    double thisDistance = this.subtract(origin).length();
    double otherDistance = otherVector.subtract(origin).length();
    return thisDistance < otherDistance;
  }
}
