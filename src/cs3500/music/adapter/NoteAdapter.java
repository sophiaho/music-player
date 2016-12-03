package cs3500.music.adapter;

import cs3500.music.model.INote;

/**
 * Created by andrew on 01/12/2016.
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
    return toAdapt.getEnd();
  }

  @Override
  public Pitch getPitch() {
    return new PitchAdapter(toAdapt.getTone().getPitch());
  }

  @Override
  public int getOctave() {
    return toAdapt.getTone().getOctave();
  }

  @Override
  public int getVolume() {
    return toAdapt.getVolume();
  }

  @Override
  public int getInstrument() {
    return toAdapt.getInstrument();
  }

  @Override
  public int getOrdinal() {
    return toAdapt.getTone().numeric();
  }
}
