package cs3500.music.model;

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

  void addNote(Note n);

  void deleteNote(Note n);

  void editNote(Note input, Note changeTo);

  List<INote> allStartsAt(int time);

  List<INote> allEndsAt(int time);

  List<ITone> getRange();

  /**
   * Gets the length of the song.
   *
   * @return int of the position
   */
  int songLength();
}
