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
    this.view.initialize();
  }

  @Override
  public void go() {
    this.view.initialize();
  }

  // TODO i believe this is next week's assignment
  @Override
  public String processCommand(String command) {
    return "";
  }

}
