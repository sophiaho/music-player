package cs3500.music;

import cs3500.music.controller.IMusicController;
import cs3500.music.controller.MusicController;
import cs3500.music.model.ISong;
import cs3500.music.model.Song;
import cs3500.music.view.GUIView;
import cs3500.music.view.IMusicView;

/**
 * Created by soapyho on 11/3/16.
 */
public class MusicMain {
  public static void main(String[] args) {
    ISong model = new Song();
    IMusicView view = new GUIView();
    IMusicController controller = new MusicController(model, view);
    controller.go();
  }
}

//TODO need to do the note parser

//TODO check out to see how ugly sophia's view is

