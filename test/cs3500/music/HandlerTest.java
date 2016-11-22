package cs3500.music;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import javafx.geometry.Point2D;

import static org.junit.Assert.assertEquals;
/**
 * Testing for both the Keyboard and Mouse Handlers.
 */
public class HandlerTest {

  class KeyboardHandlerMock {
    Map<Character, String> typed;
    Map<Integer, String> pressed;
    Map<Integer, String> released;

    public KeyboardHandlerMock() {

    }

    void setTypedMap(Map<Character, String> map) {
      typed = map;
    }

    void setPressedMap(Map<Integer, String> map) {
      pressed = map;
    }

    void setReleasedMap(Map<Integer, String> map) {
      released = map;
    }

    public String keyTyped(char e) {
      if (typed.containsKey(e)) {
        return typed.get(e);
      }
      return "Doesn't Contain Key";
    }

    public String keyPressed(int e) {
      if (pressed.containsKey(e)) {
        return pressed.get(e);
      }
      return "Doesn't Contain Key";
    }

    public String keyReleased(int e) {
      if (released.containsKey(e)) {
        return released.get(e);
      }
      return "Doesn't Contain Key";
    }
  }

  class MouseHandlerMock {
    String leftClick;
    String rightClick;
    int x, y;

    public MouseHandlerMock() {
    }

    public void setLeftClick(String left) {
      this.leftClick = left;
    }

    public void setRightClick(String right) { this.rightClick = right; }

    public void setXY(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public String mouseClicked(Point2D e, String s) {
      this.setXY((int)e.getX(), (int)e.getY());
      if ("Left Click" == s) {
        return leftClick;
      }
      if ("Right Click" == s) {
        return rightClick;
      }
      return "No Click";
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
  }

  class MockController {

    public MockController() {

    }
    public String start() {
      return "Timer and GUI started";
    }

    public String actionPerformed(String s) {
      switch(s) {
        case "Add Note":
          return "the note is added.";
        case "Remove Note":
          return "the note is removed.";
      }
      return "invalid action.";
    }

  }

  @Test
  public void testKeyboardHandler() {
    KeyboardHandlerMock x1 = new KeyboardHandlerMock();

    x1.typed = new HashMap<>();
    x1.pressed = new HashMap<>();
    x1.released = new HashMap<>();

    x1.typed.put('Y', "pause");
    assertEquals(x1.keyTyped('C'), "Doesn't Contain Key");

    x1.typed.put('U', "scroll upPress");
    assertEquals("scroll upPress", x1.keyTyped('U'));

    x1.pressed.put(1, "scroll down");
    assertEquals("scroll down", x1.keyPressed(1));

    x1.released.put(2, "scroll right");
    assertEquals("scroll right", x1.keyReleased(2));
  }

  @Test
  public void testMouseHandler() {
    MouseHandlerMock x1 = new MouseHandlerMock();
    assertEquals(x1.leftClick, null);

    x1.setLeftClick("Left Click");
    assertEquals("Left Click", x1.mouseClicked(new Point2D(2, 2), "Left Click"));

    x1.setRightClick("Right Click");
    assertEquals("Right Click", x1.mouseClicked(new Point2D(4, 2), "Right Click"));
  }

  @Test
  public void testController() {
    MockController x1 = new MockController();

    assertEquals(x1.start(), "Timer and GUI started");
    assertEquals(x1.actionPerformed("Add Note"), "the note is added.");
    assertEquals(x1.actionPerformed("Remove Note"), "the note is removed.");
    assertEquals(x1.actionPerformed("Edit Note"), "invalid action.");
  }

}
