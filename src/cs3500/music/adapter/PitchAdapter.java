package cs3500.music.adapter;

/**
 * Adapts old Pitch to provider's Pitch.
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

  @Override
  public String toString() {
    return old.toString();
  }
}
