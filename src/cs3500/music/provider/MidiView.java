package cs3500.music.provider;

import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

import cs3500.music.adapter.IMusicEditorModelView;
import cs3500.music.adapter.Note;

/**
 * class for a midi view.
 */
public class MidiView implements IView {
  private IMusicEditorModelView model;
  private Sequencer sequencer;
  private Sequence seq;
  private Track track;
  private ArrayList<Integer> instruments = new ArrayList<Integer>();
  private MockMidiDevice fakeSynth;
  private boolean isPaused;

  /**
   * constructor for a midi view.
   */
  public MidiView() {
    try {
      this.sequencer = MidiSystem.getSequencer();
      this.seq = new Sequence(Sequence.PPQ, 1);
      this.track = seq.createTrack();
      sequencer.open();
    } catch (MidiUnavailableException | InvalidMidiDataException e) {
      e.printStackTrace();
    }
    this.isPaused = false;
  }

  /**
   * constructor for a midi view (convenience).
   *
   * @param s string builder.
   */
  public MidiView(StringBuilder s) {
    try {
      this.sequencer = MidiSystem.getSequencer();
      this.seq = new Sequence(Sequence.PPQ, 1);
      this.track = seq.createTrack();
      sequencer.open();
      Transmitter seqTrans = sequencer.getTransmitter();
      fakeSynth = new MockMidiDevice(s);
      Receiver synthReceiver = fakeSynth.getReceiver();
      seqTrans.setReceiver(synthReceiver);
    } catch (MidiUnavailableException | InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  /**
   * creates the file for the midi.
   */
  private void addFile() throws InvalidMidiDataException {
    for (Note n : model.getPiece().getPiece()) {
      int pitch = n.getPitch().ordinal() + (12 * n.getOctave());
      int volume = n.getVolume();
      Integer instrument = n.getInstrument();


      int channel = 0;
      for (int i = 0; i < instruments.size(); i++) {
        if (instruments.get(i).equals(instrument)) {
          channel = i;
        }
      }
      MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, channel, pitch, volume);
      MidiEvent noteStart = new MidiEvent(start, n.getStartTime());
      MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, channel, pitch, volume);
      MidiEvent noteStop = new MidiEvent(stop, n.getStartTime() + n.getBeats());

      this.track.add(noteStart);
      this.track.add(noteStop);
    }
  }

  /**
   * sets the intruments.
   */
  private void setInstruments() {
    for (Note n : model.getPiece().getPiece()) {
      boolean contain = false;
      for (Integer i : instruments) {
        if (i == n.getInstrument()) {
          contain = true;
        }
      }
      if (!contain) {
        instruments.add(n.getInstrument());
      }
    }

    for (int i = 0; i < instruments.size(); i++) {
      try {
        MidiMessage message = new ShortMessage(ShortMessage.PROGRAM_CHANGE,
                i, instruments.get(i) - 1, 0);
        this.track.add(new MidiEvent(message, 0));
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }

  }

  /**
   * starts the sequencer.
   */
  @Override
  public void makeVisible() {
    this.sequencer.start();
  }

  /**
   * refreshes the notes being played.
   */
  @Override
  public void refresh() {
    try {
      this.setInstruments();
      this.addFile();
      this.sequencer.setSequence(this.seq);
      this.sequencer.setTempoInMPQ(this.model.getTempo());
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  /**
   * sets the model for this view.
   *
   * @param modelView the view being passed.
   */
  @Override
  public void setModel(IMusicEditorModelView modelView) {
    this.model = modelView;
  }


  /**
   * gets the instruments.
   *
   * @return the list of instruments.
   */
  public ArrayList<Integer> getInstruments() {
    return this.instruments;
  }

  /**
   * gets the sequence.
   *
   * @return this.seq.
   */
  public Sequence getSeq() {
    return this.seq;
  }

  /**
   * gets the track.
   *
   * @return this.track.
   */
  public Track getTrack() {
    return this.track;
  }

  /**
   * returns the fake synthesizer.
   *
   * @return the fake synthesizer.
   */
  public MockMidiDevice getSynthesizer() {
    return this.fakeSynth;
  }

  /**
   * gets this view's sequencer.
   *
   * @return the sequencer.
   */
  public Sequencer getSequencer() {
    return this.sequencer;
  }

  /**
   * pauses the midi player.
   */
  void pause() {
    if (isPaused) {
      this.sequencer.start();
      isPaused = false;
    } else {
      this.sequencer.stop();
      isPaused = true;
    }
    this.sequencer.setTempoInMPQ(this.model.getTempo());
  }

  /**
   * restarts the song.
   */
  void restart() {
    this.sequencer.setTickPosition(0);
    this.sequencer.setTempoInMPQ(this.model.getTempo());
    this.sequencer.start();
    this.isPaused = false;
  }

  /**
   * adds meta events to the track so that the gui view can be updated as the midi song plays.
   * @param listener contains the action to be performed every time there is a metaMessage.
   */
  void addMetas(MetaEventListener listener) {
    for (int i = 0; i < this.model.getPiece().getMaxBeat(); i++) {
      this.track.add(new MidiEvent(new MetaMessage(), i));
    }
    this.sequencer.addMetaEventListener(listener);
  }


}
