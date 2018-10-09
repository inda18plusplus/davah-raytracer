package raytracer.geometry;

/**
 * Implements a position / vector in 3D space.
 */
public class Vector3D {

  private double ex;
  private double wye;
  private double zed;

  /**
   * Creates a 3D vector.
   *
   * @param x The x coordinate.
   * @param y The y coordinate.
   * @param z The z coordinate.
   */
  public Vector3D(double x, double y, double z) {
    this.ex = x;
    this.wye = y;
    this.zed = z;
  }

  public double getX() {
    return ex;
  }

  public double getY() {
    return wye;
  }

  public double getZ() {
    return zed;
  }

  public Vector3D add(Vector3D otherVector) {
    return new Vector3D(ex + otherVector.ex, wye + otherVector.wye, zed + otherVector.zed);
  }

  public Vector3D subtract(Vector3D other) {
    return new Vector3D(ex - other.ex, wye - other.wye, zed - other.zed);
  }

  public Vector3D multiply(double factor) {
    return new Vector3D(ex * factor, wye * factor, zed * factor);
  }

  public double dot(Vector3D other) {
    return ex * other.ex + wye * other.wye + zed * other.zed;
  }

  public double length() {
    return Math.sqrt(ex * ex + wye * wye + zed * zed);
  }

  public double lengthSquared() {
    return ex * ex + wye * wye + zed * zed;
  }

  public Vector3D normalize() {
    return this.multiply(1 / this.length());
  }

  public Vector3D scale(double newLength) {
    return this.multiply(newLength / this.length());
  }

  /**
   * Checks whether this vector is closer to origin than otherVector.
   */
  public boolean closerThan(Vector3D otherVector, Vector3D origin) {
    double thisDistance = this.subtract(origin).length();
    double otherDistance = otherVector.subtract(origin).length();
    return thisDistance < otherDistance;
  }

  /**
   * Returns a random Vector3D with length <= 1.
   */
  public static Vector3D randomInUnitSphere() {
    Vector3D candidate;
    do {
      candidate = new Vector3D(Math.random(), Math.random(), Math.random());
      candidate = candidate.multiply(2).subtract(new Vector3D(1, 1, 1));
    } while (candidate.lengthSquared() > 1.0);
    return candidate;
  }
}
