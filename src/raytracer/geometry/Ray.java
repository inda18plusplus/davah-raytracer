package raytracer.geometry;

public class Ray {

  private Vector3D origin;
  private Vector3D direction;

  public Ray(Vector3D origin, Vector3D direction) {
    this.origin = origin;
    this.direction = direction.normalize();
  }

  public Vector3D getOrigin() {
    return origin;
  }

  public Vector3D getDirection() {
    return direction;
  }

  public Vector3D getPoint(double t) {
    return origin.add(direction.multiply(t));
  }

  public Ray nudgedForward() {
    return new Ray(getPoint(0.0001), direction);
  }

  /**
   * Bounces this ray in a point given a normal of the surface at that point.
   */
  public Ray bounce(Ray normalRay, double fuzziness) {
    Vector3D newOrigin = normalRay.origin;
    Vector3D normal = normalRay.getDirection();
    Vector3D scaledNormal = normal.multiply(2 * normal.dot(this.getDirection()));
    Vector3D newDirection = this.getDirection().subtract(scaledNormal);
    newDirection = newDirection.add(Vector3D.randomInUnitSphere().multiply(fuzziness));
    return new Ray(newOrigin, newDirection);
  }
}
