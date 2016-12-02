package cs3500.music.adapter;

/**
 * Created by andrew on 01/12/2016.
 */
public interface Note {
  int getStartTime();

  int getBeats();

  Pitch getPitch();

  int getOctave();

  int getVolume();

  int getInstrument();
}
