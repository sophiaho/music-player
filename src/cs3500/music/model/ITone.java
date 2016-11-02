package cs3500.music.model;

import java.util.List;

/**
 * Created by andrew on 02/11/2016.
 */
public interface ITone extends Comparable {
  @Override
  String toString();

  List<ITone> toneRange(ITone upperBound);

  @Override
  int compareTo(Object o);

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);

  int midiOrdinal();

  double numeric();
}
