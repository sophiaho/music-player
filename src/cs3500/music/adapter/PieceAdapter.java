package cs3500.music.adapter;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.model.ISong;
import cs3500.music.model.ITone;

/**
 * Created by andrew on 01/12/2016.
 */
public class PieceAdapter implements Piece {
  private ISong song;

  public PieceAdapter(ISong song) {
    this.song = song;
  }

  @Override
  public Piece getPiece() {
    return null;
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
