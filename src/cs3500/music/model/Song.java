package cs3500.music.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Song class stores all the notes with the same tone, it extends TreeMap so that it's
 * sorted and can allow easy access for times.
 */
public class Song implements ISong {
  private HashMap<Integer, List<INote>> contents;
  private int tempo;
  private ITone lowest;
  private ITone highest;
  private Repeater rep;

  public Song() {
    this.contents = new HashMap<>();
    this.tempo = 0;this.rep = new Repeater();
  }

  public Song(int tempo) {
    this.contents = new HashMap<>();
    this.tempo = tempo;
  }

  @Override
  public void addNote(INote note) {
    if (lowest == null && highest == null) {
      this.lowest = note.getTone();
      this.highest = note.getTone();
    }
    if (lowest.compareTo(note.getTone()) > 0) {
      this.lowest = note.getTone();
    }
    if (highest.compareTo(note.getTone()) < 0) {
      this.highest = note.getTone();
    }


    if ((this.contents.keySet().contains(note.getStart())) &&
            (this.contents.get(note.getStart())).contains(note)) {
      throw new IllegalArgumentException("Can't add duplicate notes.");
    } else if (!this.contents.keySet().contains(note.getStart())) {
      this.contents.put(note.getStart(), new ArrayList<>());
    }

    this.contents.get(note.getStart()).add(note);
  }

  @Override
  public int songLength() {
    Integer d = 0;
    for (List<INote> n : this.contents.values()) {
      for (INote c : n) {
        d = Math.max(c.getEnd(), d);
      }
    }
    return d;
  }

  /**
   * return all notes that start at a given time.
   *
   * @param time time wanted the start notes at in beats.
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
   *
   * @param time time of the notes in beats.
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


  @Override
  public void deleteNote(INote n) {
    // linear time delete allows for constant access and constant add,
    // since likely it is going to be the least used of the three.
    if (this.highest == n.getTone()) {
      for (Integer i : this.contents.keySet()) {
        for (INote toCheck : this.contents.get(i)) {
          if (highest.compareTo(toCheck) < 0) {
            highest = toCheck.getTone();
          }
        }
      }
    } else if (this.lowest == n.getTone()) {
      for (Integer i : this.contents.keySet()) {
        for (INote toCheck : this.contents.get(i)) {
          if (lowest.compareTo(toCheck) > 0) {
            lowest = toCheck.getTone();
          }
        }
      }
    }

    if (this.contents.get(n.getStart()).contains(n)) {
      this.contents.get(n.getStart()).remove(n);
    } else {
      throw new IllegalArgumentException("Note not in song.");
    }
  }

  @Override
  public HashMap<Integer, List<INote>> starts() {
    HashMap<Integer, List<INote>> output = new HashMap<>();
    for (int i = 0; i <= this.songLength(); i++) {
      List<INote> s = this.notesStartAt(i);
      if (s.size() > 0) {
        output.put(i, s);
      }
    }
    return output;
  }

  @Override
  public HashMap<Integer, List<INote>> ends() {
    HashMap<Integer, List<INote>> output = new HashMap<>();
    for (int i = 0; i <= this.songLength(); i++) {
      List<INote> s = this.notesEndAt(i);
      if (s.size() > 0) {
        output.put(i, s);
      }
    }
    return output;
  }

  @Override
  public List<ITone> getRange() {
    if (this.lowest == null) {
      throw new IllegalArgumentException("No notes yet.");
    } else {
      return this.lowest.toneRange(this.highest);
    }
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public void deleteNoteAtX(ITone tone, int time) {
    for (int i = 0; i < time; i++) {
      if (this.contents.keySet().contains(i)) {
        for (INote n : this.contents.get(i)) {
          if (n.getTone().equals(tone)
                  && n.getStart() <= time
                  && n.getEnd() >= time) {
            this.deleteNote(n);
          }
        }
      }
    }
  }

  @Override
  public Set<Integer> rStarts() {
    return this.rep.basicRStarts();
  }

  @Override
  public Set<Integer> rEnds() {
    return this.rep.basicREnds();
  }

  @Override
  public int corrStart(int i) {
    return this.rep.corrStart(i);
  }

  @Override
  public void restartRepeats() {
    this.rep.restartRepeats();
  }

  @Override
  public boolean isRepeat(int i) {
    return this.rep.isRepeat(i);
  }

  @Override
  public boolean addSafeBasicRepeat(int s, int e) {
    return rep.addSafeRepeat(s, e);
  }

  @Override
  public boolean multipeAction(int i) {
    return this.rep.multipleAction(i);
  }

  @Override
  public int multipleSet(int i) {
    return this.rep.multipleSet(i);
  }

  @Override
  public void addMultipleRepeat(int s, int r, List<Integer> ends) {
    this.rep.addMultiple(s, r, ends);
  }

  @Override
  public Set<Integer> mStarts() {
    return this.rep.mStarts();
  }

  @Override
  public Set<Integer> mEnds() {
    return this.rep.mEnds();
  }
}