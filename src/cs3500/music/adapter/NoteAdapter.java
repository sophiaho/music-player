package cs3500.music.adapter;

import cs3500.music.model.INote;

/**
 * Implementation adapting INote to a Note.
 */
public class NoteAdapter implements Note {
  private INote toAdapt;

  public NoteAdapter(INote n) {
    this.toAdapt = n;
  }

  @Override
  public int getStartTime() {
    return toAdapt.getStart();
  }

  @Override
  public int getBeats() {
    return toAdapt.getEnd() - toAdapt.getStart();
  }

  @Override
  public Pitch getPitch() {
    return new PitchAdapter(toAdapt.getTone().getPitch());
  }

  @Override
  public int getOctave() {
    return toAdapt.getTone().getOctave() + 1;
  }

  @Override
  public int getVolume() {
    return toAdapt.getVolume();
  }

  @Override
  public int getInstrument() {
    return toAdapt.getInstrument();
  }
}
