package raytracer;

import java.util.ArrayList;
import java.util.List;

import raytracer.geometry.Ray;
import raytracer.geometry.Vector3D;
import raytracer.light.Light;
import raytracer.shape.Shape;

public class Scene {

  private static final int MAX_RECURSION_DEPTH = 3;

  private List<Shape> shapes;
  private List<Light> lights;

  /**
   * Creates a new scene.
   */
  public Scene() {
    shapes = new ArrayList<>();
    lights = new ArrayList<>();
  }

  public void add(Shape shape) {
    shapes.add(shape);
  }

  public void addLight(Light light) {
    lights.add(light);
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
  public void draw(Vector3D cameraPosition, Vector3D facing, double angleOfView, int antiAliasing,
                   int width, int height, String filename) {
    PicturePlane picturePlane = new PicturePlane(
            cameraPosition, facing, angleOfView, width, height);
    Picture picture = new Picture(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color pixelColor;
        if (antiAliasing == 1) {
          pixelColor = trace(picturePlane.getRay(x, y), 0);
        } else {
          pixelColor = new Color(0, 0, 0);
          for (int i = 0; i < antiAliasing; i++) {
            pixelColor = pixelColor.add(trace(picturePlane.getAaRay(x, y), 0));
          }
          pixelColor = pixelColor.scale(1.0 / antiAliasing);
        }
        picture.setPixel(x, y, pixelColor.getColor());
      }
    }
    picture.write(filename);
  }

  private Color trace(Ray ray, int depth) {
    Collision collision = getClosestCollision(ray);
    if (collision.position == null) {
      return getSkyColor(ray.getDirection());
    }
    double ambientFactor = getAmbientLightLevel(collision.position)
            * collision.shape.getMaterial().getAmbientRatio();
    Color surfaceColor = collision.shape.getTexture().getColor(collision.position, collision.shape);
    Color result = surfaceColor.scale(ambientFactor);

    Ray normal = collision.shape.getNormal(collision.position);
    Ray reflection = ray.bounce(normal, collision.shape.getMaterial().getFuzziness());

    if (depth < MAX_RECURSION_DEPTH && collision.shape.getMaterial().getReflectivity() > 0.0) {
      Color reflectionColor = trace(reflection.nudgedForward(), depth + 1);
      result = result.add(reflectionColor.scale(collision.shape.getMaterial().getReflectivity()));
    }

    for (Light light : lights) {
      Vector3D lightPosition = light.getPosition();
      Vector3D lightVector = lightPosition.subtract(collision.position);
      double distanceToLight = lightVector.length();
      double distanceToLightSquared = lightVector.lengthSquared();
      double lightIntensity = Math.min(1, light.getIntensity() / distanceToLightSquared);
      Color lightColor = light.getColor();
      Vector3D lightDirection = lightVector.normalize();
      Ray shadowRay = new Ray(collision.position, lightVector);
      boolean inShadow = isObstructed(shadowRay.nudgedForward(), distanceToLight);
      if (!inShadow) {
        double diffusionFactor = lightIntensity
                * Math.max(0, normal.getDirection().dot(lightDirection))
                * collision.shape.getMaterial().getDiffuseRatio();
        Color diffusion = surfaceColor.multiply(lightColor.scale(diffusionFactor));
        result = result.add(diffusion);

        double specularFactor = lightIntensity
                * Math.pow(Math.max(0, reflection.getDirection().dot(lightDirection)),
                  collision.shape.getMaterial().getSpecularExponent())
                * collision.shape.getMaterial().getSpecularRatio();
        Color specularColor = light.getColor().scale(specularFactor);
        result = result.add(specularColor);
      }
    }

    return result.clip();
  }

  private Color getSkyColor(Vector3D direction) {
    double t = 0.5 * (direction.getY() + 1.0);
    Color up = new Color(0.9, 0.9, 0.9);
    Color down = new Color(0.6, 0.6, 0.6);
    return down.scale(1.0 - t).add(up.scale(t));
  }

  private double getAmbientLightLevel(Vector3D position) {
    return 1.0;
  }

  private Collision getClosestCollision(Ray ray) {
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
    return new Collision(closestCollision, collisionObject);
  }

  private boolean isObstructed(Ray ray, double distance) {
    for (Shape shape : shapes) {
      for (Vector3D collision : shape.getCollisions(ray)) {
        if (ray.getOrigin().subtract(collision).length() < distance) {
          return true;
        }
      }
    }
    return false;
  }

  private class Collision {

    private Vector3D position;
    private Shape shape;

    private Collision(Vector3D position, Shape shape) {
      this.position = position;
      this.shape = shape;
    }
  }
}