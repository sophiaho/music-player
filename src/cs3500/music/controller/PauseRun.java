package cs3500.music.controller;

import cs3500.music.view.IGUIView;

/**
 * Created by andrew on 21/11/2016.
 */
class PauseRun implements Runnable {
  IGUIView song;

  PauseRun(IGUIView song) { this.song = song; }

  @Override
  public void run() {
    this.song.switchPP();
  }
}
