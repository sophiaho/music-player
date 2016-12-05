package cs3500.music.adapter;

/**
 * Note Interface.
 */
public interface Note {
  int getStartTime();

  int getBeats();

  Pitch getPitch();

  int getOctave();

  int getVolume();

  int getInstrument();
}
