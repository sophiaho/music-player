package cs3500.music.view;

/**
 * A class representation of the IMusicViewFactory.
 */
public final class IMusicViewFactory {

  /**
   * A static method that allows choosing between different views.
   *
   * @param s String that is the input
   * @return IMusicView that the user selects
   */
  public static IMusicView make(String s) throws IllegalArgumentException {
    switch (s) {
      case "console":
        return new TextView(System.out);
      case "midi":
        return new MidiView();
      case "visual":
        return new GUIView();
      default:
        throw new IllegalArgumentException("Not a valid view type.");
    }
  }
}
