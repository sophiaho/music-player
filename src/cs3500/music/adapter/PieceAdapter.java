package cs3500.music.adapter;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.model.INote;
import cs3500.music.model.ISong;
import cs3500.music.model.ITone;

/**
 * Adapts ISong to a Piece.
 */
public class PieceAdapter implements Piece {
  private ISong song;
  private List<Note> pieceImpl;

  public PieceAdapter(ISong song) {
    this.song = song;
    this.pieceImpl = this.refreshPiece();
  }

  @Override
  public List<Note> getPiece() {
    return this.pieceImpl;
  }

  private List<Note> refreshPiece() {
    List<Note> output = new ArrayList<>();
    for (int i : song.starts().keySet()) {
      for (INote n : song.starts().get(i)) {
        output.add(new NoteAdapter(n));
      }
    }
    return output;
  }

  @Override
  public int getMinPitch() {
    return song.getRange().get(0).numeric();
  }

  @Override
  public int getMaxPitch() {
    List<ITone> range = song.getRange();
    return range.get(range.size() - 1).numeric();
  }

  @Override
  public int getMeasure() {
    return 4;
  }

  @Override
  public int getMaxBeat() {
    return song.songLength();
  }
}
