package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.model.ITone;

/**
 * An interface for the GUIView.
 */
public interface IGUIView extends IMusicView {

  /**
   * Resets the focus back to the scroll pane.
   */
  void resetFocus();

  /**
   * Allows the view to listen for keys pressed.
   *
   * @param     listener KeyListener
   */
  void addKeyListener(KeyListener listener);

  /**
   * Allows the view to listen for mouse clicks.
   *
   * @param     listener MouseListener
   */
  void addMouseListener(MouseListener listener);

  /**
   * Sets the scroll pane back to the beginning of the song.
   */
  void home();

  /**
   * Sets the scroll pane to the end of the song.
   */
  void end();

  /**
   * Allows the arrow keys to be pressed to move right.
   */
  void right();

  /**
   * Allows the arrow keys to be pressed to move up.
   */
  void up();

  /**
   * Allows the the arrow keys to be pressed to move down.
   */
  void down();

  /**
   * Allows the arrow keys to be pressed to move left.
   */
  void left();

  /**
   * Allows Space to be pressed to stop the song.
   */
  void pause();

  //change later!!
  void play();

  /**
   * Switches the space bar and the key P
   */
  void switchPP();

  /**
   * Allows the view to listen for buttons pressed.
   *
   * @param       listener ActionListener
   */
  void addActionListener(ActionListener listener);

  /**
   * Echoes the text input.
   *
   * @param s   The typed String.
   */
  void setEchoText(String s);

  /**
   * Gets the input string.
   *
   * @return String for the input
   */
  String getInputString();

  /**
   * Clears the text box.
   */
  void clearInputString();

  /**
   * Sets the current beat.
   *
   * @param currBeat   double
   */
  void setCurrBeat(double currBeat);

  /**
   * Gets the tone that the mouse clicked.
   *
   * @param y    y value
   * @return     ITone
   */
  ITone getClickedTone(int y);

  /**
   * Gets the Beat that the mouse clicked.
   *
   * @param x    x value
   * @return     int beat
   */
  int getClickedBeat(int x);
}
