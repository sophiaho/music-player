package cs3500.music.adapter;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.model.ISong;
import cs3500.music.model.ITone;
import cs3500.music.provider.IGuiView;
import cs3500.music.view.IGUIView;

/**
 * Adapts provider's GuiViews to our IGUIView.
 */
public class GuiViewAdapter implements IGUIView {
  private IGuiView old;

  public GuiViewAdapter(IGuiView old) {
    this.old = old;
  }

  @Override
  public void render() {
    old.refresh();
    old.makeVisible();
  }

  @Override
  public void resetFocus() {

  }

  @Override
  public void setUp(ISong s) {
    old.setModel(new MusicEditorAdapter(s));
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    old.setListeners(null, listener);
  }

  @Override
  public void addMouseListener(MouseListener listener) { }

  @Override
  public void home() {
  }

  @Override
  public void end() {

  }

  @Override
  public void right() {
    old.editXScroll(1);
  }

  @Override
  public void upPress() {
    old.editYScroll(1);
  }

  @Override
  public void down() {
    old.editYScroll(-1);
  }

  @Override
  public void left() {
    old.editXScroll(-1);
  }

  @Override
  public void autoScroll() {

  }

  @Override
  public void switchPP() {
    old.pause();
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

  @Override
  public void setCurrBeat(int currBeat) {

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
  public void repaint() {
    old.refreshGui();
  }

  @Override
  public void incrementBeat() {
  }

  @Override
  public void restart() {
    old.restart();
  }
}
