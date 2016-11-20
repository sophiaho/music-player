package cs3500.music.view;

import java.io.IOException;

import cs3500.music.model.ISong;

import static java.util.Objects.requireNonNull;

/**
 * Text view that returns a console output for the song.
 */
public class TextView implements IMusicView {
  String output;

  private Appendable ap;

  public TextView(Appendable ap) {
    output = "";
    this.ap = ap;
  }

  @Override
  public void render() {
    try {
      ap.append(output);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setUp(ISong s) {
    output = requireNonNull(s).toString();
  }
}
