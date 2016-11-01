package cs3500.music.view;

import javax.swing.*;

import java.awt.*;

/**
 * Created by andrew on 01/11/2016.
 */
public class MusicView extends javax.swing.JFrame implements IMusicView {

  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiView
   */
  public MusicView() {
    this.displayPanel = new GuiViewPanel();
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);
    this.setSize(800, 300);
    this.setResizable(false);
    this.setLayout(new ScrollPaneLayout());
    this.pack();
  }

  @Override
  public void initialize(){
    this.setVisible(true);
  }

//  @Override
//  public Dimension getPreferredSize(){
//    return new Dimension(100, 100);
//  }

}
