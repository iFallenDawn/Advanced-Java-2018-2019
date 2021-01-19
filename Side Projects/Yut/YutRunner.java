//Jordan Wang
//Yut Runner
//Spec: Creates a 2D array of JButtons that reflects the int array in the Connect Four Object,
//		and handles how the user interface looks.

import java.awt.*; // abstract windowing toolkit
import java.awt.event.*; // respond to windows events
import javax.swing.*; // JGui stuff
import javax.swing.border.EmptyBorder;

public class YutRunner extends JFrame
{
	private int turns = 0;
	private boolean player1Turn = true;
	private Yut game;
	private int dim = 6;
	private JButton[][][][] singleGrid = new JButton[dim][dim][dim][dim];
	//biggerboard row, biggerboard column, board row, board column
	private JPanel[][] allGames = new JPanel[dim][dim];
	private ButtonListener listener;
	private JPanel currentPanel;
	public UltimateTTTRunner()
	{
		super("Wang Ultimate TicTacToe");
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(dim, dim));
		listener = new ButtonListener();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setSize(dim*200, dim*200);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		UltimateTTTRunner t = new UltimateTTTRunner();
	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();

		}
	}
}