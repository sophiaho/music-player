package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * NoteSet class stores all the notes with the same tone, it extends TreeMap so that it's
 * sorted and can allow easy access for times.
 */
class NoteSet implements Comparable<NoteSet> {
  private ITone set;
  private TreeMap<Integer, List<INote>> contents;
  private HashMap<Integer, Playing> isPlaying;

  NoteSet(INote note) {
    this.contents = new TreeMap<>();
    this.set = note.getTone();
    this.isPlaying = new HashMap<>();
    this.addSafe(note);
  }

  /**
   * Adds a note if it's the same kind of tone.
   *
   * @param note note to add
   */
  public void addSafe(INote note) {
    if (!note.getTone().equals(set)) {
      throw new IllegalArgumentException("Can't add note with different tone.");
    } else if ((this.contents.keySet().contains(note.getStart())) &&
            (this.contents.get(note.getStart())).contains(note)) {
      throw new IllegalArgumentException("Can't add duplicate notes.");
    } else if (this.contents.keySet().contains(note.getStart())) {
      this.contents.get(note.getStart()).add(note);
      return;
    }
    this.contents.put(note.getStart(), new ArrayList<>());
    this.contents.get(note.getStart()).add(note);

    isPlaying.put(note.getStart(), Playing.HEAD);
    for (int i = 1; i < note.getDuration(); i++) {
      if (!isPlaying.keySet().contains(note.getStart() + i)) {
        isPlaying.put(note.getStart() + i, Playing.SUSTAIN);
      }
    }
  }

  /**
   * New compareto that compares notesets based on their tones.
   */
  public int compareTo(NoteSet noteset) {
    return this.set.compareTo(noteset.set);
  }

  /**
   * Returns what the max endtime of all the notes is.
   *
   * @return int for endtime
   */
  int endTime() {
    Integer d = 0;
    for (List<INote> n : this.contents.values()) {
      for (INote c : n) {
        d = Math.max(c.getEnd(), d);
      }
    }
    return d;
  }

  /**
   * Returns if the set is playing and what it is playing at a given time.
   *
   * @return the playing enum for what is playing at the given time
   */
  public Playing whatPlaying(int time) {
    if (isPlaying.keySet().contains(time)) {
      return isPlaying.get(time);
    } else {
      return Playing.REST;
    }
  }

  /**
   * Getter for the tone of this set.
   *
   * @return an ITone that gets the tone that the noteset corresponds to
   */
  public ITone getSet() {
    return this.set;
  }

  /**
   * return all notes that start at a given time.
   * @param time
   * @return a list of notes that start at a specific time.
   */
  public List<INote> notesStartAt(int time) {
    if (!this.contents.keySet().contains(time)) {
      return new ArrayList<>();
    }
    return this.contents.get(time);
  }

  /**
   * Retruns all notes that end at a given time.
   * @param time
   * @return a list of notes that end at a specific time
   */
  public List<INote> notesEndAt(int time) {
    int currentTime = 0;
    ArrayList<INote> output = new ArrayList<>();

    while (currentTime <= time) {
      if (this.contents.keySet().contains(currentTime)) {
        for (INote n : this.contents.get(currentTime)) {
          if (n.getEnd() == time) {
            output.add(n);
          }
        }
      }

      currentTime += 1;
    }

    return output;
  }

  /**
   * Removes a note n from the noteset.
   * @param n
   */
  public void remove(INote n) {
    this.contents.get(n.getStart()).remove(n);
  }
}