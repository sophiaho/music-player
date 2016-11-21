package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.model.ISong;
import cs3500.music.model.ITone;

/**
 * Created by andrew on 21/11/2016.
 */
public class CompositeView implements IGUIView {
  IGUIView gui;
  MidiView midi;

  public CompositeView() {
    this.gui = new GUIView();
    this.midi = new MidiView();
  }

  @Override
  public void resetFocus() {
    gui.resetFocus();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    gui.addKeyListener(listener);
  }

  @Override
  public void render() {
    midi.render();
    gui.render();
    midi.restart();
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    gui.addMouseListener(listener);
  }

  @Override
  public void home() {
    gui.home();
  }

  @Override
  public void end() {
    gui.end();
  }

  @Override
  public void right() {
    gui.right();
    midi.pause();
  }

  @Override
  public void up() {
    gui.up();
  }

  @Override
  public void down() {
    gui.down();
  }

  @Override
  public void setUp(ISong s) {
    gui.setUp(s);
    midi.setUp(s);
  }

  @Override
  public void left() {
    gui.left();
  }

  @Override
  public void pause() {
    gui.pause();
    midi.pause();
  }

  @Override
  public void setCurrBeat(double currBeat) {
    gui.setCurrBeat(currBeat);
  }

  @Override
  public ITone getClickedTone(int y) {
    return null;
  }

  @Override
  public int getClickedBeat(int x) {
    return 0;
  }

  @Override
  public void play() {
    gui.play();
    midi.restart();
  }

  @Override
  public void addActionListener(ActionListener listener) {

  }

  @Override
  public void setEchoText(String s) {

  }

  @Override
  public String getInputString() {
    return null;
  }

  @Override
  public void clearInputString() {

  }
}
