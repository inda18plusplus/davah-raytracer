import raytracer.Color;
import raytracer.Scene;
import raytracer.geometry.Vector3D;
import raytracer.light.Light;
import raytracer.shape.Plane;
import raytracer.shape.Sphere;
import raytracer.shape.Triangle;
import raytracer.shape.material.Material;
import raytracer.shape.texture.Checkered;
import raytracer.shape.texture.NormalToColor;
import raytracer.shape.texture.Pigment;
import raytracer.shape.texture.Texture;

public class Main {

  private static Material onlyAmbient = new Material(
          0.7,
          0.0,
          0.0,
          0.0,
          0.0,
          1.0
  );

  private static Material ambientReflective = new Material(
          0.5,
          0.0,
          0.5,
          0.0,
          0.0,
          1.0
  );

  private static Material matteNonReflective = new Material(
          0.3,
          0.7,
          0.0,
          0.0,
          0.5,
          14
  );

  private static Material matteReflective = new Material(
          0.1,
          0.5,
          0.4,
          0.3,
          0.5,
          6
  );

  private static Material glossy = new Material(
          0.1,
          0.5,
          0.4,
          0.01,
          0.5,
          14
  );

  /**
   * Creates a scene and renders it.
   */
  public static void main(String[] args) {
    drawOnce(checkeredFloor());
  }

  private static Scene checkeredFloor() {
    Scene scene = new Scene();
    scene.addLight(new Light(new Vector3D(-6, 10, -9), new Color(1, 1, 1), 1000));
    Texture whiteRedSquares = new Checkered(
            new Color(1, 1, 1),
            new Color(1, 0, 0)
    );
    scene.add(new Plane(matteNonReflective, whiteRedSquares,
            new Vector3D(0, 0, 0),
            new Vector3D(0, 1, 0))
    );
    Texture silver = new Pigment(new Color(0.7, 0.7, 0.7));
    scene.add(new Sphere(glossy, silver,
            new Vector3D(0, 3, 0), 3));
    return scene;
  }

  private static Scene pyramid() {
    Scene scene = new Scene();
    scene.addLight(new Light(new Vector3D(0, 4, 0), new Color(1, 1, 1), 70));
    scene.addLight(new Light(new Vector3D(-3, 2, 2), new Color(1, 1, 1), 20));
    Texture pink = new Pigment(new Color(0.8, 0.3, 0.7));
    scene.add(new Triangle(matteNonReflective, pink,
            new Vector3D(-2, 0, 2),
            new Vector3D(2, 0, 2),
            new Vector3D(0, 2, 0))
    );
    scene.add(new Triangle(matteNonReflective, pink,
            new Vector3D(-2, 0, -2),
            new Vector3D(-2, 0, 2),
            new Vector3D(0, 2, 0))
    );
    scene.add(new Triangle(matteNonReflective, pink,
            new Vector3D(2, 0, -2),
            new Vector3D(-2, 0, -2),
            new Vector3D(0, 2, 0))
    );
    scene.add(new Triangle(matteNonReflective, pink,
            new Vector3D(2, 0, 2),
            new Vector3D(2, 0, -2),
            new Vector3D(0, 2, 0))
    );
    return scene;
  }

  private static Scene box() {
    Scene scene = new Scene();
    scene.addLight(new Light(new Vector3D(4, 2, -3.4), new Color(1, 1, 1), 60));
    scene.addLight(new Light(new Vector3D(4, 2, 2.9), new Color(1, 1, 1), 20));
    Texture silver = new Pigment(new Color(0.7, 0.7, 0.7));
    Texture normalColoring = new NormalToColor();
    scene.add(new Sphere(glossy, silver,
            new Vector3D(0, 0, 0), 1.5));
    scene.add(new Plane(matteReflective, normalColoring,
            new Vector3D(5, 0, 0), new Vector3D(-1, 0, 0)));
    scene.add(new Plane(matteReflective, normalColoring,
            new Vector3D(-5, 0, 0), new Vector3D(1, 0, 0)));
    scene.add(new Plane(matteReflective, normalColoring,
            new Vector3D(0, 5, 0), new Vector3D(0, -1, 0)));
    scene.add(new Plane(matteReflective, normalColoring,
            new Vector3D(0, -5, 0), new Vector3D(0, 1, 0)));
    scene.add(new Plane(matteReflective, normalColoring,
            new Vector3D(0, 0, 5), new Vector3D(0, 0, -1)));
    scene.add(new Plane(matteReflective, normalColoring,
            new Vector3D(0, 0, -5), new Vector3D(0, 0, 1)));
    return scene;
  }

  private static void drawOnce(Scene scene) {
    long startTime = System.currentTimeMillis();
    scene.draw(new Vector3D(-6, 4, 0), new Vector3D(0, 3, 0), 80, 20,
            600, 600, "test/output.png");
    long elapsedTime = System.currentTimeMillis() - startTime;
    System.out.println("Finished in " + elapsedTime + "ms");
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
