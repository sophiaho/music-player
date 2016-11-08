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
 * Created by soapyho on 11/3/16.
 */
public class MusicMain {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    if (args.length != 2) {
      System.out.println("Should only have two arguments");
      return;
    }
    try {
      ISong model = MusicReader.parseFile(new FileReader(args[0]), new SongBuilder());
      IMusicView view = IMusicViewFactory.make(args[1]);
      IMusicController controller = new MusicController(model, view);
      controller.go();
    } catch (IOException e) {
      System.out.println("Not a valid song.");
    } catch (IllegalArgumentException ie) {
      System.out.print("Not a valid view type.");
    }
  }
}

//TODO need to do the note parser

//TODO check out to see how ugly sophia's view is

