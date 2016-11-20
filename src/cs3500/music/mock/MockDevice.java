package cs3500.music.mock;

import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

/**
 * Mock for midi device.
 */
public class MockDevice implements MidiDevice  {
  /**
   * Constructor for Mock Device, does nothing since it has no fields.
   */
  public MockDevice() {
    // does not have any fields to initialize.
  }

  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return new MockReceiver(System.out);
  }

  @Override
  public Info getDeviceInfo() {
    return null;
  }

  @Override
  public void open() throws MidiUnavailableException {
    // does not do anything for mock.
  }

  @Override
  public void close() {
    // does not do anything for mock.
  }

  @Override
  public boolean isOpen() {
    return false;
  }

  @Override
  public long getMicrosecondPosition() {
    return 0;
  }

  @Override
  public int getMaxReceivers() {
    return 0;
  }

  @Override
  public int getMaxTransmitters() {
    return 0;
  }

  @Override
  public List<Receiver> getReceivers() {
    return null;
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Transmitter> getTransmitters() {
    return null;
  }
}
