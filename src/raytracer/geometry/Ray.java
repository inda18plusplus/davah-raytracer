package raytracer.geometry;

public class Ray {

  private Vector3D origin;
  private Vector3D direction;

  public Ray(Vector3D origin, Vector3D facing) {
    this.origin = origin;
    this.direction = facing.subtract(origin).normalize();
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
}
