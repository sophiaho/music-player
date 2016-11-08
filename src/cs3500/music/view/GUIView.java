package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

import cs3500.music.model.ISong;

/**
 * A class implementation of the music view.
 */
public class GUIView extends JFrame implements IMusicView {

  private final JScrollPane scroller;
  final GuiViewPanel panel;

  /**
   * A constructor for the GUIView.
   */
  public GUIView() {
    super();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(800, 300));

    this.panel = new GuiViewPanel();
    //this.panel.setPreferredSize(new Dimension(2000, 400));



    this.scroller = new JScrollPane(this.panel);
    this.getContentPane().add(scroller);

    this.pack();
    this.repaint();
  }

  /**
   * Renders the view.
   */
  @Override
  public void render() {
    this.setVisible(true);
  }

  /**
   * Sets up the view with the song.
   *
   * @param s ISong
   */
  public void setUp(ISong s) {
    this.panel.setSong(s);
    this.panel.setPreferredSize(this.panel.preferred());
  }
}