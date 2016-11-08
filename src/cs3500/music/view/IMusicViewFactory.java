package cs3500.music.view;

/**
 * Created by andrew on 07/11/2016.
 */
public final class IMusicViewFactory {

  public static IMusicView make(String s) throws IllegalArgumentException {
    switch (s) {
      case "console": return new TextView(System.out);
      case "midi": return new MidiView();
      case "visual": return new GUIView();
      default: throw new IllegalArgumentException("Not a valid view type.");
    }
  }
}
