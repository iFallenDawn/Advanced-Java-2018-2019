//Jordan Wang and Eliyeah Pool
//Game Selector
//Spec: Allows the user to choose from a variety of games

import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.*; // JGui stuff
import java.util.*;

public class GameSelector extends JFrame
{
	private JTextArea txtTitle;
	private JButton btnUTTT, btnSnake, btnConnectFour, btnOmok;
	private ButtonListener listener;
	public GameSelector()
	{
		super("Wang and Eli and Vivek Game Selector");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setLayout(null);

		listener = new ButtonListener();

		txtTitle = new JTextArea(2, 5);
		txtTitle.setLineWrap(true);
		txtTitle.setEditable(false);
		txtTitle.append("Welcome to the Game Selector!");
		txtTitle.setFont(new Font("Calibri", Font.PLAIN, 32));
		txtTitle.setBounds(35, 30, 500, 100);

		btnUTTT = new JButton("UTTT");
		btnUTTT.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnUTTT.setBounds(30, 200, 200, 100);
		btnUTTT.addActionListener(listener);

		btnSnake = new JButton("Snake");
		btnSnake.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnSnake.setBounds(230, 200, 200, 100);
		btnSnake.addActionListener(listener);

		btnConnectFour = new JButton("Connect Four");
		btnConnectFour.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnConnectFour.setBounds(30, 300, 200, 100);
		btnConnectFour.addActionListener(listener);

		btnOmok = new JButton("Omok");
		btnOmok.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnOmok.setBounds(230, 300, 200, 100);
		btnOmok.addActionListener(listener);

		cp.add(txtTitle);
		cp.add(btnUTTT);
		cp.add(btnSnake);
		cp.add(btnConnectFour);
		cp.add(btnOmok);

		setSize(500,500);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args)
	{
		GameSelector g = new GameSelector();
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source == btnUTTT)
			{
				UTTTSelector u = new UTTTSelector();
			}
			else if(source == btnSnake)
			{
				SnakeSelector s = new SnakeSelector();
			}
			else if(source == btnConnectFour)
			{
				ConnectFourSelector c = new ConnectFourSelector();
			}
			else if(source == btnOmok)
			{
				OmokRunner o = new OmokRunner();
			}
			else
			{
				//poop
				//doublepoop
			}
		}
	}
}