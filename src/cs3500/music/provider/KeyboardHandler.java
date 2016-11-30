package cs3500.music.provider;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;


/**
 * class for a keyboard handler.
 */
public class KeyboardHandler implements KeyListener {
  private Map<Integer, Runnable> typed;
  private Map<Integer, Runnable> pressed;
  private Map<Integer, Runnable> released;


  /**
   * constructor for a keyboardHandler.
   */
  public KeyboardHandler() {
    this.typed = new HashMap<Integer, Runnable>();
    this.pressed = new HashMap<Integer, Runnable>();
    this.released = new HashMap<Integer, Runnable>();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    Runnable r = this.typed.get(e.getKeyCode());
    if (r != null) {
      r.run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    Runnable r = this.pressed.get(e.getKeyCode());
    if (r != null) {
      r.run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    Runnable r = this.released.get(e.getKeyCode());
    if (r != null) {
      r.run();
    }
  }

  /**
   * adds a runnable to the map.
   *
   * @param num number for the map.
   * @param r   runnable to be added to the map.
   */
  public void addTypedRunnable(int num, Runnable r) {
    this.typed.put(num, r);
  }

  /**
   * adds a runnable to the map.
   *
   * @param num number for the map.
   * @param r   runnable to be added to the map.
   */
  public void addPressedRunnable(int num, Runnable r) {
    this.pressed.put(num, r);
  }

  /**
   * adds a runnable to the map.
   *
   * @param num number for the map.
   * @param r   runnable to be added to the map.
   */
  public void addReleasedRunnable(int num, Runnable r) {
    this.released.put(num, r);
  }

}
