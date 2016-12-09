package cs3500.music.view;

import java.awt.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JPanel;

import cs3500.music.model.INote;
import cs3500.music.model.ISong;
import cs3500.music.model.ITone;

/**
 * A class representation of the GUI view panel.
 */
public class GuiViewPanel extends JPanel {
  private List<ITone> toneSet;
  private HashMap<Integer, List<INote>> starts;
  private HashMap<Integer, List<INote>> ends;
  private int length;
  private int tempo;
  double currBeat;

  private HashMap<Integer, Boolean> rStarts;
  private HashMap<Integer, Boolean> rEnds;

  private final int SQUARE = 20;
  private final int BEATS = 4;
  private final int TOPOFFSET = 10 + SQUARE;

  /**
   * A constructor for the GUIViewPanel.
   */
  public GuiViewPanel() {
    super();
    this.toneSet = new ArrayList<>();
    this.starts = new HashMap<>();
    this.ends = new HashMap<>();
    this.tempo = 1;
    this.rStarts = new HashMap();
    this.rEnds = new HashMap();
  }

  /**
   * Sets the song's tone, starts, ends, and tempo.
   *
   * @param s ISong
   */
  public void setSong(ISong s) {
    this.toneSet = s.getRange();
    Collections.reverse(this.toneSet);
    this.starts = s.starts();
    this.ends = s.ends();
    this.tempo = s.getTempo();
    this.length = s.songLength();
    this.currBeat = 0;
  }

  @Override
  /**
   * Does all the painting by calling helpers, overrides JPanel.
   * @param g Graphics from JPanel
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    //this.setPreferredSize(new Dimension(800, 800));

    this.drawTones(g);

    this.drawNumbers(g);

    this.drawRect(g);

    this.drawHoriz(g);

    this.drawVert(g);

    this.drawBar(g);

    if (rStarts.keySet().size() > 0) {
      for (Integer i : rStarts.keySet()) {
        this.drawStartRepeat(g, i / 20);
      }
      for (Integer i : rEnds.keySet()) {
        this.drawEndRepeat(g, i / 20);
      }
    }
  }

  /**
   * Draws the tones' strings that the song uses.
   *
   * @param g Graphics
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
   * Finds the Tone of the Note given a y value, to be used with mouse clicks.
   *
   * @param y y value provided by mouse handler
   * @return ITone that is the Tone of note clicked
   */
  public ITone findTone(int y) {
    int line = (((y - (TOPOFFSET + SQUARE)) / SQUARE) + 1);
    return toneSet.get(line);
  }

  /**
   * Draws the notes of the song, with black for the first beat, and green for the sustains.
   *
   * @param g Graphics
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
   * @param g Graphics
   */
  private void drawNumbers(Graphics g) {
    for (int i = 0; i <= this.beatsFloor(); i++) {
      g.drawString(String.valueOf(i * BEATS), i * BEATS * SQUARE + SQUARE * 2,
              TOPOFFSET - SQUARE / 2);
    }
  }

  /**
   * Finds the beat of the clicked note.
   *
   * @param x x value provided by mouse handler
   * @return int beat value
   */
  public int findBeat(int x) {
    return ((x - (TOPOFFSET + SQUARE)) / SQUARE);
  }

