package cs3500.music.provider;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import cs3500.music.adapter.IMusicEditorModelView;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements IGuiView {

  private IMusicEditorModelView model;
  private GuiViewPanel displayPanel;

  /**
   * Creates new GuiViewFrame.
   */
  public GuiViewFrame() {
    super();

    this.setTitle("Music Editor");
    this.pack();
    this.setSize(new Dimension(1200, 700));

    this.setLayout(new BorderLayout());
    this.displayPanel = new GuiViewPanel();
    this.displayPanel.setPreferredSize(new Dimension(800, 500));

    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);
    this.displayPanel.setPreferredSize(new Dimension(800, 500));

  }


  @Override
  public Dimension getPreferredSize() {
    return new Dimension(100, 100);
  }

  /**
   * refreshes the view.
   */
  public void refresh() {
    this.repaint();
  }

  /**
   * sets the modelView for this view.
   *
   * @param modelView the view being passed.
   */
  @Override
  public void setModel(IMusicEditorModelView modelView) {
    this.model = modelView;
    this.setPiece();
  }


  @Override
  public void refreshGui() {
    this.refresh();
  }

  @Override
  public String getInput() {
    //should never be called.
    return "";
  }

  @Override
  public void setListeners(ActionListener clicks, KeyListener keys) {
    this.addKeyListener(keys);
  }

  /**
   * allows the view to be displayed.
   */
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * sets the notes for this instance of GuiViewFrame.
   */
  void setPiece() {
    this.displayPanel.setPiece(model.getPiece());
  }

  /**
   * updates the time to refresh the red line location.
   *
   * @param x the time to add.
   */
  void updateTime(long x) {
    this.displayPanel.updateTime(x);
  }

  /**
   * tells the view which way to scroll.
   *
   * @param x the int to tell left from right.
   */
  public void editXScroll(int x) {
    this.displayPanel.editXScroll(x);
  }

  @Override
  public void editYScroll(int y) {
    this.displayPanel.editYScroll(y);
  }

  @Override
  public void pause() {
    // does nothing for this class
  }

  @Override
  public void restart() {
    this.displayPanel.updateTime(0);
    this.displayPanel.setXScroll(0);
    this.displayPanel.setYScroll(0);
  }


}