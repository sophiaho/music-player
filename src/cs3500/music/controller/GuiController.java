package cs3500.music.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import cs3500.music.model.INote;
import cs3500.music.model.ISong;
import cs3500.music.model.ITone;
import cs3500.music.view.IGUIView;
import cs3500.music.provider.KeyboardHandler;

/**
 * A class representation of the Music Controller.
 */
public class GuiController implements IMusicController, ActionListener {

  ISong model;
  IGUIView view;
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
    KeyboardHandler khandler = new KeyboardHandler();

    khandler.addPressedRunnable(KeyEvent.VK_HOME, new Runnable() {
      @Override
      public void run() {
        view.home();
      }
    });
    khandler.addPressedRunnable(KeyEvent.VK_END, new Runnable() {
      @Override
      public void run() {
        view.end();
      }
    });
    khandler.addPressedRunnable(KeyEvent.VK_SPACE, new Runnable() {
      @Override
      public void run() {
        view.switchPP();
      }
    });
    khandler.addPressedRunnable(KeyEvent.VK_RIGHT, new Runnable() {
      @Override
      public void run() {
        view.right();
      }
    });
    khandler.addPressedRunnable(KeyEvent.VK_LEFT, new Runnable() {
      @Override
      public void run() {
        view.left();
      }
    });
    khandler.addPressedRunnable(KeyEvent.VK_UP, new Runnable() {
      @Override
      public void run() {
        view.upPress();
      }
    });
    khandler.addPressedRunnable(KeyEvent.VK_DOWN, new Runnable() {
      @Override
      public void run() {
        view.down();
      }
    });
    khandler.addPressedRunnable(KeyEvent.VK_R, new Runnable() {
      @Override
      public void run() {
        model.restartRepeats();
        view.restart();
      }
    });
    khandler.addPressedRunnable(KeyEvent.VK_A, new Runnable() {
      @Override
      public void run() {
        JFrame frame2 = new JFrame("");
        String s = JOptionPane.showInputDialog(frame2, "Enter your input in the form " +
                ":Instrument :" +
                " Volume : MIDIValue : Beats : Start Time", JOptionPane.PLAIN_MESSAGE);
        try {
          Scanner scan = new Scanner(s);
          String noteAdd = scan.toString();
          int instrument = scan.nextInt();
          int volume = scan.nextInt();
          int val = scan.nextInt();
          int beats = scan.nextInt();
          int startTime = scan.nextInt();

          model.addNote(INote.fromString(noteAdd));
          view.repaint();
          view.clearInputString();
          view.resetFocus();
          view.restart();
        } catch (NullPointerException e) {
          e.printStackTrace();
        } catch (NumberFormatException e) {
          e.printStackTrace();
        }

      }
    });
    khandler.addPressedRunnable(KeyEvent.VK_D, new Runnable() {
      @Override
      public void run() {
        JFrame frame2 = new JFrame("");
        String s = JOptionPane.showInputDialog(frame2, "Enter your input in the form " +
                "MIDIVal : Time", JOptionPane.PLAIN_MESSAGE);


        Scanner scan = new Scanner(s);
        String noteAdd = scan.toString();
        int midi = scan.nextInt();
        int time = scan.nextInt();

        model.deleteNoteAtX(ITone.fromInt(midi), time);
        view.repaint();
        view.clearInputString();
        view.resetFocus();
        view.restart();
      }
    });

    khandler.addPressedRunnable(KeyEvent.VK_E, new Runnable() {
      @Override
      public void run() {
        JFrame frame2 = new JFrame("");
        String s = JOptionPane.showInputDialog(frame2, "Enter a repeat start and end",
                JOptionPane.PLAIN_MESSAGE);


        Scanner scan = new Scanner(s);
        int start = scan.nextInt() * 20;
        int end = scan.nextInt() * 20;

        if (model.addSafeBasicRepeat(start, end)) {
          view.setRepeats(model.rStarts(), model.rEnds());
          view.restart();
        }

//        if (start <= end && !model.inEnds(end * 20)) {
//          model.addRepeatStart(start * 20);
//          model.addRepeatEnd(end * 20);
//          model.addCorrStart(end * 20, start * 20);
//          view.setRepeats(model.rStarts(), model.rEnds());
//          view.restart();
//        }
      }
    });

    khandler.addPressedRunnable(KeyEvent.VK_M, new Runnable() {
      @Override
      public void run() {
        JFrame frame2 = new JFrame("");
        String s = JOptionPane.showInputDialog(frame2, "Enter a multiple " +
                        "repeat start, return time, and ends",
                JOptionPane.PLAIN_MESSAGE);


        Scanner scan = new Scanner(s);
        int start = scan.nextInt() * 20;
        int ref = scan.nextInt() * 20;
        List<Integer> ends = new ArrayList<>();
        while (scan.hasNext()) {
          ends.add(scan.nextInt() * 20);
        }

        model.addMultipleRepeat(start, ref, ends);

        view.setRepeats(model.mStarts(), model.mEnds());
        view.restart();

//        if (start <= end && !model.inEnds(end * 20)) {
//          model.addRepeatStart(start * 20);
//          model.addRepeatEnd(end * 20);
//          model.addCorrStart(end * 20, start * 20);
//          view.setRepeats(model.rStarts(), model.rEnds());
//          view.restart();
//        }
      }
    });

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
        view.restart();
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
        this.view.restart();
        break;
      case "Remove Note Button":
        String[] toDelete = view.getInputString().split(" ");
        model.deleteNoteAtX(ITone.fromInt(Integer.valueOf(toDelete[0])),
                Integer.valueOf(toDelete[1]));
        this.view.restart();
        break;
      case "Tick":
        int low = roundDown(view.getTick());
        if (model.isRepeat(low)) {
          view.setTick(model.corrStart(low));
          view.repaint();
        } else if (model.multipeAction(low)) {
          System.out.println(model.multipleSet(low));
          int n = model.multipleSet(low);
          if (n != low) {
            view.setTick(model.multipleSet(n));
            view.repaint();
          }
        }
        view.incrementBeat();
        view.repaint();
        break;
    }
  }

  // rounds down to multiple of 20
  private int roundDown(int i) {
    return i - (i % 20);
  }
}

