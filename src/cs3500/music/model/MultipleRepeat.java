package cs3500.music.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by andrew on 14/12/2016.
 */
public class MultipleRepeat {
  Set<Integer> ends;
  Set<MultipleRepeater> repeaters;
  Set<Integer> refSet;
  Set<Integer> starts;
  HashMap<Integer, Integer> refs;
  int lastEnd = -1;

  public MultipleRepeat() {
    this.ends = new HashSet<>();
    this.repeaters = new HashSet<>();
    this.refSet = new HashSet<>();
    this.refs = new HashMap<>();
    this.starts = new HashSet<>();
  }

  public void addRepeats(int start, int ref, List<Integer> ends) {
    MultipleRepeater toAdd = new MultipleRepeater(start, ref);
    toAdd.addEnds(ends);
    repeaters.add(toAdd);
    this.ends.addAll(ends);
    for (Integer i : ends) {
      refs.put(i, ref);
    }
    refSet.add(ref);
    this.starts.add(start);
  }

  public boolean isMidEnd(int i) {
    if (this.ends.contains(i)) {
      for (MultipleRepeater r : repeaters) {
        if (r.isEnd(i)) {
          lastEnd = i;
          return r.isMidEnd(i);
        }
      }
      return false;
    } else {
      return false;
    }
  }

  public boolean isRef(int i) {
    if (i >= 0) {
      return (this.refs.get(lastEnd) == i);
    } else {
      return false;
    }
  }
  public Set<Integer> getStarts() {
    return this.starts;
  }

  public Set<Integer> getEnds() {
    return ends;
  }

  public int nextEnd() {
    int i = lastEnd;
    if (i != -1 && this.ends.contains(i)) {
      for (MultipleRepeater r : repeaters) {
        if (r.isEnd(i)) {
          return r.nextEnd(i);
        }
      }
      throw new IllegalArgumentException("Not an end");
    } else {
      throw new IllegalArgumentException("Not an end");
    }
  }

  public boolean isAction(int i) {
    return (lastEnd >= 0 && (this.isRef(i)) || this.ends.contains(i));
  }

  public int multipleNext(int i) {
    if (lastEnd >= 0 && (this.ends.contains(i))) {
      return this.nextEnd();
    } else if (this.ends.contains(i)) {
      if (this.isMidEnd(i)) {
        lastEnd = i;
        return this.refs.get(i);
      } else {
        return i;
      }
    } else {
      return i;
    }
  }


  private class MultipleRepeater {
    int start;
    int ref;
    TreeMap<Integer, Boolean> ends;

    MultipleRepeater(int start, int ref) {
      this.start = start;
      this.ref = ref;
      this.ends = new TreeMap<>();
    }

    public void addEnds(List<Integer> endList) {
      for (Integer i : endList) {
        this.ends.put(i, false);
      }
    }

    public boolean isMidEnd(int i) {
      if (ends.containsKey(i) && !ends.get(i)) {
        ends.put(i, true);
        return true;
      } else {
        return false;
      }
    }

    public int nextEnd(int i) {
      return ends.higherKey(i + 1);
    }

    public boolean usedMidEnd(int i) {
      return ends.containsKey(i) && ends.get(i);
    }

    public int getStart() {
      return this.start;
    }

    public int getRef() {
      return this.ref;
    }

    public boolean isEnd(int i) {
      return this.ends.containsKey(i);
    }
  }
}
