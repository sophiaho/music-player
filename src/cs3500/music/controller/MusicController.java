package cs3500.music.controller;

import cs3500.music.model.ISong;
import cs3500.music.view.IMusicView;

/**
 * A class representation of the Music Controller.
 */
public class MusicController implements IMusicController {

  ISong model;
  IMusicView view;

  /**
   * Constructor for the MusicController using a view and a model.
   *
   * @param model model, must be an ISong implementation.
   * @param view  view implementation, should work for ISong.
   */
  public MusicController(ISong model, IMusicView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void start() {
    this.view.setUp(model);
    this.view.render();
  }
}