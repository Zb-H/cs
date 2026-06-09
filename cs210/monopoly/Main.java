package monopoly;
import monopoly.model.Monopoly;
import monopoly.gui.MonopolyWindow;


/** Start the graphical user interface version of the program.
@author Byron Weber Becker */
public class Main extends Object
{
   public static void main(String[] args)
   {  Monopoly model = new Monopoly();
      MonopolyWindow mw = new MonopolyWindow(model);
   }
   
}
