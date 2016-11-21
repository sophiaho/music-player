package cs3500.music.view;

import java.awt.Dimension;
import java.awt.event.ActionListener

import javax.swing.*;

import cs3500.music.model.ISong;

import static java.util.Objects.requireNonNull;

/**
 * A class implementation of the music view.
 */
public class GUIView extends JFrame implements IGUIView {
  GuiViewPanel panel;
  final JScrollPane scroller;
  JTextField input;
  JButton addNote, removeNote;

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

    addNote = new JButton("Add Note");
    addNote.setActionCommand("Add Note Button");

    removeNote = new JButton("Remove Note");
    removeNote.setActionCommand("Remove Note Button");

    JPanel interactions = new JPanel();
    interactions.setLayout(new FlowLayout());
    interactions.add(this.input);
    interactions.add(addNote);
    interactions.add(removeNote);

    this.add(interactions, BorderLayout.SOUTH);


    this.pack();
    this.repaint();
  }

  @Override
  public void render() {
    this.setVisible(true);
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
    this.setFocusable(true);
    this.requestFocus();
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

  public void setCurrBeat(int currBeat) {
    this.panel.setCurrBeat(currBeat);
    this.repaint();
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
}