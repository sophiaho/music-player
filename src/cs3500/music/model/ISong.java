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

  /**
   * Adds a note to the song.
   *
   * @param n INote
   */
  void addNote(INote n);

  /**
   * Deletes a note from the song.
   *
   * @param n INote
   */
  void deleteNote(INote n);

  /**
   * Edits a note.
   *
   * @param input    INote original
   * @param changeTo INote changed
   */
  void editNote(INote input, INote changeTo);

  /**
   * Returns a map of the starts.
   *
   * @return notes' start points
   */
  TreeMap<Integer, List<INote>> starts();

  /**
   * Returns a map of the ends.
   *
   * @return notes' end points
   */
  TreeMap<Integer, List<INote>> ends();

  /**
   * Returns the range of the tones based off the song.
   *
   * @return a list of ITone that is the range of tones that the song has.
   */
  List<ITone> getRange();

  /**
   * Gets the length of the song.
   *
   * @return int of the position
   */
  int songLength();

  /**
   * Sets the tempo of the song.
   */
  void setTempo(int tempo);

  /**
   * Gets the tempo.
   *
   * @return the tempo that was set for the song.
   */
  int getTempo();
}
