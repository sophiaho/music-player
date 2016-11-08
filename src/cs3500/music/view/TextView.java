package cs3500.music.view;

import java.io.IOException;

import cs3500.music.model.ISong;

/**
 * A class representation of the TextView.
 */
public class TextView implements IMusicView {
  String output;

  private Appendable ap;

  /**
   * A constructor for the TextView.
   *
   * @param ap appendable
   */
  public TextView(Appendable ap) {
    output = "";
    this.ap = ap;
  }

  /**
   * Renders the view.
   */
  @Override
  public void render() {
    try {
      ap.append(output);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets up the textual view.
   *
   * @param s ISong
   */
  @Override
  public void setUp(ISong s) {
    output = s.toString();
  }
}
