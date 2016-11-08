package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.swing.*;

import cs3500.music.model.INote;
import cs3500.music.model.ISong;
import cs3500.music.model.ITone;

public class GuiViewPanel extends JPanel {
  private List<ITone> toneSet;
  private TreeMap<Integer, List<INote>> starts;
  private TreeMap<Integer, List<INote>> ends;
  private int tempo;

  private final int SQUARE = 20;
  private final int BEATS = 4;
  private final int TOPOFFSET = 10 + SQUARE;

  public GuiViewPanel() {
    super();
    this.toneSet = new ArrayList<>();
    this.starts = new TreeMap<>();
    this.ends = new TreeMap<>();
    this.tempo = 1;
  }

  public void setSong(ISong s) {
    this.toneSet = s.getRange();
    this.starts = s.starts();
    this.ends = s.ends();
    this.tempo = s.getTempo();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

//    this.setPreferredSize(new Dimension(800, 800));

    this.drawTones(g);

    this.drawNumbers(g);

    this.drawRect(g);

    this.drawHoriz(g);

    this.drawVert(g);
  }

  private void drawTones(Graphics g) {
    // draw: the list of tones
    int toneY = TOPOFFSET + SQUARE; //starting gap
    for (ITone t : toneSet) {
      g.drawString(t.toString(), 2, toneY); //string, int x, int y
      toneY += SQUARE;
    }
  }

  private void drawRect(Graphics g) {
    for (Integer i : starts.keySet()) {
      for (INote n : starts.get(i)) {
        int y = toneSet.indexOf(n.getTone());
        g.setColor(Color.black);
        g.fillRect((i * SQUARE) + SQUARE * 2, (y * SQUARE) + TOPOFFSET, SQUARE, SQUARE);
        if (n.getDuration() > 1) {
          g.setColor(Color.green);
          g.fillRect(((i * SQUARE) + SQUARE * 2) + SQUARE, (y * SQUARE) + TOPOFFSET,
                  (n.getDuration() * SQUARE), SQUARE);
        }
      }
    }
  }


  private void drawNumbers(Graphics g) {
    for (int i = 0; i <= this.beatsCeil(); i++) {
      g.drawString(String.valueOf(i * BEATS), i * BEATS * SQUARE + SQUARE * 2,
              TOPOFFSET - SQUARE / 2);
    }
  }

  private void drawHoriz(Graphics g) {
    int songLength = this.beatsCeil() * 4;
    int horLineY = TOPOFFSET;
    for (int i = 0; i <= toneSet.size(); i++) {
      g.setColor(Color.black);
      g.drawLine(SQUARE * 2, horLineY, ((songLength + 2 + BEATS) * SQUARE), horLineY);
      horLineY += SQUARE;
    }
  }

  private void drawVert(Graphics g) {
    // draw: the vertical lines
    int toneLength = this.toneSet.size();
    int totalBeats = this.beatsCeil() + 1;
    int vertLineX = SQUARE * 2;
    for (int i = 0; i <= totalBeats; i++) {
      g.setColor(Color.black);
      g.drawLine(vertLineX, TOPOFFSET, vertLineX, TOPOFFSET + toneLength * SQUARE);
      vertLineX += SQUARE * BEATS;
    }
  }

  private int beatsCeil() {
    int songLength = this.ends.lastKey();
    if (songLength % BEATS == 0) {
      return songLength / BEATS;
    } else {
      return (songLength / BEATS) + 1;
    }
  }

  public TreeMap<Integer, List<INote>> getStarts() {
    return starts;
  }

  public TreeMap<Integer, List<INote>> getEnds() {
    return ends;
  }

  public int getTempo() {
    return this.tempo;
  }

  public Dimension preferred() {
    return new Dimension(SQUARE * (beatsCeil() + 3), TOPOFFSET + SQUARE * toneSet.size());
  }
}