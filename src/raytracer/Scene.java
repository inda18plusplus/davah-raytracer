package raytracer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import raytracer.geometry.Ray;
import raytracer.geometry.Vector3D;
import raytracer.shape.Shape;

public class Scene {

  private List<Shape> shapes;
  private Color skyColor;

  public Scene() {
    shapes = new ArrayList<>();
    skyColor = Color.BLACK;
  }

  public void add(Shape shape) {
    shapes.add(shape);
  }

  /**
   * Draws the scene's objects from the specified perspective to the specified file.
   *
   * @param cameraPosition The camera position.
   * @param facing Any of the infinitely many positions the camera is facing.
   * @param angleOfView The horizontal angle of view of the picture, in degrees.
   * @param width The width of the picture in pixels.
   * @param height The height of the picture in pixels.
   * @param filename The filename to output to.
   */
  public void draw(Vector3D cameraPosition, Vector3D facing, double angleOfView,
                   int width, int height, String filename) {
    PicturePlane picturePlane = new PicturePlane(
            cameraPosition, facing, angleOfView, width, height);
    Picture picture = new Picture(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        picture.setPixel(x, y, getColor(picturePlane.getRay(x, y)));
      }
    }
    picture.write(filename);
  }

  private Color getColor(Ray ray) {
    Vector3D closestCollision = null;
    Shape collisionObject = null;
    for (Shape shape : shapes) {
      for (Vector3D collision : shape.getCollisions(ray)) {
        if (closestCollision == null) {
          closestCollision = collision;
          collisionObject = shape;
        } else if (collision.closerThan(closestCollision, ray.getOrigin())) {
          closestCollision = collision;
          collisionObject = shape;
        }
      }
    }

    //TODO: Make it bounce
    Color rayColor;
    if (closestCollision == null) {
      rayColor = skyColor;
    } else {
      rayColor = collisionObject.getColor();
    }
    return rayColor;
  }
}
