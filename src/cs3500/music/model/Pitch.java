package cs3500.music.model;

import java.util.HashMap;

/**
 * Pitch enum, represents the letter value of the Tone, which is the sound of a Note.
 */
public enum Pitch {
  C(1), CS(2), D(3), DS(4), E(5), F(6), FS(7), G(8), GS(9), A(10), AS(11), B(12);

  private int noteValue;
  private String[] stringVal = new String[12];

  Pitch(int value) {
    this.noteValue = value;
    stringVal[0] = "C";
    stringVal[1] = "C#";
    stringVal[2] = "D";
    stringVal[3] = "D#";
    stringVal[4] = "E";
    stringVal[5] = "F";
    stringVal[6] = "F#";
    stringVal[7] = "G";
    stringVal[8] = "G#";
    stringVal[9] = "A";
    stringVal[10] = "A#";
    stringVal[11] = "B";
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

    nextMap.put(Pitch.A, Pitch.AS);
    nextMap.put(Pitch.AS, Pitch.B);
    nextMap.put(Pitch.B, Pitch.C);
    nextMap.put(Pitch.C, Pitch.CS);
    nextMap.put(Pitch.CS, Pitch.D);
    nextMap.put(Pitch.D, Pitch.DS);
    nextMap.put(Pitch.DS, Pitch.E);
    nextMap.put(Pitch.E, Pitch.F);
    nextMap.put(Pitch.F, Pitch.FS);
    nextMap.put(Pitch.FS, Pitch.G);
    nextMap.put(Pitch.G, Pitch.GS);
    nextMap.put(Pitch.GS, Pitch.A);

    return nextMap.get(this);
  }

  /**
   * Gets the value of a note.
   *
   * @return the note's value as an int
   */
  public int getNoteValue() {
    return noteValue;
  }

  /**
   * Gets the names of the notes.
   *
   * @return the strings that the notes correspond to
   */
  public String[] getStringVal() {
    return stringVal;
  }
}
