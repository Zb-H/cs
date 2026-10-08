import javax.swing.*;
import java.awt.event.*;
/*
 * This program creates a simple window and a button
 */

public class buttonDemo{
	public static void main(String[] args){

		JFrame window = new JFrame("Button demo");
		JButton button = new JButton("Click me");

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("Button clicked");
				JFrame frame = new JFrame("Button Clicked");
				frame.setSize(1000,600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
		//frame.setSize(1000,600);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setVisible(true);
		window.add(button);
		window.setSize(2000,1232);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
