package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import cs3500.music.model.INote;
import cs3500.music.model.ISong;
import cs3500.music.model.ITone;
import cs3500.music.view.IGUIView;

/**
 * A class representation of the Music Controller.
 */
public class GuiController implements IMusicController, ActionListener {

  ISong model;
  IGUIView view;
  boolean playing;
  private Timer timer;

  /**
   * Constructor for the GuiController using a view and a model.
   *
   * @param model model, must be an ISong implementation.
   * @param view  view implementation, should work for ISong.
   */
  public GuiController(ISong model, IGUIView view) {
    this.model = model;
    this.view = view;
    this.configureHandlers();
    this.view.addActionListener(this);
    this.playing = true;
    this.timer = new Timer(20, this);
  }

  @Override
  public void start() {
    this.timer = new Timer(20, this);
    this.timer.setActionCommand("Tick");
    this.timer.start();
    this.view.setUp(model);
    this.view.render();
  }

  /**
   * Spacebar to pause/start, left right arrow keys to scroll,
   * character inputs into the text box.
   */
  private void configureHandlers() {
    Map<Character, Runnable> typed = new HashMap<>();
    Map<Integer, Runnable> pressed = new HashMap<>();
    Map<Integer, Runnable> released = new HashMap<>();

    pressed.put(KeyEvent.VK_HOME, new Runnable() {
      @Override
      public void run() {
        view.home();
      }
    });
    pressed.put(KeyEvent.VK_END, new Runnable() {
      @Override
      public void run() {
        view.end();
      }
    });
    released.put(KeyEvent.VK_SPACE, new PauseRun(view));
    released.put(KeyEvent.VK_RIGHT, new Runnable() {
      @Override
      public void run() {
        view.switchPP();
        playing = !playing;
        view.right();
      }
    });
    released.put(KeyEvent.VK_LEFT, new Runnable() {
      @Override
      public void run() {
        view.left();
      }
    });
    released.put(KeyEvent.VK_UP, new Runnable() {
      @Override
      public void run() {
        view.upPress();
      }
    });
    released.put(KeyEvent.VK_DOWN, new Runnable() {
      @Override
      public void run() {
        view.down();
      }
    });
    released.put(KeyEvent.VK_R, new Runnable() {
      @Override
      public void run() {
        view.restart();
      }
    });

    KeyboardHandler khandler = new KeyboardHandler();
    khandler.setTypedMap(typed);
    khandler.setPressedMap(pressed);
    khandler.setReleasedMap(released);
    view.addKeyListener(khandler);

    MouseHandler mhandler = new MouseHandler();

    mhandler.setLeftClick(new Runnable() {
      @Override
      public void run() {
        int beat = view.getClickedBeat(mhandler.x);
        ITone tone = view.getClickedTone(mhandler.y);
        view.setEchoText(tone.toString() + " " + String.valueOf(beat) +
                ", Numeric Note Version: " + tone.numeric());
      }
    });
    mhandler.setRightClick(new Runnable() {
      @Override
      public void run() {
        int beat = view.getClickedBeat(mhandler.x);
        ITone tone = view.getClickedTone(mhandler.y);
        view.setEchoText(tone.toString() + " " + String.valueOf(beat) +
                ", Numeric Note Version: " + tone.numeric());
        model.deleteNoteAtX(tone, beat);
        view.repaint();
      }
    });

    view.addMouseListener(mhandler);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Add Note Button":
        String noteAdd = view.getInputString();
        model.addNote(INote.fromString(noteAdd));
        this.view.repaint();
        view.clearInputString();
        view.resetFocus();
        break;
      case "Remove Note Button":
        String[] toDelete = view.getInputString().split(" ");
        model.deleteNoteAtX(ITone.fromInt(Integer.valueOf(toDelete[0])),
                Integer.valueOf(toDelete[1]));
        this.view.repaint();
        view.clearInputString();
        view.resetFocus();
        break;
      case "Tick":
        if (playing) {
          view.incrementBeat();
          view.repaint();
        } else {
          view.switchPP();
        }
        break;
      default: break;
    }
  }
}

