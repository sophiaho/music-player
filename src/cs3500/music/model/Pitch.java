package cs3500.music.model;

import java.util.HashMap;

/**
 * Pitch enum, represents the letter value of the Tone, which is the sound of a Note.
 */
enum Pitch {
  C(1), D(2), E(3), F(4), G(5), A(6), B(7);

  int noteValue;
  private String[] stringVal = new String[7];

  Pitch(int value) {
    this.noteValue = value;
    stringVal[0] = "C";
    stringVal[1] = "D";
    stringVal[2] = "E";
    stringVal[3] = "F";
    stringVal[4] = "G";
    stringVal[5] = "A";
    stringVal[6] = "B";
  }

  /**
   * Returns this Pitch as a String (just the letters of the notes).
   *
   * @return string representation
   */
  @Override
  public String toString() {
    return stringVal[noteValue - 1];
  }

  /**
   * Returns the next pitch after this one.
   *
   * @return pitch representing the next largest pitch.
   */
  public Pitch next() {
    HashMap<Pitch, Pitch> nextMap = new HashMap<>();

    nextMap.put(Pitch.A, Pitch.B);
    nextMap.put(Pitch.B, Pitch.C);
    nextMap.put(Pitch.C, Pitch.D);
    nextMap.put(Pitch.D, Pitch.E);
    nextMap.put(Pitch.E, Pitch.F);
    nextMap.put(Pitch.F, Pitch.G);
    nextMap.put(Pitch.G, Pitch.A);

    return nextMap.get(this);
  }
}
