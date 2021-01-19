//Jordan Wang and Eliyeah Pool
//UTT Selector
//Spec: Allows the user to choose from a variety of UTTTs

import java.awt.*; // abstract windowing toolkit
import java.awt.event.*; // respond to windows events
import javax.swing.*; // JGui stuff
import java.util.*;

public class UTTTSelector extends JFrame
{
	private JTextArea txtTitle;
	private JButton btnAI, btn2PlayerComp, btnPlayer2, btn2PlayerAnalysis;
	private ButtonListener listener;
	public UTTTSelector()
	{
		super("UTTT Selector");
		Container cp = getContentPane();
		cp.setLayout(null);

		listener = new ButtonListener();

		txtTitle = new JTextArea(2, 5);
		txtTitle.setLineWrap(true);
		txtTitle.setEditable(false);
		txtTitle.append("UTTT Selector");
		txtTitle.setFont(new Font("Calibri", Font.PLAIN, 32));
		txtTitle.setBounds(150, 30, 500, 100);

		btnAI = new JButton("UTTT Against AI");
		btnAI.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAI.setBounds(30, 200, 200, 100);
		btnAI.addActionListener(listener);

		btn2PlayerComp = new JButton("UTTT Competitive");
		btn2PlayerComp.setFont(new Font("Calibri", Font.PLAIN, 14));
		btn2PlayerComp.setBounds(230, 200, 200, 100);
		btn2PlayerComp.addActionListener(listener);

		btnPlayer2 = new JButton("UTTT Against AI as Player 2");
		btnPlayer2.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnPlayer2.setBounds(30, 300, 200, 100);
		btnPlayer2.addActionListener(listener);

		btn2PlayerAnalysis = new JButton("UTTT 2 Player with Analysis");
		btn2PlayerAnalysis.setFont(new Font("Calibri", Font.PLAIN, 14));
		btn2PlayerAnalysis.setBounds(230, 300, 200, 100);
		btn2PlayerAnalysis.addActionListener(listener);

		cp.add(txtTitle);
		cp.add(btnAI);
		//cp.add(btn2PlayerComp);
		cp.add(btnPlayer2);
		cp.add(btn2PlayerAnalysis);

		setSize(500,500);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args)
	{
		UTTTSelector u = new UTTTSelector();
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source ==  btnAI)
			{

			}
			else if(source == btn2PlayerComp)
			{

			}
			else if(source == btnPlayer2)
			{

			}
			else if(source == btn2PlayerAnalysis)
			{

			}
			else
			{

			}
		}
	}
}