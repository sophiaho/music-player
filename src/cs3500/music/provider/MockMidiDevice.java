package cs3500.music.provider;

import java.util.List;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Receiver;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.midi.VoiceStatus;


/**
 * mock midi class.
 */
class MockMidiDevice implements Synthesizer {

  private StringBuilder s;

  /**
   * constructor for a MockMidiDevice.
   *
   * @param s the string builder.
   */
  MockMidiDevice(StringBuilder s) {
    this.s = s;
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public int getMaxPolyphony() {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public long getLatency() {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public MidiChannel[] getChannels() {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public VoiceStatus[] getVoiceStatus() {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @param soundbank is required from the interface.
   * @return throws error.
   */
  @Override
  public boolean isSoundbankSupported(Soundbank soundbank) {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @param instrument is required from the interface.
   * @return throws exception.
   */
  @Override
  public boolean loadInstrument(Instrument instrument) {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @param instrument is required from the interface.
   */
  @Override
  public void unloadInstrument(Instrument instrument) {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @param from is required from the interface.
   * @param to   is required from the interface.
   * @return throws an error.
   */
  @Override
  public boolean remapInstrument(Instrument from, Instrument to) {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @return throws exception.
   */
  @Override
  public Soundbank getDefaultSoundbank() {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @return throws exception.
   */
  @Override
  public Instrument[] getAvailableInstruments() {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @return throws exception.
   */
  @Override
  public Instrument[] getLoadedInstruments() {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @param soundbank is required from the interface.
   * @return throws exception.
   */
  @Override
  public boolean loadAllInstruments(Soundbank soundbank) {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @param soundbank is required from the interface.
   */
  @Override
  public void unloadAllInstruments(Soundbank soundbank) {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @param soundbank is required from the interface.
   * @param patchList is required from the interface.
   */
  @Override
  public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @return throws exception.
   */
  @Override
  public Info getDeviceInfo() {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   */
  @Override
  public void open() throws MidiUnavailableException {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   */
  @Override
  public void close() {
    throw new UnsupportedOperationException();
  }

  /**
   * is required from the interface.
   *
   * @return throws exception.
   */
  @Override
  public boolean isOpen() {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public long getMicrosecondPosition() {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public int getMaxReceivers() {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public int getMaxTransmitters() {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public MockReceiver getReceiver() throws MidiUnavailableException {
    return new MockReceiver(s);
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public List<Receiver> getReceivers() {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    throw new UnsupportedOperationException();
  }

  /**
   * throws exception.
   *
   * @return throws exception.
   */
  @Override
  public List<Transmitter> getTransmitters() {
    throw new UnsupportedOperationException();
  }
}
