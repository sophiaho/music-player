package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Class for Song, main music model.
 */
public class Song {
  private TreeMap<Tone, NoteSet> contents;
  private Tone high;
  private Tone low;

  Song() {
    this.contents = new TreeMap<>();
  }

  @Override
  /**
   * Completes toString for this.contents, using delegated toString methods.
   * @return this.contents song as a string that looks like a table
   */
  public String toString() {
    if (this.contents.keySet().size() == 0) {
      return "";
    }

    int totalLength = 0;
    for (NoteSet n : this.contents.values()) {
      totalLength = Math.max(totalLength, n.endTime());
    }
    int indexDigits = (int) Math.log10(totalLength) + 1;

    List<Tone> toneSet = this.contents.firstKey().toneRange(this.contents.lastKey());

    String output = header(indexDigits, toneSet);
    output += songVis(totalLength, indexDigits, toneSet);

    return output;
  }

  /**
   * Adds a note to the song.
   * @param n note to add to song.
   */
  public void addNote(Note n) {
    if (this.contents.keySet().contains(n.tone)) {
      this.contents.get(n.tone).addSafe(n);
    } else {
      this.contents.put(n.tone, new NoteSet(n));
    }
  }

  /**
   * Deletes a note.
   * @param n note to add to song.
   */
  public void deleteNote(Note n) {
    if (this.contents.keySet().contains(n.tone)) {
      this.contents.get(n.tone).remove(n);
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
        if (!this.contents.keySet().contains(t)) {
          output += "     ";
        } else {
          switch (this.contents.get(t).whatPlaying(i)) {
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

  /**
   *
   */
  public List<Note> allStartsAt(int time) {
    List<Note> output = new ArrayList<>();
    for (Tone t : this.contents.keySet()) {
      output.addAll(this.contents.get(t).notesStartAt(time));
    }
    return output;
  }

  public List<Note> allEndsAt(int time) {
    List<Note> output = new ArrayList<>();
    for (Tone t : this.contents.keySet()) {
      output.addAll(this.contents.get(t).notesEndAt(time));
    }
    return output;
  }

  public List<Tone> getRange() {
    return this.contents.firstKey().toneRange(this.contents.lastKey());
  }
}