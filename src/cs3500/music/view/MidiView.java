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
 * A class representation of the MidiView.
 */
public class MidiView extends GUIView {
  private Synthesizer synth;
  private Receiver receiver;
  private int OFFSET = 1000000;


  /**
   * A constructor for the MidiView.
   */
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

  /**
   * Renders the MidiView to be played.
   */
  public void render() {
    super.render();

    for (int i = 0; i < this.panel.getEnds().lastKey(); i++) {
      try {
        if (this.panel.getStarts().containsKey(i)) {
          List<INote> startsNow = this.panel.getStarts().get(i);
          for (INote n : startsNow) {
            this.receiver.send(new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument() - 1,
                    n.getMidi(), n.getVolume()), OFFSET + i * this.panel.getTempo());
          }
        } else if (this.panel.getEnds().containsKey(i)) {
          List<INote> endsNow = this.panel.getEnds().get(i);
          for (INote n : endsNow) {
            this.receiver.send(new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument() - 1,
                    n.getMidi(), n.getVolume()), OFFSET + (i * this.panel.getTempo()) - 1);
          }
        }
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
  }
}
