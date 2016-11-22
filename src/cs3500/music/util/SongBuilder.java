package cs3500.music.util;

import cs3500.music.model.ISong;
import cs3500.music.model.ITone;
import cs3500.music.model.Note;
import cs3500.music.model.Song;

/**
 * Creates a song using the reader.
 */
public class SongBuilder implements CompositionBuilder<ISong> {

  private final ISong comp = new Song(); // Song to build upPress

  @Override
  public ISong build() {
    return this.comp;
  }

  @Override
  public CompositionBuilder<ISong> setTempo(int tempo) {
    this.comp.setTempo(tempo);
    return this;
  }

  @Override
  public CompositionBuilder<ISong> addNote(int start, int end, int instrument, int pitch,
                                           int volume) {
    this.comp.addNote(new Note(end - start, ITone.fromInt(pitch), start, volume, instrument));
    return this;
  }
}
