package cs3500.music.model;

import java.util.HashMap;
import java.util.List;

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

  HashMap<Integer, List<INote>> starts();

  HashMap<Integer, List<INote>> ends();

  List<ITone> getRange();

  /**
   * Gets the length of the song.
   *
   * @return int of the position
   */
  int songLength();

  void setTempo(int tempo);

  int getTempo();

  void deleteNoteAtX(ITone tone, int time);

  HashMap<Integer, Boolean> rStarts();

  HashMap<Integer, Boolean> rEnds();

  int corrStart(int i);

  void addRepeatStart(int i);

  void addRepeatEnd(int i);

  void addCorrStart(int s, int e);

  void restartRepeats();
}
