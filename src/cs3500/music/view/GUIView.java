package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

import cs3500.music.model.ISong;

public class GUIView extends JFrame implements IMusicView {

  private final JScrollPane scroller;
  final GuiViewPanel panel;

  public GUIView() {
    super();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(1000, 1000));

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

  public void setUp(ISong s) {
    this.panel.setSong(s);
    this.setPreferredSize(this.panel.preferred());
  }
}