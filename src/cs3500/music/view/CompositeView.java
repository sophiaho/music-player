package cs3500.music.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

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
  }

  @Override
  public void upPress() {
    gui.upPress();
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
  public void autoScroll() {
    gui.autoScroll();
  }

  @Override
  public void setCurrBeat(int currBeat) {
    gui.setCurrBeat(currBeat);
  }

  @Override
  public ITone getClickedTone(int y) {
    return gui.getClickedTone(y);
  }

  @Override
  public int getClickedBeat(int x) {
    return gui.getClickedBeat(x);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    gui.addActionListener(listener);
  }

  @Override
  public void setEchoText(String s) {
    gui.setEchoText(s);
  }

  @Override
  public String getInputString() {
    return gui.getInputString();
  }

  @Override
  public void clearInputString() {
    gui.clearInputString();
  }

  @Override
  public void switchPP() {
    midi.switchPP();
    gui.switchPP();
  }

  @Override
  public void repaint() {
    gui.repaint();
  }

  @Override
  public void incrementBeat() {
    gui.setCurrBeat(midi.getTick());
    gui.repaint();
    gui.autoScroll();
  }

  @Override
  public void restart() {
    midi.restart();
    gui.restart();
    gui.repaint();
  }

  @Override
  public int getTick() {
    return midi.getTick();
  }

  @Override
  public void setTick(int i) {
    midi.changeTime(i);
  }

  @Override
  public void restartMidi() {
    midi.restartMidi();
  }

  @Override
  public void setRepeats(HashMap<Integer, Boolean> starts, HashMap<Integer, Boolean> ends) {
    gui.setRepeats(starts, ends);
  }
}
