package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * NoteSet class stores all the notes with the same tone, it extends TreeMap so that it's
 * sorted and can allow easy access for times.
 */
public class NoteSet implements Comparable<NoteSet> {
  private Tone set;
  private TreeMap<Integer, List<Note>> contents;
  private HashMap<Integer, Playing> isPlaying;

  NoteSet(Note note) {
    this.contents = new TreeMap<>();
    this.set = note.tone;
    this.isPlaying = new HashMap<>();
    this.addSafe(note);
  }

  /**
   * Adds a note if it's the same kind of tone.
   *
   * @param note note to add
   */
  public void addSafe(Note note) {
    if (!note.rightTone(set)) {
      throw new IllegalArgumentException("Can't add note with different tone.");
    } else if ((this.contents.keySet().contains(note.start)) &&
            (this.contents.get(note.start)).contains(note)) {
      throw new IllegalArgumentException("Can't add duplicate notes.");
    } else if (this.contents.keySet().contains(note.start)) {
      this.contents.get(note.start).add(note);
      return;
    }
    this.contents.put(note.start, new ArrayList<>());
    this.contents.get(note.start).add(note);

    isPlaying.put(note.start, Playing.HEAD);
    for (int i = 1; i < note.duration; i++) {
      if (!isPlaying.keySet().contains(note.start + i)) {
        isPlaying.put(note.start + i, Playing.SUSTAIN);
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
    for (List<Note> n : this.contents.values()) {
      for (Note c : n) {
        d = Math.max(c.start + c.duration, d);
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
   * @return
   */
  public Tone getSet() {
    return this.set;
  }

  /**
   * return all notes that start at a given time.
   * @param time
   * @return
   */
  public List<Note> notesStartAt(int time) {
    if (!this.contents.keySet().contains(time)) {
      return new ArrayList<>();
    }
    return this.contents.get(time);
  }

  /**
   * Retruns all notes that end at a given time.
   * @param time
   * @return
   */
  public List<Note> notesEndAt(int time) {
    int currentTime = 0;
    ArrayList<Note> output = new ArrayList<>();

    while (currentTime <= time) {
      if (this.contents.keySet().contains(currentTime)) {
        for (Note n : this.contents.get(currentTime)) {
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
   * Removes a note n.
   * @param n
   */
  public void remove(Note n) {
    this.contents.get(n.start).remove(n);
  }
}