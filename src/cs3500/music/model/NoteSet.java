package cs3500.music.model;

import java.util.TreeMap;

/**
 * NoteSet class stores all the notes with the same tone, it extends TreeMap so that it's
 * sorted and can allow easy access for times.
 */
public class NoteSet extends TreeMap<Integer, Note> implements Comparable<NoteSet> {
  Tone set;

  NoteSet(Note note) {
    this.set = note.tone;
    this.addSafe(note);
  }

  /**
   * Adds a note if it's the same kind of tone.
   *
   * @param note note to add
   */
  public void addSafe(Note note) {
    if (note.rightTone(set)) {
      this.put(note.start, note);
    } else {
      throw new IllegalArgumentException("Can't add note with different tone.");
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
    if (this.containsKey(time)) {
      return Playing.HEAD;
    } else if (time == 0) {
      return Playing.REST;
    } else {
      for (int i : keySet()) {
        if ((get(i).start + get(i).duration > time)
                && (i < time)) {
          return Playing.SUSTAIN;
        }
      }
      return Playing.REST;
    }
  }
}