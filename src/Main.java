import raytracer.Color;
import raytracer.Scene;
import raytracer.geometry.Vector3D;
import raytracer.light.Light;
import raytracer.shape.Plane;
import raytracer.shape.Sphere;
import raytracer.shape.texture.NormalToColor;
import raytracer.shape.texture.Pigment;
import raytracer.shape.texture.Texture;

public class Main {

  /**
   * Creates a scene and renders it.
   */
  public static void main(String[] args) {
    drawInCircle(box(), 40, 4.9);
  }

  private static Scene box() {
    Scene scene = new Scene();
    scene.addLight(new Light(new Vector3D(4, 2, -3.4), new Color(1, 1, 1), 60));
    scene.addLight(new Light(new Vector3D(4, 2, 2.9), new Color(1, 1, 1), 20));
    Texture silverGlossy = new Pigment(
            0.1,
            0.5,
            0.4,
            0.0,
            0.5,
            14,
            new Color(0.7, 0.7, 0.7)
    );
    Texture normalColoring = new NormalToColor(
            0.1,
            0.5,
            0.4,
            0.3,
            0.5,
            6
    );
    scene.add(new Sphere(new Vector3D(0, 0, 0), 1.5, silverGlossy));
    scene.add(new Plane(new Vector3D(5, 0, 0), new Vector3D(-1, 0, 0), normalColoring));
    scene.add(new Plane(new Vector3D(-5, 0, 0), new Vector3D(1, 0, 0), normalColoring));
    scene.add(new Plane(new Vector3D(0, 5, 0), new Vector3D(0, -1, 0), normalColoring));
    scene.add(new Plane(new Vector3D(0, -5, 0), new Vector3D(0, 1, 0), normalColoring));
    scene.add(new Plane(new Vector3D(0, 0, 5), new Vector3D(0, 0, -1), normalColoring));
    scene.add(new Plane(new Vector3D(0, 0, -5), new Vector3D(0, 0, 1), normalColoring));
    return scene;
  }

  private static void drawInCircle(Scene scene, int n, double r) {
    long startTime = System.currentTimeMillis();

    for (int i = 0; i < n; i++) {
      long startTimeI = System.currentTimeMillis();
      double alpha = 2 * i * Math.PI / n;
      double x = r * Math.cos(alpha); // Starts at r
      double z = r * Math.sin(alpha); // Starts at 0
      scene.draw(new Vector3D(x, 0, z), new Vector3D(0, 0, 0), 80, 60,
              200, 200, "test/output" + (i + 1) + ".png");
      long elapsedTimeI = System.currentTimeMillis() - startTimeI;
      System.out.println("Finished picture #" + (i + 1) + " in " + elapsedTimeI + "ms");
    }

    long elapsedTime = System.currentTimeMillis() - startTime;
    System.out.println("Finished in " + elapsedTime + "ms");
  }
}
