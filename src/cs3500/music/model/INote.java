package cs3500.music.model;

/**
 * Created by andrew on 02/11/2016.
 */
public interface INote extends Comparable {
  @Override
  String toString();

  @Override
  int compareTo(Object o);

  @Override
  int hashCode();

  /**
   * Returns instrument key.
   *
   * @return the int that corresponds to the instrument
   */
  int getInstrument();

  /**
   * Returns the end of the note.
   *
   * @return the beat that the note ends on
   */
  int getEnd();

  /**
   * Returns the start of the note.
   *
   * @return the beat that the note starts on
   */
  int getStart();

  /**
   * Returns the volume.
   *
   * @return the volume of the note
   */
  int getVolume();

  /**
   * Returns the midi numeric.
   *
   * @return the numerical value for the Tone
   */
  int getMidi();

  /**
   * Returns the duration of the note.
   *
   * @return the duration
   */
  int getDuration();

  /**
   * Returns the tone.
   *
   * @return the tone of the note.
   */
  ITone getTone();

  static INote fromString(String input) {
    String[] arr = input.split(" ");

    return new Note(Integer.valueOf(arr[0]), ITone.fromInt(Integer.valueOf(arr[1])),
    Integer.valueOf(arr[2]), Integer.valueOf(arr[3]), Integer.valueOf(arr[4]));
  }
}
