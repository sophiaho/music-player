package cs3500.music.view;

import javax.swing.*;

import cs3500.music.model.ISong;

import java.awt.*;

/**
 * Created by andrew on 01/11/2016.
 */
public class GUIView extends javax.swing.JFrame implements IMusicView {

  private GuiViewPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiView
   */
  public GUIView() {
    this.displayPanel = new GuiViewPanel();
    this.setSize(800, 300);
    this.setResizable(false);
    this.getContentPane().add(displayPanel);
    this.pack();

    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  }

  public void render(ISong s) {
    this.displayPanel.initialize(s);
    this.pack();
    this.repaint();
  }

  //  @Override
  public void initialize() {
    this.pack();
    this.setVisible(true);
  }

//  @Override //TODO what dis for
//  public Dimension getPreferredSize(){
//    return new Dimension(100, 100);
//  }

}
