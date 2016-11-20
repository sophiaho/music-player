package cs3500.music.model;

/**
 * Note contains a tone (sound) a duration, and a start time.
 */
public class Note implements INote {

  private int duration;
  private ITone tone;
  private int start;
  private int volume;
  private int instrument;

  /**
   * Constructor for Note, defaults to instrument 1 for testing.
   *
   * @param p        pitch / sound
   * @param duration duration
   * @param o        octave
   */
  Note(Pitch p, int duration, int o, int start) {
    this.tone = new Tone(p, o);
    this.duration = duration;
    this.start = start;
    this.volume = 1;
    this.instrument = 1;
  }

  /**
   * Constructor for all fields.
   *
   * @param duration   duration of note
   * @param tone       tone / sound
   * @param start      start time in beats
   * @param volume     volume for midi
   * @param instrument midi instrument
   */
  public Note(int duration, ITone tone, int start, int volume, int instrument) {
    this.duration = duration;
    this.tone = tone;
    this.start = start;
    this.volume = volume;
    this.instrument = instrument;
  }

  @Override
  public String toString() {
    return this.tone.toString();
  }

  @Override
  public int compareTo(Object o) {
    if (o instanceof Note) {
      return this.start - ((Note) o).start;
    } else {
      throw new IllegalArgumentException("Not a Note");
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Note) {
      return (this.tone.numeric() == ((Note) obj).tone.numeric())
              && (this.start == ((Note) obj).start)
              && (this.duration == ((Note) obj).duration)
              && (this.volume == ((Note) obj).volume)
              && (this.instrument == ((Note) obj).instrument);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return ((((this.tone.numeric() * 31 + this.duration) * 67 + this.start))
            * 139 + this.instrument) * 19 + this.volume;
  }

  @Override
  public int getInstrument() {
    return this.instrument;
  }

  @Override
  public int getEnd() {
    return this.start + this.duration;
  }

  @Override
  public int getStart() {
    return this.start;
  }

  @Override
  public int getVolume() {
    return this.volume;
  }

  @Override
  public int getMidi() {
    return this.tone.numeric();
  }

  @Override
  public ITone getTone() {
    return this.tone;
  }

  @Override
  public int getDuration() {
    return this.duration;
  }
}