package cs3500.music;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.GuiController;
import cs3500.music.controller.IMusicController;
import cs3500.music.model.ISong;
import cs3500.music.util.MusicReader;
import cs3500.music.util.SongBuilder;
import cs3500.music.mock.MockReceiver;
import cs3500.music.view.IMusicView;
import cs3500.music.view.IMusicViewFactory;
import cs3500.music.view.MidiView;

/**
 * Test suite for Midi Main.
 */
public class MidiTest {
  @Test
  public void testMidi() {
    // uses mock to check if midi is being played
    Appendable ap = new StringBuilder();
    try {
      ISong model = MusicReader.parseFile(new FileReader("songs/mary-little-lamb.txt"),
              new SongBuilder());
      MidiView view = new MidiView();
      view.setReceiver(new MockReceiver(ap));
      IMusicController controller = new GuiController(model, view);
      controller.start();
    } catch (IOException e) {
      System.out.println("Not a valid song.");
    } catch (IllegalArgumentException ie) {
      System.out.print("Not a valid view type.");
    }
    assertEquals("note 144 0  56 70\n" +
            "note 144 0  65 72\n" +
            "note 144 0  63 72\n" +
            "note 144 0  61 71\n" +
            "note 144 0  63 79\n" +
            "note 128 0  56 70\n" +
            "note 144 0  56 79\n" +
            "note 144 0  65 85\n" +
            "note 144 0  65 78\n" +
            "note 144 0  65 74\n" +
            "note 128 0  56 79\n" +
            "note 128 0  65 74\n" +
            "note 144 0  56 77\n" +
            "note 144 0  63 75\n" +
            "note 144 0  63 77\n" +
            "note 144 0  63 75\n" +
            "note 144 0  56 79\n" +
            "note 144 0  65 82\n" +
            "note 144 0  68 84\n" +
            "note 144 0  68 75\n" +
            "note 144 0  56 78\n" +
            "note 144 0  65 73\n" +
            "note 144 0  63 69\n" +
            "note 144 0  61 71\n" +
            "note 144 0  63 80\n" +
            "note 144 0  56 79\n" +
            "note 144 0  65 84\n" +
            "note 144 0  65 76\n" +
            "note 144 0  65 74\n" +
            "note 144 0  65 77\n" +
            "note 144 0  56 78\n" +
            "note 144 0  63 75\n" +
            "note 144 0  63 74\n" +
            "note 144 0  65 81\n" +
            "note 144 0  63 70\n" +
            "note 144 0  53 72\n" +
            "note 144 0  61 73\n", ap.toString());
  }

