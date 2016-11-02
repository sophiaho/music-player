package cs3500.music.model;

/**
 * Enum that represents accidentals.
 */
enum Accidental {
  FLAT(-0.5), NATURAL(0), SHARP(0.5);

  public final double effect;

  Accidental(double value) {
    this.effect = value;
  }
}
