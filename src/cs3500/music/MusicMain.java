package cs3500.music;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.adapter.GuiViewAdapter;
import cs3500.music.controller.GuiController;
import cs3500.music.controller.IMusicController;
import cs3500.music.model.ISong;
import cs3500.music.provider.ComboView;
import cs3500.music.provider.GuiViewFrame;
import cs3500.music.provider.IGuiView;
import cs3500.music.provider.IView;
import cs3500.music.provider.MidiView;
import cs3500.music.util.MusicReader;
import cs3500.music.util.SongBuilder;
import cs3500.music.view.CompositeView;

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
//    if (args.length != 1) {
//      System.out.println("Should only have one argument");
//      return;
//    }
    try {
      ISong model = MusicReader.parseFile(new FileReader("songs/mary-little-lamb.txt"), new SongBuilder());
      //      IGUIView view = IMusicViewFactory.make(args[1]);
      IGuiView view = new ComboView(new MidiView(), new GuiViewFrame());
      IView midi = new MidiView();
      IMusicController controller = new GuiController(model, new CompositeView());
      controller.start();
    } catch (IOException e) {
      System.out.println("Not a valid song.");
    } catch (IllegalArgumentException ie) {
      ie.printStackTrace();
    }
  }
}
