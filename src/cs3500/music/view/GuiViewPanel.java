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

  private final ISong song;

  public GuiViewPanel(ISong song) {
    this.song = song;
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
//    g.drawLine(10, 20, )
    for (int i = 0; i < toneSet.size(); i++) {

    }
  }
}
