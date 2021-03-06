package cs3500.music.view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.*;

import cs3500.music.model.ISong;
import cs3500.music.model.ITone;

import static java.util.Objects.requireNonNull;

/**
 * A class implementation of the music view.
 */
public class GUIView extends JFrame implements IGUIView {
  GuiViewPanel panel;
  private final JScrollPane scroller;
  private JTextField input;
  private int curr;

  /**
   * A constructor for the GUIView.
   */
  public GUIView() {
    super();

    this.setLayout(new BorderLayout());

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(800, 300));

    this.panel = new GuiViewPanel();

    this.add(this.panel, BorderLayout.CENTER);

    this.scroller = new JScrollPane(this.panel);
    this.getContentPane().add(scroller);

    this.input = new JTextField(50);
    this.curr = 0;

    this.pack();
    this.repaint();
  }

  @Override
  public void render() {
    scroller.addMouseListener(this.getMouseListeners()[0]);
    this.setVisible(true);
  }

  /**
   * Sets upPress the view with the song.
   *
   * @param s ISong
   */
  public void setUp(ISong s) {
    this.panel.setSong(requireNonNull(s));
    this.panel.setPreferredSize(this.panel.preferred());
  }

  @Override
  public void resetFocus() {
    this.panel.setFocusable(true);
    this.panel.requestFocus();
  }

  @Override
  public void home() {
    JScrollBar hbar = scroller.getHorizontalScrollBar();
    JScrollBar vbar = scroller.getVerticalScrollBar();
    hbar.setValue(hbar.getMinimum());
    vbar.setValue(vbar.getMinimum());
  }

  @Override
  public void end() {
    JScrollBar hbar = scroller.getHorizontalScrollBar();
    JScrollBar vbar = scroller.getVerticalScrollBar();
    hbar.setValue(hbar.getMaximum());
    vbar.setValue(vbar.getMinimum());
  }

  @Override
  public void right() {
    JScrollBar hbar = scroller.getHorizontalScrollBar();
    if (hbar.getBlockIncrement() + hbar.getValue() < hbar.getMaximum()) {
      hbar.setValue(hbar.getBlockIncrement() + hbar.getValue());
    } else {
      hbar.setValue(hbar.getMaximum());
    }
  }

  @Override
  public void autoScroll() {
    JScrollBar hbar = scroller.getHorizontalScrollBar();
    hbar.setUnitIncrement(700);
    if (this.panel.currBeat > (hbar.getValue() + hbar.getUnitIncrement())) {
      hbar.setValue(hbar.getValue() + hbar.getUnitIncrement());
    }
  }

  @Override
  public void left() {
    JScrollBar hbar = scroller.getHorizontalScrollBar();
    if (hbar.getValue() - hbar.getBlockIncrement() > hbar.getMinimum()) {
      hbar.setValue(hbar.getValue() - hbar.getBlockIncrement());
    } else {
      hbar.setValue(hbar.getMinimum());
    }
  }

  @Override
  public void upPress() {
    JScrollBar vbar = scroller.getVerticalScrollBar();
    if (vbar.getValue() + vbar.getBlockIncrement() > vbar.getBlockIncrement()) {
      vbar.setValue(vbar.getValue() - vbar.getBlockIncrement());
    } else {
      vbar.setValue(vbar.getMaximum());
    }
  }

  @Override
  public void down() {
    JScrollBar vbar = scroller.getVerticalScrollBar();
    if (vbar.getValue() - vbar.getBlockIncrement() < vbar.getMinimum()) {
      vbar.setValue(vbar.getValue() + vbar.getBlockIncrement());
    } else {
      vbar.setValue(vbar.getMinimum());
    }
  }

  public void setCurrBeat(int currBeat) {
    this.panel.setCurrBeat(currBeat);
    this.repaint();
  }

  @Override
  public void setEchoText(String s) {
//    display.setText(s);
  }

  @Override
  public String getInputString() {
    return input.getText();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }

  @Override
  public void addActionListener(ActionListener listener) {
//    addNote.addActionListener(listener);
//    removeNote.addActionListener(listener);
  }

  @Override
  public ITone getClickedTone(int y) {
    return panel.findTone(y);
  }

  @Override
  public int getClickedBeat(int x) {
    return panel.findBeat(x);
  }

  @Override
  public void switchPP() {
    // does nothing since it's linked to Midi.
  }

  private int sleepTime() {
    return this.panel.getTempo() / (1000 * this.panel.getSQUARE());
  }

  public void repaint() {
    this.panel.repaint();
  }

  @Override
  public void incrementBeat() {
    this.curr += 1;
    this.panel.setCurrBeat(curr);
  }

  public void restart() {
    this.panel.setCurrBeat(curr);
    this.repaint();
  }

  @Override
  public int getTick() {
    return 0;
  }

  @Override
  public void setTick(int i) {

  }

  @Override
  public void restartMidi() {

  }

  @Override
  public void setRepeats(Set<Integer> starts, Set<Integer> ends) {
    this.panel.setRStarts(starts);
    this.panel.setREnds(ends);
  }
}