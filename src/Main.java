import raytracer.Position;
import raytracer.Scene;

public class Main {

  public static void main(String[] args) {
    Scene scene = new Scene();
    scene.draw(new Position(1, 0, 0), new Position(0, 0, 0), 140,
            600, 300, "image.bmp");
  }
}
