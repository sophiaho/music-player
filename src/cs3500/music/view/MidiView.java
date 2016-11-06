package cs3500.music.view;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.ISong;
import cs3500.music.model.INote;

/**
 * Created by andrew on 01/11/2016.
 */
public class MidiView implements IMusicView {
  private Synthesizer synth;
  private Receiver receiver;

  public MidiView() {
    Synthesizer trySynth;
    Receiver tryRec;
    try {
      trySynth = MidiSystem.getSynthesizer();
      tryRec = trySynth.getReceiver();
      trySynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
      trySynth = null;
      tryRec = null;
    }
    this.synth = trySynth;
    this.receiver = tryRec;
  }

  public void render(int beat, ISong s) throws InvalidMidiDataException {
    List<INote> starts = s.allStartsAt(beat);
    List<INote> ends = s.allEndsAt(beat);

    for (INote n : starts) {
      this.receiver.send(new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument(),
              n.getMidi(), n.getVolume()), beat);
    }

    for (INote n : ends) {
      this.receiver.send(new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument(),
              n.getMidi(), 0), beat);
    }
  }

  @Override
  public void initialize() {
    //TODO
  }
}
