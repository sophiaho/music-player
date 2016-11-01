package cs3500.music.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import cs3500.music.model.*;

/**
 * A class representation of the Music Controller.
 */
public class MusicController {


  Readable rd;
  Appendable ap;

  /**
   * Constructs a {@Code MusicController} object.
   *
   * @param rd readable
   * @param ap appendable
   */
  public MusicController(Readable rd, Appendable ap) {
    this.rd = rd;
    this.ap = ap;

    try {
      Objects.requireNonNull(rd);
      Objects.requireNonNull(ap);
    } catch (NullPointerException e) {
      throw new IllegalStateException("Readable and appendable cannot be null.");
    }
  }

  private void tryAppend(String s) {
    try {
      ap.append(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
