package monopoly.model;
import java.util.*;
import becker.util.IView;



/** A class representing one square on a monopoly board. A Square can be a
building lot such as "Vermont Ave" or "Park Place". It can also be a
railroad, "Go", Chance, the jail, and so on.
@author Byron Weber Becker */
public abstract class Square extends Object
{
   private IView[] views = new IView[1];
   private int numViews = 0;
   

   public String getName()
   {  
    return null;
   }

   /** Get a description of this square.
   @return a String description of this square */
   public String getDescription()
   {  
 return null;
   }

   

   /** Get a list of the players currently occupying this property.
   @return a full array with the 0 or more players who have most recently
   landed on this property */
   public Player[] getPlayers()
   {  
    return null;
   }


   /** Add another view to be notified whenever this part of the model changes.
   @param view the view to add */
   public void addView(IView aView)
   {  
   }

   /** Update all the views registered with this part of the model.*/
   protected synchronized void updateAllViews()
   {  
   }

}
