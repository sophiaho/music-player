package cs3500.music.provider;


import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;

import cs3500.music.model.Note;
import cs3500.music.model.Piece;


/**
 * the GUI panel.
 * (actually displays the rects)
 */
class GuiViewPanel extends JPanel {
  /**
   * the piece and the dimensions for drawing.
   */
  private Piece piece;
  private static final int GRID_RECT_X = 80;
  private static final int GRID_RECT_Y = 20;
  private static final int GRID_OFFSET_X = 1200;
  private static final int GRID_OFFSET_Y = 700;
  private int curBeat;
  private int xScroll;
  private int yScroll;


  /**
   * constructor for a GUIViewPanel.
   */
  GuiViewPanel() {
    super();
    this.xScroll = 0;
    this.yScroll = 0;
  }

  /**
   * this method paints the view using graphics g and the piece.
   *
   * @param g the graphics library.
   */
  @Override
  protected void paintComponent(Graphics g) {
    if (curBeat % 60 == 0 && curBeat > 0 && curBeat < this.calcColumns() * 4) {
      xScroll++;
    }
    if (xScroll >= 0 && yScroll >= 0) {
      super.paintComponent(g);

      for (Note n : this.piece.getPiece()) {
        g.setColor(Color.green);
        g.fillRect((GRID_RECT_Y * n.getStartTime() + GRID_RECT_X) - (GRID_OFFSET_X * xScroll)
                , GRID_RECT_Y *
                        (1 + ((n.getPitch().ordinal() + 12 * n.getOctave())
                                - this.piece.getMinPitch())) - (GRID_OFFSET_Y * yScroll),
                GRID_RECT_Y * n.getBeats(), GRID_RECT_Y);

        g.setColor(Color.black);
        g.fillRect((GRID_RECT_Y * n.getStartTime() + GRID_RECT_X) - (GRID_OFFSET_X * xScroll),
                GRID_RECT_Y *
                        (1 + ((n.getPitch().ordinal() + 12 * n.getOctave())
                                - this.piece.getMinPitch())) - (GRID_OFFSET_Y * yScroll),
                GRID_RECT_Y, GRID_RECT_Y);

      }

      for (int x = 1; x <= this.calcColumns(); x++) {
        g.drawRect((x * GRID_RECT_X) - (GRID_OFFSET_X * xScroll)
                , GRID_RECT_Y - (GRID_OFFSET_Y * yScroll), GRID_RECT_X,
                (this.calcRows() + 1) * GRID_RECT_Y);
      }

      for (int y = 1; y <= this.calcRows(); y++) {
        g.drawRect(GRID_RECT_X - (GRID_OFFSET_X * xScroll),
                (GRID_RECT_Y * y) + GRID_RECT_Y - (GRID_OFFSET_Y * yScroll),
                calcColumns() * GRID_RECT_X,
                GRID_RECT_Y);
      }

      for (int i = this.piece.getMinPitch(); i <= this.piece.getMaxPitch(); i++) {
        String pitch = Piece.numToPitch(i % 12).toString();
        int octave = i / 12;
        pitch += octave;
        g.drawString(pitch, GRID_RECT_X / 2 - (GRID_OFFSET_X * xScroll),
                ((i - this.piece.getMinPitch()) * GRID_RECT_Y) +
                        ((GRID_RECT_Y * 5) / 3) - (GRID_OFFSET_Y * yScroll));
      }

      for (int i = 0; i <= this.calcColumns(); i++) {
        if (i % 4 == 0) {
          String num = i * this.piece.getMeasure() + "";
          g.drawString(num, (GRID_RECT_X * i) + GRID_RECT_X - (GRID_OFFSET_X * xScroll),
                  GRID_RECT_Y / 2 - (GRID_OFFSET_Y * yScroll));
        }
      }

      g.setColor(Color.red);
      if (curBeat < this.calcColumns() * 4) {
        g.drawLine(GRID_RECT_X + (GRID_RECT_Y * this.curBeat) - (GRID_OFFSET_X * xScroll),
                GRID_RECT_Y - (GRID_OFFSET_Y * yScroll),
                GRID_RECT_X + (GRID_RECT_Y * this.curBeat) - (GRID_OFFSET_X * xScroll),
                (2 * GRID_RECT_Y) +
                        (GRID_RECT_Y * this.calcRows()) - (GRID_OFFSET_Y * yScroll));
        g.setColor(Color.black);
      }


    }
  }

  /**
   * sets the piece for this GUI view.
   *
   * @param p the piece to be set in this view.
   */
  public void setPiece(Piece p) {
    this.piece = p;
  }

  /**
   * calculates the number of columns of the grid.
   *
   * @return width of the grid.
   */
  private int calcColumns() {
    return (int) Math.ceil((double) this.piece.getMaxBeat() / (double) this.piece.getMeasure());
  }

  /**
   * calculates the rows of the grid.
   *
   * @return height of the grid.
   */
  private int calcRows() {
    return this.piece.getMaxPitch() - this.piece.getMinPitch();
  }

  /**
   * gets the current time.
   *
   * @param x the current time.
   */
  void updateTime(long x) {
    curBeat = (int) x;
  }

  /**
   * scrolls to the right or left.
   *
   * @param x -1 = left 1 = right.
   */
  void editXScroll(int x) {
    this.xScroll += x;
    if (xScroll < 0) {
      xScroll = 0;
    }
  }

  /**
   * scrolls to the up or down.
   *
   * @param y -1 = up 1 = down.
   */
  void editYScroll(int y) {
    this.yScroll += y;
    if (yScroll < 0) {
      yScroll = 0;
    }
  }

  /**
   * sets the yScroll to the given int.
   *
   * @param y what to set yScroll to.
   */
  void setYScroll(int y) {
    this.yScroll = y;
  }

  /**
   * sets the xScroll to the given int.
   *
   * @param x what to set xScroll to.
   */
  void setXScroll(int x) {
    this.xScroll = x;
  }


}
