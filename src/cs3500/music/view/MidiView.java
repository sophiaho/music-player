package cs3500.music.view;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
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
 * Plays the song.
 */
public class MidiView extends GUIView {
  private Receiver receiver;
  private static final int OFFSET = 0;
  private Sequencer sequencer;
  private Sequence sequence;
  private int songLength;

  /**
   * Constructor for MidiView, starts the sequencer.
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
      tryRec = null;
    }
    this.receiver = tryRec;

    Sequencer tempSequencer = null;
    Sequence tempSequence = null;

    try {
      tempSequencer = MidiSystem.getSequencer();
      tempSequence = new Sequence(Sequence.PPQ, 20);
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

    Track song = this.sequence.createTrack();

    for (int i = 0; i <= this.panel.getLength(); i++) {
      try {
        if (this.panel.getStarts().containsKey(i)) {
          List<INote> startsNow = this.panel.getStarts().get(i);
          for (INote n : startsNow) {
            MidiEvent addStart = new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON,
                    n.getInstrument() - 1,
                    n.getMidi(), n.getVolume()), i * this.panel.getSQUARE());
            song.add(addStart);
          }
        }
        if (this.panel.getEnds().containsKey(i)) {
          List<INote> endsNow = this.panel.getEnds().get(i);
          for (INote n : endsNow) {
            MidiEvent addEnd = new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF,
                    n.getInstrument() - 1,
                    n.getMidi(), n.getVolume()), i * this.panel.getSQUARE() - 1);
            song.add(addEnd);
          }
        }
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }

    try {
      this.sequencer.open();
      this.sequencer.setTempoInMPQ(this.panel.getTempo());
      this.sequencer.start();
      this.songLength = this.panel.getLength();
      this.receiver = this.sequencer.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

  }

  /**
   * Sets what the receiver of this is from default. Used for mock testing.
   *
   * @param r whatever mock you want to write notes to.
   */
  public void setReceiver(Receiver r) {
    this.receiver = r;
  }

  @Override
  public void switchPP() {
    if (this.sequencer.isRunning()) {
      this.sequencer.stop();
    } else {
      this.sequencer.start();
    }
  }

  /**
   * Returns the time that midi is at right now from the sequencer.
   * @return int time in ticks.
   */
  public int getTick() {
    if (this.sequencer.getTickPosition() >= this.songLength * this.panel.getTempo()) {
      sequencer.close();
    }
    return (int) this.sequencer.getTickPosition();
  }

  /**
   * Restarts the whole midi from the beginning.
   */
  public void restart() {
    try {
      this.sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    this.sequencer.setTickPosition(0);
    this.sequencer.start();
  }
}
