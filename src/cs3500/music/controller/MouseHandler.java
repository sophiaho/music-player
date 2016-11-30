package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A class to handle mouse events.
 */
public class MouseHandler implements MouseListener {
  Runnable leftClick;
  Runnable rightClick;
  int x;
  int y;

  public MouseHandler() {
    // doesn't have anything set because it has setters.
  }

  public void setLeftClick(Runnable left) {
    this.leftClick = left;
  }

  public void setRightClick(Runnable right) {
    this.rightClick = right;
  }

  public void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    this.setXY(e.getX(), e.getY());
    if (MouseEvent.BUTTON1 == e.getButton()) {
      leftClick.run();
    }
    if (MouseEvent.BUTTON3 == e.getButton()) {
      rightClick.run();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // doesn't need to do anything
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // doesn't need to do anything
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // doesn't need to do anything
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // doesn't need to do anything
  }
}
