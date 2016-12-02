package cs3500.music.provider;


import cs3500.music.adapter.IMusicEditorModelView;

/**
 * interface for a view of any kind.
 */
public interface IView {
  /**
   * makes the view visible to the user.
   */
  void makeVisible();


  /**
   * signals the view to draw itself.
   */
  void refresh();

  /**
   * sets the model to given modelView
   *
   * @param modelView the view being passed.
   */
  void setModel(IMusicEditorModelView modelView);

}