  /**
   * Draws the horizontal lines for the music piece.
   *
   * @param g Graphics
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
   *
   * @param g Graphics
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
   * Draws the bar that shows the start of the repeat.
   *
   * @param beat the beat that you want the repeat to start on
   * @param g Graphics
   */
  private void drawStartRepeat(Graphics g, int beat) {
    Graphics2D g2d = (Graphics2D) g;
    int toneLength = this.toneSet.size();
    Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
            new float[] {5}, 0);
    Stroke bar = new BasicStroke(1);
    Stroke solid = new BasicStroke(2);
    g2d.setStroke(solid);
    g2d.setColor(Color.black);
    g2d.drawLine(beat * SQUARE + SQUARE * 2, TOPOFFSET,
            beat * SQUARE + SQUARE * 2, TOPOFFSET + toneLength * SQUARE);
    g2d.setStroke(dashed);
    g2d.drawLine((beat * SQUARE + SQUARE * 2) + 4, TOPOFFSET, (beat * SQUARE + SQUARE * 2) + 4,
            TOPOFFSET + toneLength * SQUARE);
  }


  /**
   * Draws the bar that shows the end of the repeat.
   *
   * @param beat the beat that you want the repeat to end on
   */
  private void drawEndRepeat(Graphics g, int beat) {
    Graphics2D g2d = (Graphics2D) g;
    int toneLength = this.toneSet.size();
    Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
            new float[] {5}, 0);
    Stroke solid = new BasicStroke(2);
    g2d.setStroke(dashed);
    g2d.setColor(Color.black);
    g2d.drawLine((beat * SQUARE + SQUARE * 2) - 4, TOPOFFSET,
            (beat * SQUARE + SQUARE * 2) - 4, TOPOFFSET + toneLength * SQUARE);
    g2d.setStroke(solid);
    g2d.drawLine(beat * SQUARE + SQUARE * 2, TOPOFFSET, beat * SQUARE + SQUARE * 2,
            TOPOFFSET + toneLength * SQUARE);
  }

  /**
   * Draws the bar that plays the first time through then repeats, allowing for varied endings
   *
   * @param beat the beat that the first time bar is located on
   */
  private void drawFirstTimeBar(Graphics g, int beat) {
    Graphics2D g2d = (Graphics2D) g;
    int toneLength = this.toneSet.size();
    Stroke bar = new BasicStroke(1);
    g2d.setStroke(bar);
    g2d.setColor(Color.black);
    g2d.drawLine(beat * SQUARE + SQUARE * 2, TOPOFFSET, beat * SQUARE + SQUARE * 2,
            TOPOFFSET + toneLength * SQUARE);
  }

  /**
   * Calculates the floor for the beat count.
   *
   * @return int, the beat count
   */
  private int beatsFloor() {
    int songLength = this.length;
    if (songLength % BEATS == 0) {
      return songLength / BEATS - 1;
    } else {
      return (songLength / BEATS);
    }
  }

  /**
   * Draws the red bar that moves along with the music playing.
   *
   * @param g Graphics
   */
  private void drawBar(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    double set = this.currBeat;
    g2d.setColor(Color.RED);
    g2d.setStroke(new BasicStroke(2));

    g2d.drawLine((int) Math.round(set + SQUARE * 2),
            TOPOFFSET,
            (int) Math.round(set + SQUARE * 2),
            SQUARE * this.toneSet.size() + TOPOFFSET);
  }

  public void setCurrBeat(double currBeat) {
    this.currBeat = currBeat;
    this.repaint();
  }

  /**
   * Gets the notes that start at a certain beat.
   *
   * @return a list of notes (value) that start at a beat (key).
   */
  public HashMap<Integer, List<INote>> getStarts() {
    return starts;
  }

  /**
   * Gets the notes that end at a certain beat.
   *
   * @return a list of notes (value) that start at a beat (key).
   */
  public HashMap<Integer, List<INote>> getEnds() {
    return ends;
  }

  /**
   * Gets the tempo of the song.
   *
   * @return an int, that is the set tempo of the music piece.
   */
  public int getTempo() {
    return this.tempo;
  }

  /**
   * Creates the preferred dimensions of the window.
   *
   * @return a Dimension set to the preferred size.
   */
  public Dimension preferred() {
    return new Dimension((SQUARE * (beatsFloor() + 3) * 4),
            TOPOFFSET + SQUARE * toneSet.size() + 50);
  }


  /**
   * Finds the length of the song.
   *
   * @return int length value
   */
  public int getLength() {
    return length;
  }

  public int getSQUARE() {
    return SQUARE;
  }

  public void setRStarts(HashMap<Integer, Boolean> starts) {
    this.rStarts = starts;
  }

  public void setREnds(HashMap<Integer, Boolean> ends) {
    this.rEnds = ends;
  }
}