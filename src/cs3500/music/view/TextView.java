package cs3500.music.view;

import java.io.IOException;

import cs3500.music.model.ISong;

/**
 * Created by andrew on 07/11/2016.
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
    output = s.toString();
  }
}
