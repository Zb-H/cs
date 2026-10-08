package firstWindow;
//import java extention package(javax), call Java frame(swing), Java Frame class(JFrame)
import javax.swing.JFrame;
/*
 * This program creates a  simple window.
 */
public class myWindow{
	public static void main(String[] args){
		JFrame frame = new JFrame("My First Window");
		// class variable = newobject constructor("name");
		frame.setSize(400,300);
		//object.method();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//object.nethod(javaFrameClass.constant);
		frame.setVisible(true);
	}
}

