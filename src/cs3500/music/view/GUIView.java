package cs3500.music.view;

import javax.swing.*;
import java.awt.*;

import cs3500.music.model.ISong;

public class GUIView extends JFrame implements IMusicView {

  private final JScrollPane scroller;
  final GuiViewPanel panel;

  public GUIView() {
    super();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    this.setSize(this.getPreferredSize());

    this.panel = new GuiViewPanel();

    this.scroller = new JScrollPane(this.panel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.getContentPane().add(scroller);

    this.pack();
    this.repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(800, 300);
  }


  @Override
  public void render() {
    this.setVisible(true);
  }

  public void setUp(ISong s) {
    this.panel.setSong(s);
  }
}