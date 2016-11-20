package cs3500.music.view;

import cs3500.music.model.ISong;

/**
 * Created by andrew on 01/11/2016.
 */
public interface IMusicView {

  /**
   * Starts the actual rendering (text, music, or gui).
   */
  void render();

  /**
   * Sets up the view before using render, splits the ISong into its components.
   * @param s ISong being processed.
   */
  void setUp(ISong s);
}
