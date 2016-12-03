package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tone represents a sound (Pitch and Accidental and Octave) without any time fields.
 */
public class Tone implements ITone {
  private Pitch pitch;
  private int octave;

  Tone(Pitch pitch, int octave) {
    this.pitch = pitch;
    this.octave = octave;
  }

  @Override
  public String toString() {
    return this.pitch.toString() + String.valueOf(this.octave);
  }


  @Override
  public List<ITone> toneRange(ITone upperBound) {
    List<ITone> output = new ArrayList<>();

    Tone toAdd = this;
    Tone next;

    while (toAdd.numeric() != upperBound.numeric()) {

      if (toAdd.pitch.equals(Pitch.B)) {
        next = new Tone(toAdd.pitch.next(), toAdd.octave + 1);
      } else {
        next = new Tone(toAdd.pitch.next(), toAdd.octave);
      }

      output.add(toAdd);
      toAdd = next;
    }

    output.add(upperBound);

    return output;
  }

  @Override
  public int compareTo(Object o) {
    if (o instanceof Tone) {
      Tone other = (Tone) o;
      if (this.numeric() == other.numeric()) {
        return 0;
      } else if ((this.numeric() - other.numeric()) < 0) {
        return -1;
      } else {
        return 1;
      }
    } else {
      throw new IllegalArgumentException("Not a valid Tone");
    }
  }

  @Override
  public int hashCode() {
    return (int) (this.numeric() * 10);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Tone) {
      return this.numeric() == ((Tone) obj).numeric();
    } else {
      return false;
    }
  }

  @Override
  public int numeric() {
    return 12 * (this.octave + 1) +
            this.pitch.getNoteValue();
  }

  @Override
  public int getOctave() {
    return octave;
  }

  @Override
  public Pitch getPitch() {
    return pitch;
  }
}