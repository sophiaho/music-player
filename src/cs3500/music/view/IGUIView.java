package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * An interface
 */
public interface IGUIView extends IMusicView {

  void resetFocus();

  void addKeyListener(KeyListener listener);

  void addMouseListener(MouseListener listener);

  void home();

  void end();

  void right();

  void up();

  void down();

  void left();

  void addActionListener(ActionListener listener);

  String getInputString();

  void clearInputString();
}
