package monopoly.gui;
import javax.swing.*;
import java.awt.*;
import becker.util.IView;
import monopoly.model.*;


/** A view for a Square model.
@author Byron Weber Becker */
/* package */ class SquareView extends JPanel implements IView
{
   private Square model;
   private JTextArea tArea = new JTextArea();
   private static final Font defFont = new Font("SansSerif", Font.PLAIN, 9);
   private static final int TOKEN_DIA = 20;

   /** Construct a new view.
   @param aSquare the model for this view */
   /* package */ SquareView(Square aSquare)
   {  super();
      this.model = aSquare;

      this.layoutView();
      this.model.addView(this);
   }

   /** Layout all the components in this view. */
   private void layoutView()
   {  this.setLayout(new GridLayout());
      this.add(tArea);
      this.tArea.setFont(this.defFont);
      this.tArea.setOpaque(false);
      this.tArea.setEditable(false);
   }

   /** Update the information shown by this view.*/
   public void updateView()
   {  this.tArea.setText(this.model.getDescription());
      this.repaint();
   }

   /** Paint the view. */
   public void paintComponent(Graphics g)
   {
      g.setColor(this.getBackground());
      g.fillRect(0,0,this.getWidth(),this.getHeight());
      Player[] playersHere = this.model.getPlayers();
      for(int i=0; i<playersHere.length; i++)
      {  int pID = playersHere[i].getID();
         g.setColor(MonopolyGUI.PLAYER_COLORS[pID]);
         g.fillOval(i*TOKEN_DIA, 0, TOKEN_DIA, TOKEN_DIA);
      }
   }
   
}
