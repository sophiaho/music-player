package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to handle keyboard events.
 */
public class KeyboardHandler implements KeyListener {
  private Map<Character, Runnable> typed;
  private Map<Integer, Runnable> pressed;
  private Map<Integer, Runnable> released;

  void setTypedMap(Map<Character, Runnable> map) {
    typed = map;
  }

  void setPressedMap(Map<Integer, Runnable> map) {
    pressed = map;
  }

  void setReleasedMap(Map<Integer, Runnable> map) {
    released = map;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (typed.containsKey(e.getKeyChar()))
      typed.get(e.getKeyChar()).run();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (pressed.containsKey(e.getKeyChar()))
      pressed.get(e.getKeyCode()).run();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (released.containsKey(e.getKeyChar()))
      released.get(e.getKeyCode()).run();
  }
}
