package cs3500.music.adapter;

/**
 * Created by andrew on 01/12/2016.
 */
public interface IMusicEditorModelView {
  /**
   * prints out the state of the piece.
   *
   * @param p the piece to be printed.
   * @return the string containing the printed data.
   */
  String display(Piece p);

  /**
   * gets the piece from the model.
   * @return the piece form the model.
   */
  Piece getPiece();

  /**
   * gets the tempo.
   * @return the tempo.
   */
  int getTempo();

}
