import java.awt.Color;
import raytracer.Scene;
import raytracer.geometry.Vector3D;
import raytracer.shape.Sphere;

public class Main {

  /**
   * Creates a scene and renders it.
   */
  public static void main(String[] args) {
    Scene scene1 = new Scene();
    scene1.add(new Sphere(new Vector3D(0, 0, 0), 1, Color.WHITE));
    scene1.add(new Sphere(new Vector3D(2, 0, 0), 1, Color.RED));
    scene1.add(new Sphere(new Vector3D(0, 2, 0), 1, Color.GREEN));
    scene1.add(new Sphere(new Vector3D(0, 0, 2), 1, Color.BLUE));
    scene1.draw(new Vector3D(5, 5, 5), new Vector3D(0, 0, 0), 70,
            600, 400, "image1.png");
  }
}
