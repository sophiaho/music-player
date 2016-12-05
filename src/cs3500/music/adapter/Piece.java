package cs3500.music.adapter;

import java.util.HashMap;
import java.util.List;

/**
 * Interface for Piece in provider.
 */
public interface Piece {
  List<Note> getPiece();

  int getMinPitch();

  int getMaxPitch();

  int getMeasure();

  int getMaxBeat();

  static Pitch numToPitch(int i) {
    HashMap<Integer, cs3500.music.model.Pitch> map = new HashMap<>();

    map.put(0, cs3500.music.model.Pitch.C);
    map.put(1, cs3500.music.model.Pitch.CS);
    map.put(2, cs3500.music.model.Pitch.D);
    map.put(3, cs3500.music.model.Pitch.DS);
    map.put(4, cs3500.music.model.Pitch.E);
    map.put(5, cs3500.music.model.Pitch.F);
    map.put(6, cs3500.music.model.Pitch.FS);
    map.put(7, cs3500.music.model.Pitch.G);
    map.put(8, cs3500.music.model.Pitch.GS);
    map.put(9, cs3500.music.model.Pitch.A);
    map.put(10, cs3500.music.model.Pitch.AS);
    map.put(11, cs3500.music.model.Pitch.B);

    return new PitchAdapter(map.get(i));
  }
}
