package cs3500.music.view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.model.ISong;
import cs3500.music.model.ITone;

import static java.util.Objects.requireNonNull;

/**
 * A class implementation of the music view.
 */
public class GUIView extends JFrame implements IGUIView {
  GuiViewPanel panel;
  private final JScrollPane scroller;
  private JTextField input;
  private JButton addNote, removeNote;
  private JLabel display;
  private boolean playing;

  /**
   * A constructor for the GUIView.
   */
  public GUIView() {
    super();

    this.setLayout(new BorderLayout());

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(800, 300));

    this.panel = new GuiViewPanel();

    this.add(this.panel, BorderLayout.CENTER);

    this.scroller = new JScrollPane(this.panel);
    this.getContentPane().add(scroller);

    this.input = new JTextField(50);
    this.playing = true;

    addNote = new JButton("Add Note");
    addNote.setActionCommand("Add Note Button");

    removeNote = new JButton("Remove Note");
    removeNote.setActionCommand("Remove Note Button");

    display = new JLabel("Enter a note below.");

    JPanel interactions = new JPanel();
    interactions.setLayout(new BorderLayout());
    interactions.add(this.input, BorderLayout.CENTER);
    interactions.add(addNote, BorderLayout.SOUTH);
    interactions.add(removeNote, BorderLayout.EAST);
    interactions.add(display, BorderLayout.NORTH);

    this.add(interactions, BorderLayout.SOUTH);

    this.pack();
    this.repaint();
  }

  @Override
  public void render() {
    scroller.addMouseListener(this.getMouseListeners()[0]);
    this.setVisible(true);
    int sleepy = this.sleepTime();
    int curr = 0;
    while(true) {
      if (this.playing) {
        this.setCurrBeat(curr);
        this.repaint();
        curr += 1;
        try {
          Thread.sleep(sleepy);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Sets up the view with the song.
   *
   * @param s  ISong
   */
  public void setUp(ISong s) {
    this.panel.setSong(requireNonNull(s));
    this.panel.setPreferredSize(this.panel.preferred());
  }

  @Override
  public void resetFocus() {
    this.panel.setFocusable(true);
    this.panel.requestFocus();
  }

  @Override
  public void home() {
    JScrollBar hbar = scroller.getHorizontalScrollBar();
    JScrollBar vbar = scroller.getVerticalScrollBar();
    hbar.setValue(hbar.getMinimum());
    vbar.setValue(vbar.getMinimum());
  }

  @Override
  public void end() {
    JScrollBar hbar = scroller.getHorizontalScrollBar();
    JScrollBar vbar = scroller.getVerticalScrollBar();
    hbar.setValue(hbar.getMaximum());
    vbar.setValue(vbar.getMinimum());
  }

  @Override
  public void right() {
    JScrollBar hbar = scroller.getHorizontalScrollBar();
    if (hbar.getBlockIncrement() + hbar.getValue() < hbar.getMaximum()) {
      hbar.setValue(hbar.getBlockIncrement() + hbar.getValue());
    } else {
      hbar.setValue(hbar.getMaximum());
    }
  }

  @Override
  public void left() {
    JScrollBar hbar = scroller.getHorizontalScrollBar();
    if (hbar.getValue() - hbar.getBlockIncrement() > hbar.getMinimum()) {
      hbar.setValue(hbar.getValue() - hbar.getBlockIncrement());
    }
    else {
      hbar.setValue(hbar.getMinimum());
    }
  }

  @Override
  public void up() {
    JScrollBar vbar = scroller.getVerticalScrollBar();
    if (vbar.getValue() + vbar.getBlockIncrement() > vbar.getBlockIncrement()) {
      vbar.setValue(vbar.getValue() - vbar.getBlockIncrement());
    } else {
      vbar.setValue(vbar.getMaximum());
    }
  }

  @Override
  public void down() {
    JScrollBar vbar = scroller.getVerticalScrollBar();
    if (vbar.getValue() - vbar.getBlockIncrement() < vbar.getMinimum()) {
      vbar.setValue(vbar.getValue() + vbar.getBlockIncrement());
    } else {
      vbar.setValue(vbar.getMinimum());
    }
  }

  public void setCurrBeat(double currBeat) {
    this.panel.setCurrBeat(currBeat);
    this.repaint();
  }

  @Override
  public void pause() {
    //TODO the pause is now linked to the spacebar, but idk how you did pause so
    this.playing = false;
    render();
  }

  @Override
  public void setEchoText(String s) {
    display.setText(s);
  }

  @Override
  public String getInputString() {
    return input.getText();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }

  @Override
  public void addActionListener(ActionListener listener) {
    addNote.addActionListener(listener);
    removeNote.addActionListener(listener);
  }

  @Override
  public ITone getClickedTone(int y) {
    return panel.findTone(y);
  }

  @Override
  public int getClickedBeat(int x) {
    return panel.findBeat(x);
  }

  @Override
  public void play() {
    this.playing = true;
    render();
  }

  @Override
  public void switchPP() {
    this.playing = !this.playing;
  }

  private int sleepTime() {
    return (int) Math.round((double) this.panel.getTempo() / (1000 * this.panel.getSQUARE()));
  }

//  @Override
//  public void addKeyListener(KeyListener listener) {
//
//  }
//
//  @Override
//  public void addMouseListener(MouseListener listener) {
//
//  }
}