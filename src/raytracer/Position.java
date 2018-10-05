package raytracer;

public class Position {

  private double xx;
  private double yy;
  private double zz;

  /**
   * Creates a position in 3D space.
   *
   * @param xx The x position.
   * @param yy The y position.
   * @param zz The z position.
   */
  public Position(double xx, double yy, double zz) {
    this.xx = xx;
    this.yy = yy;
    this.zz = zz;
  }

  public double getX() {
    return xx;
  }

  public double getY() {
    return yy;
  }

  public double getZ() {
    return zz;
  }
}
