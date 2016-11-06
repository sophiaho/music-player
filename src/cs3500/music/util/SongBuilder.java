package cs3500.music.util;

import cs3500.music.model.Song;

/**
 * Created by soapyho on 11/4/16.
 */
public class SongBuilder implements CompositionBuilder<Song> {

  private final Song comp = new Song();

  @Override
  public Song build() {
    return null;
  }

  @Override
  public CompositionBuilder<Song> setTempo(int tempo) {
    return null;
  }

  @Override
  public CompositionBuilder<Song> addNote(int start, int end, int instrument, int pitch, int volume) {
    return null;
  }
}
