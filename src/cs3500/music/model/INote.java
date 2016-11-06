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

  int getInstrument();

  int getEnd();

  int getStart();

  int getVolume();

  int getMidi();

  int getDuration();

  ITone getTone();
}
