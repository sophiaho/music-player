package cs3500.music.model;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by andrew on 02/11/2016.
 */
public interface ISong {
  @Override
  /**
   * Completes toString for this.contents, using delegated toString methods.
   * @return this.contents song as a string that looks like a table
   */
  String toString();

  void addNote(INote n);

  void deleteNote(INote n);

  void editNote(INote input, INote changeTo);

  List<INote> allStartsAt(int time);

  List<INote> allEndsAt(int time);

  TreeMap<Integer, List<INote>> starts();

  TreeMap<Integer, List<INote>> ends();

  List<ITone> getRange();

  /**
   * Gets the length of the song.
   *
   * @return int of the position
   */
  int songLength();

  void setTempo(int tempo);

  int getTempo();
}
