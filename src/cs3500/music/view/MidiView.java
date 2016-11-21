package cs3500.music.view;

import java.nio.ByteBuffer;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;

import cs3500.music.model.INote;

/**
 * Created by andrew on 01/11/2016.
 */
public class MidiView extends GUIView {
  private Receiver receiver;
  private static final int OFFSET = 1;
  private final Sequencer sequencer;
  private Sequence sequence;

  public MidiView() {
    Synthesizer trySynth;
    Receiver tryRec;
    try {
      trySynth = MidiSystem.getSynthesizer();
      tryRec = trySynth.getReceiver();
      trySynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
      tryRec = null;
    }
    this.receiver = tryRec;

    Sequencer tempSequencer = null;
    Sequence tempSequence = null;

    try {
      tempSequencer = MidiSystem.getSequencer();
      tempSequence = new Sequence(Sequence.PPQ, 4);
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.sequencer = tempSequencer;
    this.sequence = tempSequence;

    try {
      this.sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void render() {
    //super.render();

    this.sequencer.setTempoInMPQ(this.panel.getTempo());
    Track song = this.sequence.createTrack();

    for (int i = 0; i < this.panel.getLength(); i++) {
      try {
        if (this.panel.getStarts().containsKey(i)) {
          List<INote> startsNow = this.panel.getStarts().get(i);
          for (INote n : startsNow) {
            MidiEvent addStart = new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument() - 1,
                    n.getMidi(), n.getVolume()), i);
            song.add(addStart);
          }
        }
        if (this.panel.getEnds().containsKey(i)) {
          List<INote> endsNow = this.panel.getEnds().get(i);
          for (INote n : endsNow) {
            MidiEvent addEnd = new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument() - 1,
                    n.getMidi(), n.getVolume()), i - 1);
            song.add(addEnd);
          }
        }
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }

    try {
      for (int n = 0; n < panel.getLength(); n++) {
        byte[] bytes = ByteBuffer.allocate(4).putInt(n).array();
        MidiEvent buffer = new MidiEvent(
                new MetaMessage(1, ByteBuffer.allocate(4).putInt(n).array(), bytes.length), n);
        song.add(buffer);
      }

      this.sequencer.open();
      sequencer.start();
      System.out.print("hi");
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }

  }

  public void pause() {
    this.sequencer.stop();
  }

  public void restart() {
    this.sequencer.setTempoInMPQ(this.panel.getTempo());
    this.sequencer.start();
  }

  /**
   * Sets what the receiver of this is from default. Used for mock testing.
   * @param r whatever mock you want to write notes to.
   */
  public void setReceiver(Receiver r) {
    this.receiver = r;
  }
}
