package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import cs3500.music.model.Song;
import cs3500.music.model.Tone;

/**
 * A dummy view that simply draws a string
 */
public class GuiViewPanel extends JPanel {

  private final Song song;

  public GuiViewPanel(Song song) {
    this.song = song;
  }

  @Override
  public void paintComponent(Graphics g){
    // Handle the default painting
    super.paintComponent(g);

    List<Tone> toneSet = song.firstKey().toneRange(song.lastKey());

    // draw: the list of tones
    int toneY = 20; //starting gap
    for (Tone t : toneSet) {
      g.drawString(t.toString(), 2, toneY); //string, int x, int y
      toneY += 20;
    }

    // draw: the horizontal lines
    int horLineY = 20;
    g.drawLine(10, 20, )
    for (int i = 0; i < toneSet.size(); i++) {

    }


  }

}
