package cs3500.music.view;

import java.awt.Dimension;

import javax.swing.*;

import cs3500.music.model.ISong;

import static java.util.Objects.requireNonNull;

/**
 * A class implementation of the music view.
 */
public class GUIView extends JFrame implements IGUIView {

  final GuiViewPanel panel;
  final JScrollPane scroller;

  /**
   * A constructor for the GUIView.
   */
  public GUIView() {
    super();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(800, 300));

    this.panel = new GuiViewPanel();

    this.scroller = new JScrollPane(this.panel);
    this.getContentPane().add(scroller);

    this.pack();
    this.repaint();
  }

  @Override
  public void render() {
    this.setVisible(true);
  }

  /**
   * Sets up the view with the song.
   *
   * @param s  ISong
   */
  public void setUp(ISong s) {
    this.panel.setSong(requireNonNull(s));
    this.panel.setPreferredSize(this.panel.preferred());
  }

  @Override
  public void resetFocus() {

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
  public void left() {
    JScrollBar hbar = scroller.getHorizontalScrollBar();
    if (hbar.getValue() - hbar.getBlockIncrement() > hbar.getMinimum()) {
      hbar.setValue(hbar.getValue() - hbar.getBlockIncrement());
    }
    else {
      hbar.setValue(hbar.getMinimum());
    }
  }

  @Override
  public void up() {
    JScrollBar vbar = scroller.getVerticalScrollBar();
    if (vbar.getValue() + vbar.getBlockIncrement() < vbar.getBlockIncrement()) {
      vbar.setValue(vbar.getValue() + vbar.getBlockIncrement());
    } else {
      vbar.setValue(vbar.getMaximum());
    }
  }

  @Override
  public void down() {
    JScrollBar vbar = scroller.getVerticalScrollBar();
    if (vbar.getValue() - vbar.getBlockIncrement() > vbar.getMinimum()) {
      vbar.setValue(vbar.getValue() - vbar.getBlockIncrement());
    } else {
      vbar.setValue(vbar.getMinimum());
    }
  }
}