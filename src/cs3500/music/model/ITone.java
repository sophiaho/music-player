package cs3500.music.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by andrew on 02/11/2016.
 */
public interface ITone extends Comparable {
  @Override
  String toString();

  /**
   * Returns the whole set of tones in between the two values.
   *
   * @param upperBound the highest note in the set of notes desired.
   * @return List of all tones in between this and upperBound, inclusive
   */
  List<ITone> toneRange(ITone upperBound);

  @Override
  int compareTo(Object o);

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);

  /**
   * Getter method for the octave of the tone.
   * @return int ordinal of the tone.
   */
  int getOctave();

  /**
   * Returns the value of this as a midi int.
   *
   * @return a number that corresponds to a tone's pitch and octave
   */
  int numeric();

  /**
   * Returns the pitch of this note. Getter method.
   *
   * @return pitch of the note.
   */
  Pitch getPitch();

  /**
   * A static method that translates the numeric to a tone.
   *
   * @param value the numeric
   * @return the tone that corresponds with the numeric
   */
  static ITone fromInt(int value) {

    HashMap<Integer, Pitch> output = new HashMap<>();

    output.put(0, Pitch.C);
    output.put(1, Pitch.CS);
    output.put(2, Pitch.D);
    output.put(3, Pitch.DS);
    output.put(4, Pitch.E);
    output.put(5, Pitch.F);
    output.put(6, Pitch.FS);
    output.put(7, Pitch.G);
    output.put(8, Pitch.GS);
    output.put(9, Pitch.A);
    output.put(10, Pitch.AS);
    output.put(11, Pitch.B);

    return new Tone(output.get(value % 12), (value / 12) - 1);
  }
}
