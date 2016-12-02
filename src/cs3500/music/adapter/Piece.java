package cs3500.music.adapter;

import java.util.List;

import cs3500.music.model.ITone;

/**
 * Created by andrew on 01/12/2016.
 */
public interface Piece extends List<Note> {
  Piece getPiece();

  int getMinPitch();

  int getMaxPitch();

  int getMeasure();

  int getMaxBeat();

  static Pitch numToPitch(int i) {
    return new PitchAdapter(ITone.fromInt(i));
  }
}
