package raytracer;

import raytracer.geometry.Ray;
import raytracer.geometry.Vector3D;

class PicturePlane {

  private Vector3D origin;
  private Vector3D horizontal;
  private Vector3D vertical;
  private double width;
  private double height;

  PicturePlane(Vector3D cameraPosition, Vector3D facing, double angleOfView,
               double imageWidth, double imageHeight) {
    this.origin = facing;

    Vector3D normal = facing.subtract(cameraPosition);
    double horizontalLength = normal.length() * Math.tan(angleOfView / 2);
    double verticalLength = horizontalLength * imageWidth / imageHeight;
    this.horizontal = getHorizontal(normal).scale(horizontalLength);
    this.vertical = getVertical(normal).scale(verticalLength);

    this.width = imageWidth;
    this.height = imageHeight;
  }

  private Vector3D getHorizontal(Vector3D normal) {
    return new Vector3D(-normal.getZ(), 0, normal.getX());
  }

  private Vector3D getVertical(Vector3D normal) {
    Vector3D horizontalProjection = new Vector3D(normal.getX(), 0, normal.getZ());
    double horizontalStretch = normal.getY() / horizontalProjection.length();
    return new Vector3D(normal.getX() * horizontalStretch,
            horizontalProjection.length(),
            normal.getZ() * horizontalStretch);
  }

  public Ray getRay(double x, double y) {
    double horizontalFactor = 2 * x / width - 1;
    double verticalFactor = 2 * y / height - 1;
    Vector3D stretchedHorizontal = horizontal.multiply(horizontalFactor);
    Vector3D stretchedVertical = vertical.multiply(verticalFactor);
    Vector3D planePosition = origin.add(stretchedHorizontal).add(stretchedVertical);
    return new Ray(origin, planePosition);
  }
}
