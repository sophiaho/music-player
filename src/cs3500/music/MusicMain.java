package cs3500.music;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.IMusicController;
import cs3500.music.controller.MusicController;
import cs3500.music.model.ISong;
import cs3500.music.util.MusicReader;
import cs3500.music.util.SongBuilder;
import cs3500.music.view.IMusicView;
import cs3500.music.view.MidiView;

/**
 * A main class for the music player.
 */
public class MusicMain {
  public static void main(String[] args) throws IOException, InvalidMidiDataException{
    ISong model = MusicReader.parseFile(new FileReader("songs/zoot-lw.txt"), new SongBuilder());
    IMusicView view = new MidiView();
    IMusicController controller = new MusicController(model, view);
    controller.go();
  }
}


