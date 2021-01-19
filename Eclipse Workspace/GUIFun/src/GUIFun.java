import java.awt.*; // abstract windowing toolkit
import java.awt.event.*; // respond to windows events
import javax.swing.*; // JGui stuff
import java.util.*;

public class GUIFun extends JFrame
{
	public GUIFun()
	{
		super("Poop");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		setSize(700, 500);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new GUIFun();
	}
}
