package cs3500.music;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.GuiController;
import cs3500.music.controller.IMusicController;
import cs3500.music.controller.MusicController;
import cs3500.music.model.ISong;
import cs3500.music.util.MusicReader;
import cs3500.music.util.SongBuilder;
import cs3500.music.view.CompositeView;
import cs3500.music.view.IGUIView;
import cs3500.music.view.IMusicView;
import cs3500.music.view.IMusicViewFactory;

/**
 * Created by soapyho on 11/3/16.
 */
public class MusicMain {
  /**
   * Main public static void method, runs the music editor on the console.
   * @param args arguments from console.
   * @throws IOException when inputs are wrong and not in directory.
   * @throws InvalidMidiDataException when malformed midis are imported.
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
//    if (args.length != 2) {
//      System.out.println("Should only have two arguments");
//      return;
//    }
    try {
      ISong model = MusicReader.parseFile(new FileReader("songs/mystery-1.txt"), new SongBuilder());
//      IGUIView view = IMusicViewFactory.make("visual");
      IGUIView view = new CompositeView();
      IMusicController controller = new GuiController(model, view);
      controller.start();
    } catch (IOException e) {
      System.out.println("Not a valid song.");
    } catch (IllegalArgumentException ie) {
      System.out.print("Not a valid view type.");
    }
  }
}



