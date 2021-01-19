import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import javax.swing.border.Border;
import javax.swing.*;

public class MovieGUI extends JFrame
{
	private JLabel lblPicture, lblTitle;
	private ImageIcon icoMovie;
	private JTextArea txtInput, txtSummary;
	private JButton btnSearch;
	private ButtonListener listener;
	private JPanel pnlSouth;
	private ArrayList<Movie> movieList;
	public MovieGUI()
	{
		super("Movie Inheritance");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		movieList = new ArrayList<Movie>();
		listener = new ButtonListener();

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		Border border = BorderFactory.createLineBorder(Color.BLACK); //Creates a border to be used around the text areas

		lblPicture = new JLabel();
		icoMovie = new ImageIcon("");
		lblPicture.setIcon(icoMovie);
		lblTitle = new JLabel("", SwingConstants.CENTER);
		lblTitle.setFont (lblTitle.getFont().deriveFont(28.0f));
		txtInput = new JTextArea(1,10);
		txtInput.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtSummary = new JTextArea(10, 25);
		txtSummary.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txtSummary.setEditable(false);
		txtSummary.setLineWrap(true);
		txtSummary.setWrapStyleWord(true);
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(listener);


		pnlSouth = new JPanel();
		pnlSouth.setLayout(new BorderLayout());
		pnlSouth.add(txtInput, BorderLayout.CENTER);
		pnlSouth.add(btnSearch, BorderLayout.EAST);
		cp.add(lblTitle, BorderLayout.NORTH);
		cp.add(lblPicture, BorderLayout.CENTER);
		cp.add(txtSummary, BorderLayout.EAST);
		cp.add(pnlSouth, BorderLayout.SOUTH);
		setSize(600,600);
		setVisible(true);
		setResizable(false);
	}

	public void addMovie(Movie m)
	{
		movieList.add(m);
	}

	public void setMovie(Movie m)
	{
		lblTitle.setText(m.getTitle());
		lblPicture.setIcon(new ImageIcon(new ImageIcon(m.getPoster()).getImage().getScaledInstance(297, 497, java.awt.Image.SCALE_SMOOTH)));
		txtSummary.setText(m.toString());
	}

	public static void main(String[] args) throws Exception
	{
		new MovieGUI();
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source == btnSearch)
			{
				String text = txtInput.getText();
				for (Movie m : movieList)
				{
					if (m.getTitle().toUpperCase().contains(text.toUpperCase()))
					{
						setMovie(m);
					}
				}
				//do stuff here
			}
		}
	}
}
