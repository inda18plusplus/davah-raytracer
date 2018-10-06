import raytracer.Scene;
import raytracer.geometry.Vector3D;

public class Main {

  /**
   * Creates a scene and renders it.
   */
  public static void main(String[] args) {
    Scene scene = new Scene();
    scene.draw(new Vector3D(2, 0, 0), new Vector3D(0, 0, 0), 140,
            600, 300, "image.png");
  }
}
