package cs3500.music.view;

import cs3500.music.model.ISong;

/**
 * An interface representation of the IMusicView.
 */
public interface IMusicView {

  /**
   * Renders the view for the music player.
   */
  void render();

  /**
   * Sets up the song for the view.
   *
   * @param s ISong
   */
  void setUp(ISong s);
}
