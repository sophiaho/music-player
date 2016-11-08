package cs3500.music;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.IMusicController;
import cs3500.music.controller.MusicController;
import cs3500.music.model.ISong;
import cs3500.music.util.MusicReader;
import cs3500.music.util.SongBuilder;
import cs3500.music.view.GUIView;
import cs3500.music.view.IMusicView;
import cs3500.music.view.MidiView;

/**
 * Created by soapyho on 11/3/16.
 */
public class MusicMain {
  public static void main(String[] args) throws IOException, InvalidMidiDataException{
    ISong model = MusicReader.parseFile(new FileReader("songs/mary-little-lamb.txt"), new SongBuilder());
    IMusicView view = new MidiView();
    IMusicController controller = new MusicController(model, view);
    controller.go();
  }
}

//TODO need to do the note parser

//TODO check out to see how ugly sophia's view is

