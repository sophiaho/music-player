package cs3500.music.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.music.model.ISong;
import cs3500.music.model.ITone;


/**
 * A dummy view that simply draws a string
 */
public class GuiViewPanel extends JPanel {

  ISong song;

  public GuiViewPanel(ISong song) {
    // setting the layout
    this.setLayout(new BorderLayout());
    JPanel musicPanel = new JPanel();
    musicPanel.setPreferredSize(new Dimension(800,300));
    JScrollPane scrollPane = new JScrollPane(musicPanel);
    this.add(scrollPane,BorderLayout.CENTER);

  }

  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);

    List<ITone> toneSet = song.getRange();

    // draw: the list of tones
    int toneY = 20; //starting gap
    for (ITone t : toneSet) {
      g.drawString(t.toString(), 2, toneY); //string, int x, int y
      toneY += 20;
    }

    // draw: the horizontal lines
    int horLineY = 20;
    for (int i = 0; i < toneSet.size(); i++) {
      g.drawLine(10, horLineY, song.songLength() * 5, horLineY);
      horLineY += 20;
    }

    // draw: the vertical lines
    int vertLineX = 20;
    for (int i = 0; i < toneSet.size(); i++) {
      g.drawLine(vertLineX, 20, vertLineX, song.songLength() * 5);
      vertLineX += 20;
    }

    //draw: the green sustains
    int sustainX =
    g.drawRect();
    // TODO draw out the rectangles

  }
}
