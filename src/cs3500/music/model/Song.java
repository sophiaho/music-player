package cs3500.music.model;

import java.util.List;
import java.util.TreeMap;

/**
 * Class for Song, main music model.
 */
public class Song extends TreeMap<Tone, NoteSet> {
  Song() {
    // extends the Treemap
  }

  @Override
  /**
   * Completes toString for this, using delegated toString methods.
   * @return this song as a string that looks like a table
   */
  public String toString() {
    if (this.keySet().size() == 0) {
      return "";
    }

    int totalLength = 0;
    for (NoteSet n : this.values()) {
      totalLength = Math.max(totalLength, n.endTime());
    }
    int indexDigits = (int) Math.log10(totalLength) + 1;

    List<Tone> toneSet = this.firstKey().toneRange(this.lastKey());

    String output = header(indexDigits, toneSet);
    output += songVis(totalLength, indexDigits, toneSet);

    return output;
  }

  /**
   * Adds a note to the song.
   * @param n note to add to song.
   */
  public void addNote(Note n) {
    if (this.keySet().contains(n.tone)) {
      this.get(n.tone).addSafe(n);
    } else {
      this.put(n.tone, new NoteSet(n));
    }
  }

  /**
   * Deletes a note.
   * @param n note to add to song.
   */
  public void deleteNote(Note n) {
    if (this.keySet().contains(n.tone)) {
      this.get(n.tone).remove(n);
    } else {
      throw new IllegalArgumentException("Song does not contain that note.");
    }
  }

  /**
   * Edit note, changes an input note to anther one.
   * @param input note to change.
   * @param changeTo what to change the note into.
   */
  public void editNote(Note input, Note changeTo) {
    this.deleteNote(input);
    this.addNote(changeTo);
  }

  /**
   * Returns the header of the song state (needed notes).
   *
   * @param indexDigits number of digits needed for padding
   * @param toneSet     set of tones to write out
   * @return the notes in the song
   */
  private String header(int indexDigits, List<Tone> toneSet) {
    String output = String.format("%" + indexDigits + "s", "");
    for (Tone t : toneSet) {
      output += String.format("%5s", t.toString());
    }
    output += "\n";
    return output;
  }

  /**
   * Returns the table with the notes and visualization.
   *
   * @param totalLength length of the whole song
   * @param indexDigits number of digits for index
   * @param toneSet     set of tones to render
   */
  private String songVis(int totalLength, int indexDigits, List<Tone> toneSet) {
    String output = "";
    int ind = 0;
    for (int i = 0; i < totalLength; i++) {
      output += String.format("%" + indexDigits + "s", String.valueOf(ind)) + " ";
      for (Tone t : toneSet) {
        if (!this.keySet().contains(t)) {
          output += "     ";
        } else {
          switch (get(t).whatPlaying(i)) {
            case REST:
              output += "     ";
              break;
            case HEAD:
              output += "  X  ";
              break;
            case SUSTAIN:
              output += "  |  ";
              break;
            default:
              throw new IllegalArgumentException("Not a valid playing type.");
          }
        }
      }
      output += "\n";
      ind += 1;
    }
    return output;
  }
}