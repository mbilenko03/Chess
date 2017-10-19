package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;

	final Dimension WINDOW_SIZE = new Dimension(600, 600);

	final static int SIZE = 8;

	JPanel game = new JPanel();
	JButton[] grid = new JButton[SIZE * SIZE];

	public Window()
	{
		super("Chess");

		// make full screen later
		setSize(WINDOW_SIZE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		game.setLayout(new GridLayout(SIZE, SIZE));

		this.add(game);
		setVisible(true);
	}

	public static void initCheckerBoard()
	{
		// for (i = 0; i < SIZE * SIZE; i++)
		{

		}
	}

}
