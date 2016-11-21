package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import cs3500.music.model.ISong;
import cs3500.music.view.IGUIView;
import cs3500.music.view.IMusicView;

/**
 * A class representation of the Music Controller.
 */
public class GuiController implements IMusicController, ActionListener {

  ISong model;
  IMusicView view;

  /**
   *  Constructor for the GuiController using a view and a model.
   *
   * @param model model, must be an ISong implementation.
   * @param view view implementation, should work for ISong.
   */
  public GuiController(ISong model, IGUIView view) {
    this.model = model;
    this.view = view;
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
  private void configureKeyboardHandler() {
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

    KeyboardHandler handler = new KeyboardHandler();
    handler.setTypedMap(typed);
    handler.setPressedMap(pressed);
    handler.setReleasedMap(released);
    view.addKeyListener(handler);
  }
  @Override
  public void actionPerformed(ActionEvent e) {

  }

  }

