package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import javax.swing.*;

import cs3500.music.model.INote;
import cs3500.music.model.ISong;
import cs3500.music.model.ITone;

/**
 * A class representation of the GUI view panel.
 */
public class GuiViewPanel extends JPanel {
  private List<ITone> toneSet;
  private TreeMap<Integer, List<INote>> starts;
  private TreeMap<Integer, List<INote>> ends;
  private int tempo;

  private final int SQUARE = 20;
  private final int BEATS = 4;
  private final int TOPOFFSET = 10 + SQUARE;

  /**
   * A constructor for the GUIViewPanel.
   */
  public GuiViewPanel() {
    super();
    this.toneSet = new ArrayList<>();
    this.starts = new TreeMap<>();
    this.ends = new TreeMap<>();
    this.tempo = 1;
  }

  /**
   * Sets the song's tone, starts, ends, and tempo.
   *
   * @param s  ISong
   */
  public void setSong(ISong s) {
    this.toneSet = s.getRange();
    Collections.reverse(this.toneSet);
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

  /**
   * Draws the tones' strings that the song uses.
   *
   * @param g    Graphics
   */
  private void drawTones(Graphics g) {
    // draw: the list of tones
    int toneY = TOPOFFSET + SQUARE; //starting gap
    for (ITone t : toneSet) {
      g.drawString(t.toString(), 2, toneY); //string, int x, int y
      toneY += SQUARE;
    }
  }

  /**
   * Draws the notes of the song, with black for the first beat, and green for the sustains.
   *
   * @param g    Graphics
   */
  private void drawRect(Graphics g) {
    for (Integer i : starts.keySet()) {
      for (INote n : starts.get(i)) {
        int y = toneSet.indexOf(n.getTone());
        g.setColor(Color.black);
        g.fillRect((i * SQUARE) + SQUARE * 2, (y * SQUARE) + TOPOFFSET, SQUARE, SQUARE);
        if (n.getDuration() > 1) {
          g.setColor(Color.green);
          g.fillRect((i * SQUARE) + SQUARE * 3, (y * SQUARE) + TOPOFFSET,
                  (n.getDuration() - 1) * SQUARE, SQUARE);
        }
      }
    }
  }


  /**
   * Draws the numbers that count the measures.
   *
   * @param g     Graphics
   */
  private void drawNumbers(Graphics g) {
    int current = 0;
    for (int i = 0; i <= this.beatsFloor(); i++) {
      g.drawString(String.valueOf(i * BEATS), i * BEATS * SQUARE + SQUARE * 2, TOPOFFSET);
      //string, int x, int y
      current += BEATS;
    }
  }

  /**
   * Draws the horizontal lines for the music piece.
   *
   * @param g    Graphics
   */
  private void drawHoriz(Graphics g) {
    int songLength = this.beatsFloor() * 4;
    int horLineY = TOPOFFSET;
    for (int i = 0; i <= toneSet.size(); i++) {
      g.setColor(Color.black);
      g.drawLine(SQUARE * 2, horLineY, ((songLength + 2 + BEATS) * SQUARE), horLineY);
      horLineY += SQUARE;
    }
  }

  /**
   * Draws the vertical lines for the music piece.
   * @param g    Graphics
   */
  private void drawVert(Graphics g) {
    // draw: the vertical lines
    int toneLength = this.toneSet.size();
    int totalBeats = this.beatsFloor() + 1;
    int vertLineX = SQUARE * 2;
    for (int i = 0; i <= totalBeats; i++) {
      g.setColor(Color.black);
      g.drawLine(vertLineX, TOPOFFSET, vertLineX, TOPOFFSET + toneLength * SQUARE);
      vertLineX += SQUARE * BEATS;
    }
  }

  /**
   * Calculates the floor for the beat count.
   *
   * @return int, the beat count
   */
  private int beatsFloor() {
    int songLength = this.ends.lastKey();
    if (songLength % BEATS == 0) {
      return songLength / BEATS - 1;
    } else {
      return (songLength / BEATS);
    }
  }

  /**
   * Gets the notes that start at a certain beat.
   *
   * @return a list of notes (value) that start at a beat (key).
   */
  public TreeMap<Integer, List<INote>> getStarts() {
    return starts;
  }

  /**
   * Gets the notes that end at a certain beat.
   *
   * @return a list of notes (value) that start at a beat (key).
   */
  public TreeMap<Integer, List<INote>> getEnds() {
    return ends;
  }

  /**
   * Gets the tempo of the song.
   *
   * @return an int, that is the set tempo of the music piece.
   */
  public int getTempo() { return this.tempo; }
}