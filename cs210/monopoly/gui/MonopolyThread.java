package monopoly.gui;
import java.lang.Thread;
import java.lang.Runnable;
import monopoly.model.*;


/** A class to make a monopoly model runnable as a thread.
@author Byron Weber Becker */
/* package */ class MonopolyThread extends Object implements Runnable
{
   private Monopoly model;

   /* package */ MonopolyThread(Monopoly aGame)
   {  super();
      this.model = aGame;
   }

   public void run()
   {  this.model.playGame();
   }
   
}
