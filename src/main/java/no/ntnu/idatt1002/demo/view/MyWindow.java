package no.ntnu.idatt1002.demo.view;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import no.ntnu.idatt1002.demo.data.MyEntity;
import no.ntnu.idatt1002.demo.repo.MyEntityRepo;

/**
 * Main window for my application.
 *
 * @author nilstes
 */
public class MyWindow extends JFrame {
  //TODO: sjekk om vi bruker denne noe sted. Hvis ikke, så slett.

  /**
   * Constructor for window.
   *
   * @param  title  Title ow the window
   * @see         Image
   */
  public MyWindow(String title) {
    super(title);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(new GridLayout(1, 1, 1, 1));
  
    MyEntityRepo dao = new MyEntityRepo();
    MyEntity object = dao.getMyEntity("id");
  
    add(new JLabel(object.getName()));
  
    pack();
  }
}
