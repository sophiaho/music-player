package cs3500.music.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import cs3500.music.model.*;
import cs3500.music.view.IMusicView;

/**
 * A class representation of the Music Controller.
 */
public class MusicController implements IMusicController{

  ISong model;
  IMusicView view;

  /**
   *
   * @param model
   * @param view
   */
  public MusicController(ISong model, IMusicView view) {
    this.model = model;
    this.view = view;
  }

  public void go() {
    this.view.setUp(model);
    this.view.render();
  }
}