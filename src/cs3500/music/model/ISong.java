package cs3500.music.model;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

  Set<Integer> rStarts();

  Set<Integer> rEnds();

  Set<Integer> mStarts();

  Set<Integer> mEnds();

  void restartRepeats();

  boolean addSafeBasicRepeat(int s, int e);

  boolean isRepeat(int i);

  int corrStart(int i);

  boolean multipeAction(int i);

  int multipleSet(int i);

  void addMultipleRepeat(int s, int r, List<Integer> ends);
}
