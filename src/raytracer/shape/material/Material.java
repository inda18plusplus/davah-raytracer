package raytracer.shape.material;

public class Material {

  private double ambientRatio;
  private double diffuseRatio;
  private double reflectivity;
  private double fuzziness;
  private double specularRatio;
  private double specularExponent;

  /**
   * Creates a new material with certain properties regarding how it affects light.
   *
   * @param ambientRatio Proportion of ambient light that gets reflected.
   * @param diffuseRatio Proportion of incoming light that gets reflected according to Lambert's
   *                     cosine law.
   * @param reflectivity Proportion of all incoming light (not only directly from light sources)
   *                     that is reflected in the recursive ray tracing step.
   * @param fuzziness How much to blur the recursive ray tracing light (0 = no blur, 1 = maximum
   *                  blur). Higher values will need more anti-aliasing to look good.
   * @param specularRatio Proportion of incoming light that gets reflected as specular light in
   *                      Phong's light model.
   * @param specularExponent I refer to Phong's model again for this one.
   */
  public Material(double ambientRatio,
          double diffuseRatio,
          double reflectivity,
          double fuzziness,
          double specularRatio,
          double specularExponent) {
    this.ambientRatio = ambientRatio;
    this.diffuseRatio = diffuseRatio;
    this.reflectivity = reflectivity;
    this.fuzziness = fuzziness;
    this.specularRatio = specularRatio;
    this.specularExponent = specularExponent;
  }

  public double getAmbientRatio() {
    return ambientRatio;
  }

  public double getDiffuseRatio() {
    return diffuseRatio;
  }

  public double getReflectivity() {
    return reflectivity;
  }

  public double getFuzziness() {
    return fuzziness;
  }

  public double getSpecularRatio() {
    return specularRatio;
  }

  public double getSpecularExponent() {
    return specularExponent;
  }
}
