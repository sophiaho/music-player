package cs3500.music.adapter;

import java.util.HashMap;

import cs3500.music.model.ITone;

/**
 * Created by andrew on 01/12/2016.
 */
public class PitchAdapter implements Pitch {
  private cs3500.music.model.Pitch old;

  public PitchAdapter(cs3500.music.model.Pitch p) {
    this.old = p;
  }

  @Override
  public int ordinal() {
    return old.getNoteValue();
  }
}
