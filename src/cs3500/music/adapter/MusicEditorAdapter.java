package cs3500.music.adapter;

import cs3500.music.model.ISong;

/**
 * Adapts ISong to a ViewModel for Piece.
 */
public class MusicEditorAdapter implements IMusicEditorModelView {
  private PieceAdapter piece;
  private int tempo;

  public MusicEditorAdapter(ISong s) {
    this.piece = new PieceAdapter(s);
    this.tempo = s.getTempo();
  }

  @Override
  public String display(Piece p) {
    return p.toString();
  }

  @Override
  public Piece getPiece() {
    return piece;
  }

  @Override
  public int getTempo() {
    return tempo;
  }
}