  @Test
  public void textTest() {
    // checks if text is being outputted
    OutputStream out = new ByteArrayOutputStream();
    PrintStream p = new PrintStream(out);
    System.setOut(p);
    try {
      ISong model = MusicReader.parseFile(new FileReader("songs/mary-little-lamb.txt"),
              new SongBuilder());
      IMusicView text = IMusicViewFactory.make("console");
      IMusicController controller = new GuiController(model, text);
      controller.start();
    } catch (IOException e) {
      System.out.println("Not a valid song.");
    } catch (IllegalArgumentException ie) {
      System.out.print("Not a valid view type.");
    }
    assertEquals(
            "     E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  " +
                    "C#4   D4  D#4   E4   F4  F#4   G4\n" +
            " 0                  X                              " +
                    "              X                 \n" +
            " 1                  |                              " +
                    "              |                 \n" +
            " 2                  |                               " +
                    "   X                           \n" +
            " 3                  |                                 " +
                    " |                           \n" +
            " 4                  |                        X      " +
                    "                               \n" +
            " 5                  |                        |        " +
                    "                             \n" +
            " 6                  |                               " +
                    "   X                           \n" +
            " 7                                                   " +
                    "  |                           \n" +
            " 8                  X                                       " +
                    "     X                 \n" +
            " 9                  |                              " +
                    "              |                 \n" +
            "10                  |                                    " +
                    "        X                 \n" +
            "11                  |                                    " +
                    "        |                 \n" +
            "12                  |                                    " +
                    "        X                 \n" +
            "13                  |                                    " +
                    "        |                 \n" +
            "14                  |                                   " +
                    "         |                 \n" +
            "15                                                      " +
                    "                           \n" +

            "16                  X                                  X  " +
                    "                         \n" +
            "17                  |                                  |  " +
                    "                         \n" +
            "18                  |                                  X    " +
                    "                       \n" +
            "19                  |                                  |     " +
                    "                      \n" +
            "20                  |                                  X     " +
                    "                      \n" +
            "21                  |                                  |     " +
                    "                      \n" +
            "22                  |                                  |    " +
                    "                       \n" +
            "23                  |                                  |    " +
                    "                       \n" +
            "24                  X                                       " +
                    "     X                 \n" +
            "25                  |                                       " +
                    "     |                 \n" +
            "26                                                            " +
                    "                  X  \n" +
            "27                                                             " +
                    "                 |  \n" +
            "28                                                            " +
                    "                  X  \n" +
            "29                                                           " +
                    "                   |  \n" +
            "30                                                           " +
                    "                   |  \n" +
            "31                                                           " +
                    "                   |  \n" +
            "32                  X                                        " +
                    "    X                 \n" +
            "33                  |                                       " +
                    "     |                 \n" +
            "34                  |                                  X    " +
                    "                       \n" +
            "35                  |                                  |    " +
                    "                       \n" +
            "36                  |                        X              " +
                    "                       \n" +
            "37                  |                        |              " +
                    "                       \n" +
            "38                  |                                  X     " +
                    "                      \n" +
            "39                  |                                  |          " +
                    "                 \n" +
            "40                  X                                       " +
                    "     X                 \n" +
            "41                  |                                            |  " +
                    "               \n" +
            "42                  |                                            X   " +
                    "              \n" +
            "43                  |                                            |   " +
                    "              \n" +
            "44                  |                                            X  " +
                    "               \n" +
            "45                  |                                            |  " +
                    "               \n" +
            "46                  |                                            X     " +
                    "            \n" +
            "47                  |                                            |  " +
                    "               \n" +
            "48                  X                                  X              " +
                    "             \n" +
            "49                  |                                  |               " +
                    "            \n" +
            "50                  |                                  X                " +
                    "           \n" +
            "51                  |                                  |                 " +
                    "          \n" +
            "52                  |                                            X       " +
                    "          \n" +
            "53                  |                                            |      " +
                    "           \n" +
            "54                  |                                  X                " +
                    "           \n" +
            "55                  |                                  |                 " +
                    "          \n" +
            "56   X                                       X                          " +
                    "           \n" +
            "57   |                                       |                              " +
                    "       \n" +
            "58   |                                       |                             " +
                    "        \n" +
            "59   |                                       |                      " +
                    "               \n" +
            "60   |                                       |                    " +
                    "                 \n" +
            "61   |                                       |                    " +
                    "                 \n" +
            "62   |                                       |                    " +
                    "                 \n" +
            "63   |                                       |                   " +
                    "                  \n",
            out.toString());
  }

  @Test
  public void testBadArgs() {
    // checks if it handles not having the right number of arguments
    OutputStream out = new ByteArrayOutputStream();
    PrintStream p = new PrintStream(out);
    System.setOut(p);

    try {
      MusicMain.main(new String[]{"songs/mary-little-lamb.txt"});
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    } catch (IOException ie) {
      ie.printStackTrace();
    }

    assertEquals("Should only have two arguments\n", out.toString());
  }

  @Test
  public void testWrongViewArgs() throws IOException, InvalidMidiDataException {
    // checks for if it handles having the wrong view type
    OutputStream out = new ByteArrayOutputStream();
    PrintStream p = new PrintStream(out);
    System.setOut(p);

    try {
      MusicMain.main(new String[]{"songs/mary-little-lamb.txt", "midis"});
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    } catch (IOException ie) {
      ie.printStackTrace();
    }

    assertEquals("Not a valid view type.", out.toString());
  }

