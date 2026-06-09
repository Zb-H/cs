package monopoly.model;
import javax.swing.*;


/** "Roll" a pair of dice by asking the user for a value. Very useful for
debugging!
@author Byron Weber Becker */
public class UserInputDice extends Dice
{
   /** Construct a new object. */
   public UserInputDice()
   {  super();
   }

   /** Ask the user for the number to use. If the user hits "Cancel",
   the program ends (after a confirmation dialog).
   @return the "roll" of the dice obtained from the user */
   public int getRoll()
   {
      while (true)
      {  try
         {  //JOptionPane pane = new JOptionPane();
            String inputValue = JOptionPane.showInputDialog("Enter dice roll: ");
            if (inputValue == null || inputValue.equals(""))
            {  int answer = JOptionPane.showConfirmDialog(null, "Exiting program", "Confirm Exit",
                        JOptionPane.YES_NO_OPTION);
               if (answer == JOptionPane.OK_OPTION)
               {  System.exit(0);
               }
            } else
            {  int i = Integer.parseInt(inputValue);
               return i;
            }
         } catch (NumberFormatException e)
         {  // ignored
         }
      }
   }
   
}
