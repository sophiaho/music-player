package cs3500.music.provider;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * interface that we use for our guiview.
 */
public interface IGuiView extends IView {
  /**
   * refreshes the GUI but NOT the MIDI.
   */
  void refreshGui();

  /**
   * gets the input string.
   *
   * @return the input string.
   */
  String getInput();


  /**
   * adds the listeners for the current view.
   */
  void setListeners(ActionListener clicks, KeyListener keys);

  /**
   * scrolls to the left or right.
   *
   * @param x left or right.
   */
  void editXScroll(int x);

  /**
   * scrolls up or down.
   *
   * @param y up or down.
   */
  void editYScroll(int y);

  /**
   * pauses the gui and the midi view.
   */
  void pause();

  /**
   * restarts the song from the beginning.
   */
  void restart();
}
