package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.ISong;
import cs3500.music.model.ITone;
import cs3500.music.view.IGUIView;

/**
 * A class representation of the Music Controller.
 */
public class GuiController implements IMusicController, ActionListener {

  ISong model;
  IGUIView view;

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
  }

  @Override
  public void start() {
    this.view.setUp(model);
    this.view.render();
  }

  /**
   * spacebar to pause/start, left right arrow keys to scroll,
   * character inputs into the text box
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
    pressed.put(KeyEvent.VK_SPACE, new Runnable() {
      @Override
      public void run() {
        view.pause();
      }
    });
    released.put(KeyEvent.VK_RIGHT, new Runnable() {
      @Override
      public void run() {
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
        view.up();
      }
    });
    released.put(KeyEvent.VK_DOWN, new Runnable() {
      @Override
      public void run() {
        view.down();
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
        view.setEchoText(tone.toString() + " " + String.valueOf(beat));
      }
    });

    view.addMouseListener(mhandler);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String note = view.getInputString();
    switch (e.getActionCommand()) {
      case "Add Note Button":
      //TODO after you enter something into the text field, which is in the line above it has all the information needed to add a note, but idk how to String --> Note
        //model.addNote();
        view.clearInputString();
        break;
      case "Remove Note Button":
      //TODO same issue here
        //model.deleteNote();
        view.clearInputString();
        break;
    }
  }

  private void increment() {
    int time = 0;
    this.view.render();
    while (true) {
      if (playing) {
        this.view.render();
        this.view.setCurrBeat(time);
        time += 1;
        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

