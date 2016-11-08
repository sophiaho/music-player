package cs3500.music.util;

import cs3500.music.model.*;

/**
 * A class implementation of a Composition Builder.
 */
public class SongBuilder implements CompositionBuilder<ISong> {

  private final ISong comp = new Song();

  /**
   * Constructs the composition given the notes that have been added.
   *
   * @return the composition (an ISong)
   */
  @Override
  public ISong build() {
    return this.comp;
  }

  /**
   * Sets the tempo for the song.
   *
   * @param tempo The speed, in microseconds per beat
   * @return the song builder
   */
  @Override
  public CompositionBuilder<ISong> setTempo(int tempo) {
    this.comp.setTempo(tempo);
    return this;
  }

  /**
   * Adds a note to the ISong.
   *
   * @param start      The start time of the note, in beats
   * @param end        The end time of the note, in beats
   * @param instrument The instrument number (to be interpreted by MIDI)
   * @param pitch      The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a
   *                   piano)
   * @param volume     The volume (in the range [0, 127])
   * @return the song builder
   */
  @Override
  public CompositionBuilder<ISong> addNote(int start, int end, int instrument, int pitch,
                                           int volume) {
    this.comp.addNote(new Note(end - start, ITone.fromInt(pitch), start, volume, instrument));
    return this;
  }
}
