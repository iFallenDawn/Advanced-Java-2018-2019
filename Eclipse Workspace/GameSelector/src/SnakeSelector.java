//Jordan Wang and Eliyeah Pool and Vivek Shah
//Snake Selector
//Spec: Allows the user to choose from a variety of Snakes

import java.awt.*; // abstract windowing toolkit
import java.awt.event.*; // respond to windows events
import javax.swing.*; // JGui stuff
import java.util.*;

public class SnakeSelector extends JFrame
{
	private JTextArea txtTitle;
	private JButton btn1Player, btn2Player;
	private ButtonListener listener;
	public SnakeSelector()
	{
		super("Snake Selector");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setLayout(null);

		listener = new ButtonListener();

		txtTitle = new JTextArea(2, 5);
		txtTitle.setLineWrap(true);
		txtTitle.setEditable(false);
		txtTitle.append("Snake Selector");
		txtTitle.setFont(new Font("Calibri", Font.PLAIN, 32));
		txtTitle.setBounds(150, 30, 500, 100);

		btn1Player = new JButton("Snake 1 Player");
		btn1Player.setFont(new Font("Calibri", Font.PLAIN, 14));
		btn1Player.setBounds(30, 200, 200, 100);
		btn1Player.addActionListener(listener);

		btn2Player = new JButton("Snake 2 Player");
		btn2Player.setFont(new Font("Calibri", Font.PLAIN, 14));
		btn2Player.setBounds(230, 200, 200, 100);
		btn2Player.addActionListener(listener);

		cp.add(txtTitle);
		cp.add(btn1Player);
		cp.add(btn2Player);

		setSize(500,500);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args)
	{
		SnakeSelector s = new SnakeSelector();
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source == btn1Player)
			{
				SnakeP1 s = new SnakeP1();
			}
			else if(source == btn2Player)
			{
				SnakeP2 s = new SnakeP2();
			}
		}
	}
}