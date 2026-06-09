package monopoly.gui;
import javax.swing.*;
import monopoly.model.Monopoly;


/** Embed the MonopolyGUI in a JFrame to run as a stand-alone application.
@author Byron Weber Becker */
public class MonopolyWindow extends JFrame
{
   public MonopolyWindow(Monopoly model)
   {  super("Monopoly");
      JPanel contents = new MonopolyGUI(model);
      this.setContentPane(contents);
      this.setSize(800, 700);
      this.setVisible(true);
      //this.requestFocus();

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
}
