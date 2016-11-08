package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Class for Song, main music model.
 */
public class Song implements ISong {
  private TreeMap<ITone, NoteSet> contents;
  private int tempo;

  public Song() { // CHANGE: made public
    this.contents = new TreeMap<>();
    this.tempo = 0;
  }

  public Song(int tempo) {
    this.contents = new TreeMap<>();
    this.tempo = tempo;
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

    // TODO check to see if the new method works the way you want it to and then you can just delete this part and change it
    int totalLength = 0;
    for (NoteSet n : this.contents.values()) {
      totalLength = Math.max(totalLength, n.endTime());
    }
    int indexDigits = (int) Math.log10(totalLength) + 1;

    List<ITone> toneSet = this.getRange();

    String output = header(indexDigits, toneSet);
    output += songVis(totalLength, indexDigits, toneSet);

    return output;
  }

  /**
   * Adds a note to the song.
   * @param n note to add to song.
   */
  @Override
  public void addNote(INote n) {
    if (this.contents.keySet().contains(n.getTone())) {
      this.contents.get(n.getTone()).addSafe(n);
    } else {
      this.contents.put(n.getTone(), new NoteSet(n));
    }
  }

  /**
   * Deletes a note.
   * @param n note to add to song.
   */
  @Override
  public void deleteNote(INote n) {
    if (this.contents.keySet().contains(n.getTone())) {
      this.contents.get(n.getTone()).remove(n);
    } else {
      throw new IllegalArgumentException("Song does not contain that note.");
    }
  }

  /**
   * Returns the header of the song state (needed notes).
   *
   * @param indexDigits number of digits needed for padding
   * @param toneSet     set of tones to write out
   * @return the notes in the song
   */
  private String header(int indexDigits, List<ITone> toneSet) {
    String output = String.format("%" + indexDigits + "s", "");
    for (ITone t : toneSet) {
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
  private String songVis(int totalLength, int indexDigits, List<ITone> toneSet) {
    String output = "";
    int ind = 0;
    for (int i = 0; i < totalLength; i++) {
      output += String.format("%" + indexDigits + "s", String.valueOf(ind)) + " ";
      for (ITone t : toneSet) {
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
   * //TODO write these javadocs
   */
  private List<INote> allStartsAt(int time) {
    List<INote> output = new ArrayList<>();
    for (ITone t : this.contents.keySet()) {
      output.addAll(this.contents.get(t).notesStartAt(time));
    }
    return output;
  }

  private List<INote> allEndsAt(int time) {
    List<INote> output = new ArrayList<>();
    for (ITone t : this.contents.keySet()) {
      output.addAll(this.contents.get(t).notesEndAt(time));
    }
    return output;
  }

  @Override
  public List<ITone> getRange() {
    return this.contents.firstKey().toneRange(this.contents.lastKey());
  }

  /**
   * Gets the length of the song.
   *
   * @return int of the position
   */
  public int songLength() {
    int totalLength = 0;
    for (NoteSet n : this.contents.values()) {
      totalLength = Math.max(totalLength, n.endTime());
    }
    return totalLength;
  }

  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  public TreeMap<Integer, List<INote>> starts() {
    TreeMap<Integer, List<INote>> output = new TreeMap<>();
    for (int i = 0; i <= this.songLength(); i++) {
      List<INote> s = this.allStartsAt(i);
      if (s.size() > 0) {
        output.put(i, s);
      }
    }
    return output;
  }

  @Override
  public TreeMap<Integer, List<INote>> ends() {
    TreeMap<Integer, List<INote>> output = new TreeMap<>();
    for (int i = 0; i <= this.songLength(); i++) {
      List<INote> s = this.allEndsAt(i);
      if (s.size() > 0) {
        output.put(i, s);
      }
    }
    return output;
  }

  public int getTempo() {
    return this.tempo;
  }
}