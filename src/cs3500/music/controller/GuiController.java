package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import cs3500.music.adapter.IMusicEditorModelView;
import cs3500.music.adapter.MusicEditorAdapter;
import cs3500.music.adapter.Note;
import cs3500.music.adapter.Piece;
import cs3500.music.adapter.PieceAdapter;
import cs3500.music.adapter.Pitch;
import cs3500.music.model.ISong;
import cs3500.music.provider.IGuiView;
import cs3500.music.provider.KeyboardHandler;

/**
 * A class representation of the Music Controller.
 */
public class GuiController implements IMusicController, ActionListener {

  ISong model;
  IGuiView view;
  boolean playing;
  private Timer timer;

  /**
   * Constructor for the GuiController using a view and a model.
   *
   * @param model model, must be an ISong implementation.
   * @param view  view implementation, should work for ISong.
   */
  public GuiController(ISong model, IGuiView view) {
    this.model = model;
    this.view = view;
    this.configureHandlers();
    this.view.setListeners(this, new KeyboardHandler());
    this.playing = true;
    this.timer = new Timer(20, this);
  }

  @Override
  public void start() {
    this.timer = new Timer(20, this);
    this.timer.setActionCommand("Tick");
    this.timer.start();
    this.view.setModel(new MusicEditorAdapter(model));
    this.view.refresh();
    this.view.makeVisible();
  }

  /**
   * Spacebar to pause/start, left right arrow keys to scroll,
   * character inputs into the text box.
   */

  private void configureHandlers() {

    KeyboardHandler khandler = new KeyboardHandler();
    khandler.addPressedRunnable(KeyEvent.VK_RIGHT, new Runnable() {
      @Override
      public void run() {
        view.editXScroll(1);
      }
    });

    khandler.addPressedRunnable(KeyEvent.VK_LEFT, new Runnable() {
      @Override
      public void run() {
        view.editXScroll(-1);
      }
    });

    khandler.addPressedRunnable(KeyEvent.VK_UP, new Runnable() {
      @Override
      public void run() {
        view.editYScroll(1);
      }
    });

    khandler.addPressedRunnable(KeyEvent.VK_DOWN, new Runnable() {
      @Override
      public void run() {
        view.editXScroll(-1);
      }
    });

    khandler.addPressedRunnable(KeyEvent.VK_R, new Runnable() {
      @Override
      public void run() {
        view.restart();
      }
    });

    view.setListeners(this, khandler);


//    pressed.put(KeyEvent.VK_HOME, new Runnable() {
//      @Override
//      public void run() {
//        view.home();
//      }
//    });
//
//    pressed.put(KeyEvent.VK_END, new Runnable() {
//      @Override
//      public void run() {
//        view.end();
//      }
//    });
//    released.put(KeyEvent.VK_SPACE, new PauseRun(view));
//    released.put(KeyEvent.VK_RIGHT, new Runnable() {
//      @Override
//      public void run() {
//        view.switchPP();
//        playing = !playing;
//        view.right();
//      }
//    });
//    released.put(KeyEvent.VK_LEFT, new Runnable() {
//      @Override
//      public void run() {
//        view.left();
//      }
//    });
//    released.put(KeyEvent.VK_UP, new Runnable() {
//      @Override
//      public void run() {
//        view.upPress();
//      }
//    });
//    released.put(KeyEvent.VK_DOWN, new Runnable() {
//      @Override
//      public void run() {
//        view.down();
//      }
//    });
//    released.put(KeyEvent.VK_R, new Runnable() {
//      @Override
//      public void run() {
//        view.restart();
//      }
//    });
//
//
//    khandler.setTypedMap(typed);
//    khandler.setPressedMap(pressed);
//    khandler.setReleasedMap(released);
//    view.addKeyListener(khandler);
//
//    MouseHandler mhandler = new MouseHandler();
//
//    mhandler.setLeftClick(new Runnable() {
//      @Override
//      public void run() {
//        int beat = view.getClickedBeat(mhandler.x);
//        ITone tone = view.getClickedTone(mhandler.y);
//        view.setEchoText(tone.toString() + " " + String.valueOf(beat) +
//                ", Numeric Note Version: " + tone.numeric());
//      }
//    });
//    mhandler.setRightClick(new Runnable() {
//      @Override
//      public void run() {
//        int beat = view.getClickedBeat(mhandler.x);
//        ITone tone = view.getClickedTone(mhandler.y);
//        view.setEchoText(tone.toString() + " " + String.valueOf(beat) +
//                ", Numeric Note Version: " + tone.numeric());
//        model.deleteNoteAtX(tone, beat);
//        view.repaint();
//      }
//    });
//
//    view.addMouseListener(mhandler);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      /*
      case "Add Note Button":
        String noteAdd = view.getInputString();
        model.addNote(IMusicEditorModelView.fromString(noteAdd));
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
        */
      case "Tick":
//        if (playing) {
//          view.refreshGui();
//        } else {
////          view.pause();
//        }
        view.refreshGui();
        break;
      default: break;
    }
  }
}

