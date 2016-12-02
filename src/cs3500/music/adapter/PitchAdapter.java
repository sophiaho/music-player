package cs3500.music.adapter;

import cs3500.music.model.ITone;

/**
 * Created by andrew on 01/12/2016.
 */
public class PitchAdapter implements Pitch {
  private ITone tone;

  public PitchAdapter(ITone t) {
    this.tone = t;
  }

  @Override
  public int ordinal() {
    return tone.numeric();
  }
}
