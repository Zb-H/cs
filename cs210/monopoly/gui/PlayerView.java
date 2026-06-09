package monopoly.gui;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import becker.util.IView;
import monopoly.model.*;


/** A view of a player.
@author Byron Weber Becker */
/* package */ class PlayerView extends JPanel implements IView
{
   private JLabel balance = new JLabel("$0");
   private JLabel netWorth = new JLabel("$0");
   private JButton buyProperty = new JButton("Buy Property");
   private JButton finished = new JButton("Finished");
   private JList ownedProp = new JList();
   private static final Font buttonFont = new Font("SansSerif", Font.BOLD, 10);
   private Player model;

   /** Construct a new player view
   @param aPlayer the model for this view */
   /* package */ PlayerView(Player aPlayer)
   {  super();
      this.model = aPlayer;

      this.layoutView();
      this.registerListeners();
      this.model.addView(this);
   }

   /** Update the information shown by this view.*/
   public void updateView()
   {
      this.balance.setText("$" + this.model.getBalance());
      this.netWorth.setText("$" + this.model.getNetWorth());
      this.ownedProp.setListData(this.model.getOwnedPropertyNames());

      if (this.model instanceof ComputerPlayer)
      {  this.buyProperty.setEnabled(false);
         this.finished.setEnabled(false);
         this.ownedProp.setEnabled(false);
      }
      else if (this.model instanceof HumanPlayer)
      {  HumanPlayer hp = (HumanPlayer)this.model;
         // enable or disable controls
         this.buyProperty.setEnabled(hp.isTakingTurn() && hp.canBuyOccupiedSquare());
         this.finished.setEnabled(hp.isTakingTurn());
         this.ownedProp.setEnabled(hp.isTakingTurn());
      }
   }

   /** Layout all the components in this view. */
   private void layoutView()
   {
      this.setLayout(new GridBagLayout());
      this.setBorder(BorderFactory.createTitledBorder(this.model.getName()));

      this.balance.setFont(PlayerView.buttonFont);
      this.netWorth.setFont(PlayerView.buttonFont);
      this.buyProperty.setFont(PlayerView.buttonFont);
      this.finished.setFont(PlayerView.buttonFont);
      this.ownedProp.setFont(PlayerView.buttonFont);

      this.ownedProp.setVisible(true);
      this.ownedProp.setVisibleRowCount(5);
      JScrollPane propScroller = new JScrollPane(this.ownedProp);
      propScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      propScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

      GridBagConstraints gc = new GridBagConstraints();
      gc.gridx = 0;
      gc.gridy = 0;
      gc.weightx = 0.25;
      gc.weighty = 0.0;
      //this.add(new Token(this.model.getID()), gc);
      gc.fill = gc.BOTH;
      this.add(this.balance, gc);
      gc.gridx++;
      this.add(this.netWorth, gc);
      gc.gridwidth=2;
      gc.gridx=0;
      gc.gridy++;
      this.add(this.buyProperty, gc);
      gc.gridy++;
      gc.weighty = 0.75;
      this.add(propScroller, gc);
      gc.gridy++;
      gc.weighty = 0.0;
      this.add(this.finished, gc);
   }

   private void registerListeners()
   {  this.buyProperty.addActionListener(new BuyPropertyListener());
      this.finished.addActionListener(new FinishedListener());
      //this.ownedProp.addMouseListener(new BuyHouseListener());
      this.ownedProp.addListSelectionListener(new BuyHouseListener());
   }

   /** Paint the view. */
   public void paintComponent(Graphics g)
   {  // fill in the background
      g.setColor(this.getBackground());
      g.fillRect(0,0,this.getWidth(), this.getHeight());

      // paint the player's token
      g.setColor(MonopolyGUI.PLAYER_COLORS[this.model.getID()]);
      Insets insets = this.getInsets();
      g.fillOval(this.getWidth() - insets.right - 20, insets.top - 10, 20, 20);

      // paint the other components
      this.paintComponents(g);
   }

   /** The listener for the Buy Property button. */
   private class BuyPropertyListener implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  if (model instanceof HumanPlayer)
         {  HumanPlayer hp = (HumanPlayer)model;
            hp.buyOccupiedSquare();
         }
      }
   }

   /** The listener for the Finished button. */
   private class FinishedListener implements ActionListener
   {
      public void actionPerformed(ActionEvent evt)
      {  if (model instanceof HumanPlayer)
         {  HumanPlayer hp = (HumanPlayer)model;
            hp.finishMove();
         }
      }
   }

   /** The listener for the list of owned properties. */
   private class BuyHouseListener implements ListSelectionListener
   {  public void valueChanged(ListSelectionEvent e)
      {  if (PlayerView.this.ownedProp.getSelectedValue() == null)
         {  return;
         } else if (model instanceof HumanPlayer)
         {  HumanPlayer hp = (HumanPlayer)model;
            String propName = (String)PlayerView.this.ownedProp.getSelectedValue();
            hp.buyHouse(propName);
            PlayerView.this.ownedProp.clearSelection();
         }
      }
   }
   
}
