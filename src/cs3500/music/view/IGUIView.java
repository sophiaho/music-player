package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.model.ITone;

/**
 * An interface for the GUIView.
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

  void pause();

  //change later!!
  void play();

  void switchPP();

  void addActionListener(ActionListener listener);

  void setEchoText(String s);

  String getInputString();

  void clearInputString();

  void setCurrBeat(double currBeat);

  ITone getClickedTone(int y);

  int getClickedBeat(int x);
}
