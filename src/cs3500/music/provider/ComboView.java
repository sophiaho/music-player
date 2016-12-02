package cs3500.music.provider;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;

import cs3500.music.adapter.IMusicEditorModelView;


/**
 * This View is the combination of the GUI and the Midi Views.
 */
public class ComboView extends javax.swing.JFrame implements IGuiView {

  private MidiView midiView;
  private GuiViewFrame guiView;
  private IMusicEditorModelView model;
  private int time;


  /**
   * constructor for a ComboView.
   *
   * @param midiView the midi view we are using.
   * @param guiView  the gui view we are using.
   */
  public ComboView(MidiView midiView, GuiViewFrame guiView) {
    this.guiView = guiView;
    this.midiView = midiView;
    this.time = 0;
  }


  @Override
  public void refreshGui() {
    this.guiView.refresh();
  }

  @Override
  public String getInput() {
    return null;
  }

  @Override
  public void setListeners(ActionListener clicks, KeyListener keys) {
    this.addKeyListener(keys);
  }

  @Override
  public void makeVisible() {
    this.guiView.makeVisible();

    MetaEventListener timeUpdater = new MetaEventListener() {
      public void meta(MetaMessage evt) {
        updateTime();
        guiView.refresh();
      }
    };
    //this.timer = new Timer((this.model.getTempo()) / 1000, timeUpdater);
    //this.timer.start();
    this.midiView.addMetas(timeUpdater);
    this.midiView.makeVisible();
    this.setVisible(true);
  }


  @Override
  public void refresh() {
    this.guiView.refresh();
    this.midiView.refresh();
  }

  @Override
  public void setModel(IMusicEditorModelView modelView) {
    this.model = modelView;
    this.guiView.setModel(this.model);
    this.midiView.setModel(this.model);
  }

  /**
   * udpates the time so that the panel can draw the red line.
   */
  private void updateTime() {
    this.time++;
    this.guiView.updateTime(time);
  }

  /**
   * tells the view to scroll left or right.
   *
   * @param x left or right.
   */
  public void editXScroll(int x) {
    this.guiView.editXScroll(x);
  }

  /**
   * tells the view to scroll up or down.
   *
   * @param y up or down.
   */
  public void editYScroll(int y) {
    this.guiView.editYScroll(y);
  }

  @Override
  public void pause() {
    this.midiView.pause();
  }

  @Override
  public void restart() {
    this.midiView.restart();
    this.guiView.restart();
    this.time = 0;
  }


}
