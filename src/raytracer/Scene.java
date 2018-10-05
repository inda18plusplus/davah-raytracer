package raytracer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import raytracer.shape.Shape;

public class Scene {

  private List<Shape> shapes;

  public Scene() {
    shapes = new ArrayList<>();
  }

  public void add(Shape shape) {
    shapes.add(shape);
  }

  /**
   * Draws the scene's objects from the specified perspective to the specified file.
   *
   * @param position The camera position.
   * @param facing Any of the infinitely many positions the camera is facing.
   * @param angleOfView The horizontal angle of view of the picture, in degrees.
   * @param width The width of the picture in pixels.
   * @param height The height of the picture in pixels.
   * @param filename The filename to output to.
   */
  public void draw(Position position, Position facing, double angleOfView,
                   int width, int height, String filename) {
    Picture picture = new Picture(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        picture.setPixel(x, y, Color.BLACK);
      }
    }
    picture.write(filename);
  }
}
