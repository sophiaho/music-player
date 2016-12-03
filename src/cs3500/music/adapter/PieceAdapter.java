package cs3500.music.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cs3500.music.model.INote;
import cs3500.music.model.ISong;
import cs3500.music.model.ITone;

/**
 * Created by andrew on 01/12/2016.
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
    for (int i = 0; i < song.songLength(); i++) {
      if (song.starts().containsKey(i)) {
        for (INote n : song.starts().get(i)) {
          output.add(new NoteAdapter(n));
        }
      }
    }
    Collections.sort(output, new NoteComparator());
    return output;
  }

  private class NoteComparator implements Comparator<Note> {
    @Override
    public int compare(Note o1, Note o2) {
      return o1.getOrdinal() - o2.getOrdinal();
    }
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
    return 0;
  }

  @Override
  public int getMaxBeat() {
    return song.songLength();
  }
}
