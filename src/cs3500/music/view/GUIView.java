package cs3500.music.view;

import javax.swing.*;

import cs3500.music.model.ISong;

import java.awt.*;

/**
 * Created by andrew on 01/11/2016.
 */
public class GUIView extends javax.swing.JFrame implements IMusicView {

  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiView
   */
  public GUIView(ISong song) {
    this.displayPanel = new GuiViewPanel(song);
    this.getContentPane().add(displayPanel);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.setSize(300, 800);
    this.setResizable(false);
    this.pack();
  }

//  @Override
  public void initialize(){
    this.setVisible(true);
  }

  @Override //TODO what dis for
  public Dimension getPreferredSize(){
    return new Dimension(100, 100);
  }

}
