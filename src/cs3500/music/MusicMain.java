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
import cs3500.music.view.IMusicViewFactory;

/**
 * A main class for the music player.
 */
public class MusicMain {

  /**
   * A static method to run the views of the music player.
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    String[] ars = new String[2];
    ars[0] = "songs/mary-little-lamb.txt";
    ars[1] = "midi";
    if (ars.length != 2) {
      System.out.println("Should only have two arguments");
      return;
    }
    try {
      ISong model = MusicReader.parseFile(new FileReader(ars[0]), new SongBuilder());
      IMusicView view = IMusicViewFactory.make(ars[1]);
      IMusicController controller = new MusicController(model, view);
      controller.go();
    } catch (IOException e) {
      System.out.println("Not a valid song.");
    } catch (IllegalArgumentException ie) {
      System.out.print("Not a valid view type.");
    }
  }
}


