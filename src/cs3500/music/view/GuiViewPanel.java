package cs3500.music.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs3500.music.model.INote;
import cs3500.music.model.ISong;
import cs3500.music.model.ITone;


/**
 * A dummy view that simply draws a string
 */
public class GuiViewPanel extends JPanel {

  private ISong song;

//  public GuiViewPanel() {
//    // setting the layout
//    JPanel musicPanel = new JPanel();
//    musicPanel.setPreferredSize(new Dimension(800,300));
//    JScrollPane scrollPane = new JScrollPane(musicPanel);
//    this.add(scrollPane,BorderLayout.CENTER);
//  }

  public void initialize(ISong song) {
    this.setSize(800, 300);
    this.setLayout(new BorderLayout());
    this.song = song;
    int i = 1 + 1;
    List<ITone> toneSet = song.getRange();
  }

  @Override
  public void paintComponent(Graphics g) {

    // Handle the default painting
    super.paintComponent(g);

    List<ITone> toneSet = song.getRange();

    drawTones(g, toneSet);

    // draw: the horizontal lines
//    int horLineY = 20;
//    for (int i = 0; i < toneSet.size(); i++) {
//      g.drawLine(10, horLineY, song.songLength() * 5, horLineY);
//      horLineY += 20;
//    }

    // draw: the vertical lines
//    int vertLineX = 20;
//    for (int i = 0; i < toneSet.size(); i++) {
//      g.drawLine(vertLineX, 20, vertLineX, song.songLength() * 5);
//      vertLineX += 20;
//    }




  }

  private void drawTones(Graphics g, List<ITone> tones) {
    // draw: the list of tones
    int toneY = 20; //starting gap
    for (ITone t : tones) {
      g.drawString(t.toString(), 2, toneY); //string, int x, int y
      toneY += 20;
    }
  }

  private void drawRect(Graphics g, List<ITone> tones) {
    //draw: the rectangles , 5 is the height/width
    for (int i = 0; i < song.songLength(); i++) { 
      if (song.allStartsAt(i).size() > 0) { 
        for (INote n : song.allStartsAt(i)) { 
          int y = tones.indexOf(n.getTone()); 
          g.fillRect((i * 5) + 2, (y * 10), 5, 5); 
          g.setColor(Color.black);  
          if (n.getDuration() > 1) {
            g.fillRect(((i * 5) + 2) + 5, (y * 10), (n.getDuration() * 5), 5);
            g.setColor(Color.green);
          }
        }
      } 
    } 
  }
}
