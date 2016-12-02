package cs3500.music.provider;


import java.io.IOException;


import cs3500.music.adapter.IMusicEditorModelView;

/**
 * A dummy view that simply draws a string.
 */
public class ConsoleGuiViewPanel implements IView {
  private Appendable ap;
  private IMusicEditorModelView model;

  /**
   * constructor for a console view to display the string view of the piece.
   */
  public ConsoleGuiViewPanel(Appendable ap) {
    this.ap = ap;
  }

  /**
   * refreshes the output of the view.
   */
  public void refresh() {
    String output = model.display(model.getPiece());
    try {
      ap.append(output);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * does nothing for this view because we pass system.out so it auto prints.
   */
  public void makeVisible() {
    //do nothing for this view.
  }


  /**
   * receives the modelView from the controller.
   *
   * @param modelView the view being passed.
   */
  public void setModel(IMusicEditorModelView modelView) {
    this.model = modelView;
  }


}