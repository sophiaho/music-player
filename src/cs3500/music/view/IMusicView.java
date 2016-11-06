package cs3500.music.view;

import cs3500.music.model.ISong;

/**
 * Created by andrew on 01/11/2016.
 */
public interface IMusicView {

  /**
   *
   */
  void initialize();

  void render(ISong s);
}
