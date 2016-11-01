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

  // notes in the form of duration, pitch, accidental, octave, start
  // override
  public void control() {
    try {
      Scanner scanner = new Scanner(rd);
      String command = "";
      boolean commandCorrectInput = false;

      try {
        command = scanner.next();
      } catch (NoSuchElementException e) {
        ap.append()
      }

      while (!commandCorrectInput) {
        try {
          switch(command) {
            case "add":

              break;
            case "edit":

              break;
            case "delete":

              break;
            default:
              break;
          }
        } catch ()
      }

    } catch ()
  }

  public Note noteMaker(String d, String p, String a, String o, String s) {
    try {
      int duration = Integer.parseInt(d);

      Pitch pitch;
      switch (p) {
        case "C":
          pitch = Pitch.C;
          break;
        case "D":
          pitch = Pitch.D;
          break;
        case "E":
          pitch = Pitch.E;
          break;
        case "F":
          pitch = Pitch.F;
          break;
        case "G":
          pitch = Pitch.G;
          break;
        case "A":
          pitch = Pitch.A;
          break;
        case "B":
          pitch = Pitch.B;
          break;
        default:
          ap.append("Not a valid pitch.");
      }


    }

  }


}
