package cs3500.music.provider;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;


/**
 * mock receiver class.
 */
public class MockReceiver implements Receiver {

  private StringBuilder s;

  /**
   * constructor for a MockReciever.
   *
   * @param s String builder.
   */
  MockReceiver(StringBuilder s) {
    this.s = s;
  }

  /**
   * receives the commands as strings.
   *
   * @param message   the message for each note.
   * @param timeStamp when each note is played.
   */
  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage message1 = (ShortMessage) message;
    s.append("Command ").append(message1.getCommand());
    s.append("Channel ").append(message1.getChannel());
    s.append("Data 1 ").append(message1.getData1());
    s.append("Data 2 ").append(message1.getData2());
  }

  /**
   * does nothing. Is in interface.
   */
  @Override
  public void close() {
    //Do nothing.
  }

  /**
   * gets the stringBuilder.
   *
   * @return the stringBuilder.
   */
  public StringBuilder getStringBuilder() {
    return this.s;
  }
}
