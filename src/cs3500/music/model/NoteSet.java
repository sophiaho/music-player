package cs3500.music.model;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * NoteSet class stores all the notes with the same tone, it extends TreeMap so that it's
 * sorted and can allow easy access for times.
 */
public class NoteSet extends TreeMap<Integer, Note> implements Comparable<NoteSet> {
  Tone set;
  private HashMap<Integer, Playing> isPlaying;

  NoteSet(Note note) {
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
    } else if ((this.keySet().contains(note.start)) &&
            (note.equals(this.get(note.start)))) {
      throw new IllegalArgumentException("Can't add duplicate notes.");
    }
    this.put(note.start, note);

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
    for (Note n : this.values()) {
      d = Math.max(n.start + n.duration, d);
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
}