  @Test
  public void testGoodMain() {
    // tests a good usage of the main function
    OutputStream out = new ByteArrayOutputStream();
    PrintStream p = new PrintStream(out);
    System.setOut(p);

    try {
      MusicMain.main(new String[]{"songs/mary-little-lamb.txt", "console"});
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    } catch (IOException ie) {
      ie.printStackTrace();
    }

    assertEquals("     E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  " +
            "C#4   D4  D#4   E4   F4  F#4   G4\n" +
                    " 0                  X                              " +
                    "              X                 \n" +
                    " 1                  |                              " +
                    "              |                 \n" +
                    " 2                  |                               " +
                    "   X                           \n" +
                    " 3                  |                                 " +
                    " |                           \n" +
                    " 4                  |                        X      " +
                    "                               \n" +
                    " 5                  |                        |        " +
                    "                             \n" +
                    " 6                  |                               " +
                    "   X                           \n" +
                    " 7                                                   " +
                    "  |                           \n" +
                    " 8                  X                                       " +
                    "     X                 \n" +
                    " 9                  |                              " +
                    "              |                 \n" +
                    "10                  |                                    " +
                    "        X                 \n" +
                    "11                  |                                    " +
                    "        |                 \n" +
                    "12                  |                                    " +
                    "        X                 \n" +
                    "13                  |                                    " +
                    "        |                 \n" +
                    "14                  |                                   " +
                    "         |                 \n" +
                    "15                                                      " +
                    "                           \n" +

                    "16                  X                                  X  " +
                    "                         \n" +
                    "17                  |                                  |  " +
                    "                         \n" +
                    "18                  |                                  X    " +
                    "                       \n" +
                    "19                  |                                  |     " +
                    "                      \n" +
                    "20                  |                                  X     " +
                    "                      \n" +
                    "21                  |                                  |     " +
                    "                      \n" +
                    "22                  |                                  |    " +
                    "                       \n" +
                    "23                  |                                  |    " +
                    "                       \n" +
                    "24                  X                                       " +
                    "     X                 \n" +
                    "25                  |                                       " +
                    "     |                 \n" +
                    "26                                                            " +
                    "                  X  \n" +
                    "27                                                             " +
                    "                 |  \n" +
                    "28                                                            " +
                    "                  X  \n" +
                    "29                                                           " +
                    "                   |  \n" +
                    "30                                                           " +
                    "                   |  \n" +
                    "31                                                           " +
                    "                   |  \n" +
                    "32                  X                                        " +
                    "    X                 \n" +
                    "33                  |                                       " +
                    "     |                 \n" +
                    "34                  |                                  X    " +
                    "                       \n" +
                    "35                  |                                  |    " +
                    "                       \n" +
                    "36                  |                        X              " +
                    "                       \n" +
                    "37                  |                        |              " +
                    "                       \n" +
                    "38                  |                                  X     " +
                    "                      \n" +
                    "39                  |                                  |          " +
                    "                 \n" +
                    "40                  X                                       " +
                    "     X                 \n" +
                    "41                  |                                            |  " +
                    "               \n" +
                    "42                  |                                            X   " +
                    "              \n" +
                    "43                  |                                            |   " +
                    "              \n" +
                    "44                  |                                            X  " +
                    "               \n" +
                    "45                  |                                            |  " +
                    "               \n" +
                    "46                  |                                            X     " +
                    "            \n" +
                    "47                  |                                            |  " +
                    "               \n" +
                    "48                  X                                  X              " +
                    "             \n" +
                    "49                  |                                  |               " +
                    "            \n" +
                    "50                  |                                  X                " +
                    "           \n" +
                    "51                  |                                  |                 " +
                    "          \n" +
                    "52                  |                                            X       " +
                    "          \n" +
                    "53                  |                                            |      " +
                    "           \n" +
                    "54                  |                                  X                " +
                    "           \n" +
                    "55                  |                                  |                 " +
                    "          \n" +
                    "56   X                                       X                          " +
                    "           \n" +
                    "57   |                                       |                              " +
                    "       \n" +
                    "58   |                                       |                             " +
                    "        \n" +
                    "59   |                                       |                      " +
                    "               \n" +
                    "60   |                                       |                    " +
                    "                 \n" +
                    "61   |                                       |                    " +
                    "                 \n" +
                    "62   |                                       |                    " +
                    "                 \n" +
                    "63   |                                       |                   " +
                    "                  \n", out.toString());
  }
}
