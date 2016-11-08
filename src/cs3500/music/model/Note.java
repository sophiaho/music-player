package cs3500.music.model;

import java.util.HashMap;

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
   * Constructor for Note.
   *
   * @param p        pitch
   * @param duration duration
   * @param o        octave
   */
  Note(Pitch p, int duration, int o, int start) {
    this.tone = new Tone(p, o);
    this.duration = duration;
    this.start = start;
    this.volume = 1;
    this.instrument = 0;
  }

  /**
   * Constructor for all fields.
   *
   * @param duration
   * @param tone
   * @param start
   * @param volume
   * @param instrument
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

  /**
   * Returns if a tone is the same as this.
   */
  boolean rightTone(ITone tone) {
    return (tone.compareTo(tone) == 0);
  }

  /**
   * Compares this object with the specified object for order.  Returns a
   * negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object.
   *
   * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
   * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
   * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
   * <tt>y.compareTo(x)</tt> throws an exception.)
   *
   * <p>The implementor must also ensure that the relation is transitive:
   * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
   * <tt>x.compareTo(z)&gt;0</tt>.
   *
   * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
   * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
   * all <tt>z</tt>.
   *
   * <p>It is strongly recommended, but <i>not</i> strictly required that
   * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
   * class that implements the <tt>Comparable</tt> interface and violates
   * this condition should clearly indicate this fact.  The recommended
   * language is "Note: this class has a natural ordering that is
   * inconsistent with equals."
   *
   * <p>In the foregoing description, the notation
   * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
   * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
   * <tt>0</tt>, or <tt>1</tt> according to whether the value of
   * <i>expression</i> is negative, zero or positive.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer for object comparison.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it from being compared to
   *                              this object.
   */
  @Override
  public int compareTo(Object o) {
    if (o instanceof Note) {
      return this.start - ((Note) o).start;
    } else {
      throw new IllegalArgumentException("Not a Note");
    }
  }

  /**
   * Returns a hash code value for the object. This method is
   * supported for the benefit of hash tables such as those provided by
   * {@link HashMap}.
   * <p>
   * The general contract of {@code hashCode} is:
   * <ul>
   * <li>Whenever it is invoked on the same object more than once during
   * an execution of a Java application, the {@code hashCode} method
   * must consistently return the same integer, provided no information
   * used in {@code equals} comparisons on the object is modified.
   * This integer need not remain consistent from one execution of an
   * application to another execution of the same application.
   * <li>If two objects are equal according to the {@code equals(Object)}
   * method, then calling the {@code hashCode} method on each of
   * the two objects must produce the same integer result.
   * <li>It is <em>not</em> required that if two objects are unequal
   * according to the {@link Object#equals(Object)}
   * method, then calling the {@code hashCode} method on each of the
   * two objects must produce distinct integer results.  However, the
   * programmer should be aware that producing distinct integer results
   * for unequal objects may improve the performance of hash tables.
   * </ul>
   * <p>
   * As much as is reasonably practical, the hashCode method defined by
   * class {@code Object} does return distinct integers for distinct
   * objects. (This is typically implemented by converting the internal
   * address of the object into an integer, but this implementation
   * technique is not required by the
   * Java&trade; programming language.)
   *
   * @return a hash code value for this object.
   * @see Object#equals(Object)
   * @see System#identityHashCode
   */
  @Override
  public int hashCode() {
    return (int) ((((this.tone.numeric() * 31 + this.duration) * 67 + this.start))
            * 139 + this.instrument) * 19 + this.volume;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * <p>
   * The {@code equals} method implements an equivalence relation
   * on non-null object references:
   * <ul>
   * <li>It is <i>reflexive</i>: for any non-null reference value
   * {@code x}, {@code x.equals(x)} should return
   * {@code true}.
   * <li>It is <i>symmetric</i>: for any non-null reference values
   * {@code x} and {@code y}, {@code x.equals(y)}
   * should return {@code true} if and only if
   * {@code y.equals(x)} returns {@code true}.
   * <li>It is <i>transitive</i>: for any non-null reference values
   * {@code x}, {@code y}, and {@code z}, if
   * {@code x.equals(y)} returns {@code true} and
   * {@code y.equals(z)} returns {@code true}, then
   * {@code x.equals(z)} should return {@code true}.
   * <li>It is <i>consistent</i>: for any non-null reference values
   * {@code x} and {@code y}, multiple invocations of
   * {@code x.equals(y)} consistently return {@code true}
   * or consistently return {@code false}, provided no
   * information used in {@code equals} comparisons on the
   * objects is modified.
   * <li>For any non-null reference value {@code x},
   * {@code x.equals(null)} should return {@code false}.
   * </ul>
   * <p>
   * The {@code equals} method for class {@code Object} implements
   * the most discriminating possible equivalence relation on objects;
   * that is, for any non-null reference values {@code x} and
   * {@code y}, this method returns {@code true} if and only
   * if {@code x} and {@code y} refer to the same object
   * ({@code x == y} has the value {@code true}).
   * <p>
   * Note that it is generally necessary to override the {@code hashCode}
   * method whenever this method is overridden, so as to maintain the
   * general contract for the {@code hashCode} method, which states
   * that equal objects must have equal hash codes.
   *
   * @param obj the reference object with which to compare.
   * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
   * @see #hashCode()
   * @see HashMap
   */
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

  /**
   * Returns instrument key.
   *
   * @return the int that corresponds to the instrument
   */
  @Override
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Returns the end of the note.
   *
   * @return the beat that the note ends on
   */
  @Override
  public int getEnd() {
    return this.start + this.duration;
  }

  /**
   * Returns the start of the note.
   *
   * @return the beat that the note starts on
   */
  @Override
  public int getStart() {
    return this.start;
  }

  /**
   * Returns the volume.
   *
   * @return the volume of the note
   */
  @Override
  public int getVolume() {
    return this.volume;
  }

  /**
   * Returns the midi numeric.
   *
   * @return the numerical value for the Tone
   */
  @Override
  public int getMidi() {
    return this.tone.numeric();
  }

  /**
   * Returns the tone.
   *
   * @return the tone of the note.
   */
  @Override
  public ITone getTone() {
    return this.tone;
  }

  /**
   * Returns the duration of the note.
   *
   * @return the duration
   */
  @Override
  public int getDuration() {
    return this.duration;
  }
}