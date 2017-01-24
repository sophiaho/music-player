package cs3500.music.model;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by andrew on 14/12/2016.
 */
public class Repeater {
  private HashMap<Integer, Boolean> rStarts;
  private HashMap<Integer, Boolean> rEnds;
  private HashMap<Integer, Integer> corrStart; // map from end to start
  private MultipleRepeat mrs;

  public Repeater() {
    this.rStarts = new HashMap<>();
    this.rEnds = new HashMap<>();
    this.corrStart = new HashMap<>();
    this.mrs = new MultipleRepeat();
  }

  private void addRepeat(int s, int e) {
    this.rStarts.put(s, false);
    this.rEnds.put(e, false);
    this.corrStart.put(e, s);
  }

  // Todo
  private boolean inEnds(int i) {
    return this.rEnds.containsKey(i);
  }

  public int corrStart(int e) {
    return this.corrStart.get(e);
  }

  public boolean isRepeat(int i) {
    if (rEnds.containsKey(i) && !rEnds.get(i)) {
      rEnds.put(i, true);
      return true;
    }
    else {
      return false;
    }
  }

  public void restartRepeats() {
    for (Integer i : rEnds.keySet()) {
      rEnds.put(i, false);
    }
  }

  public Set<Integer> basicRStarts() {
    return this.rStarts.keySet();
  }

  public Set<Integer> basicREnds() {
    return this.rEnds.keySet();
  }

  public boolean addSafeRepeat(int s, int e) {
    if (s <= e && !inEnds(e)) {
      addRepeat(s, e);
      return true;
    }
    return false;
  }

  public void addMultiple(int s, int ref, List<Integer> ends) {
    this.mrs.addRepeats(s, ref, ends);
  }

  public boolean multipleAction(int i) {
    return this.mrs.isAction(i);
  }

  public int multipleSet(int i) {
    return this.mrs.multipleNext(i);
  }

  public Set<Integer> mStarts() {
    return this.mrs.getStarts();
  }

  public Set<Integer> mEnds() {
    return this.mrs.getEnds();
  }
}
