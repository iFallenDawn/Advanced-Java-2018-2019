//Jordan Wang and Eliyeah Pool and Vivek Shah
//ConnectFour Selector
//Spec: Allows the user to choose from a variety of ConnectFour

import java.awt.*; // abstract windowing toolkit
import java.awt.event.*; // respond to windows events
import javax.swing.*; // JGui stuff
import java.util.*;

public class ConnectFourSelector extends JFrame
{
	private JTextArea txtTitle;
	private JButton btn1Player, btn2Player;
	private ButtonListener listener;
	public ConnectFourSelector()
	{
		super("Connect Four Selector");
		Container cp = getContentPane();
		cp.setLayout(null);

		listener = new ButtonListener();

		txtTitle = new JTextArea(2, 5);
		txtTitle.setLineWrap(true);
		txtTitle.setEditable(false);
		txtTitle.append("Connect Four Selector");
		txtTitle.setFont(new Font("Calibri", Font.PLAIN, 32));
		txtTitle.setBounds(100, 30, 500, 100);

		btn1Player = new JButton("Connect 4 Against AI");
		btn1Player.setFont(new Font("Calibri", Font.PLAIN, 14));
		btn1Player.setBounds(30, 200, 200, 100);
		btn1Player.addActionListener(listener);

		btn2Player = new JButton("Connect Four 2 Players");
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
		ConnectFourSelector c = new ConnectFourSelector();
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source == btn1Player)
			{
				ConnectFourRunnerAI c = new ConnectFourRunnerAI();
			}
			else if(source == btn2Player)
			{
				ConnectFourRunner2Player c = new ConnectFourRunner2Player();
			}
		}
	}
}