package cs3500.music.view;

/**
 * Static class to create view implementations.
 */
public final class IMusicViewFactory {

  /**
   * Returns an empty view given the type of view.
   * @param s represents what kind of view is wanted.
   * @return the view implementation that is desired.
   * @throws IllegalArgumentException when not a valid view argument.
   */
  public static IGUIView make(String s) throws IllegalArgumentException {
    switch (s) {
      //case "console": return new TextView(System.out);
      //case "midi": return new MidiView();
      case "visual": return new GUIView();
      case "composite": return new CompositeView();
      default: throw new IllegalArgumentException("Not a valid view type.");
    }
  }
}
