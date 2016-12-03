package cs3500.music.util;

import cs3500.music.adapter.IMusicEditorModelView;
import cs3500.music.adapter.MusicEditorAdapter;
import cs3500.music.adapter.Piece;
import cs3500.music.adapter.PieceAdapter;
import cs3500.music.model.ISong;
import cs3500.music.model.ITone;
import cs3500.music.model.Note;
import cs3500.music.model.Song;

/**
 * Created by andrew on 02/12/2016.
 */
public class ProviderSongBuilder implements CompositionBuilder<IMusicEditorModelView> {

  private final ISong comp = new Song(); // Song to build upPress

  @Override
  public IMusicEditorModelView build() {
    return new MusicEditorAdapter(this.comp);
  }

  @Override
  public CompositionBuilder<IMusicEditorModelView> setTempo(int tempo) {
    this.comp.setTempo(tempo);
    return this;
  }

  @Override
  public CompositionBuilder<IMusicEditorModelView> addNote(int start, int end, int instrument, int pitch,
                                           int volume) {
    this.comp.addNote(new Note(end - start, ITone.fromInt(pitch), start, volume, instrument));
    return this;
  }
}
