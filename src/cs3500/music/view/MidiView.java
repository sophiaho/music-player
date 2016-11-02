package cs3500.music.view;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.Note;
import cs3500.music.model.Song;

/**
 * Created by andrew on 01/11/2016.
 */
public class MidiView implements IMusicView {
  private Synthesizer synth;
  private Receiver receiver;

  public void render(int beat, Song s) throws InvalidMidiDataException {
    List<Note> starts = s.allStartsAt(beat);
    List<Note> ends = s.allEndsAt(beat);

    for (Note n : starts) {
      this.receiver.send(new ShortMessage(ShortMessage.NOTE_ON, n.getInstrument(),
             n.getMidi(), n.getVolume()), beat);
    }

    for (Note n : ends) {
      this.receiver.send(new ShortMessage(ShortMessage.NOTE_OFF, n.getInstrument(),
              n.getMidi(), 0), beat);
    }
  }
}
