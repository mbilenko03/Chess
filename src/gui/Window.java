package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	final Dimension WINDOW_SIZE = new Dimension(600, 600);

	final static int SIZE = 8;

	static JPanel game = new JPanel();
	static JButton[] grid = new JButton[SIZE * SIZE];

	public Window()
	{
		super("Chess");

		// make full screen later
		setSize(WINDOW_SIZE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		game.setLayout(new GridLayout(SIZE, SIZE));
		initCheckerBoard();

		this.add(game);
		setVisible(true);
	}

	public static void initCheckerBoard()
	{
		for (int i = 0; i < SIZE * SIZE; i++)
		{
			// populate buttons to board
			grid[i] = new JButton();
			game.add(grid[i]);

			// if even row
			if ((i / SIZE) % 2 == 0)
			{
				// if even
				if (i % 2 == 0)
				{
					grid[i].setBackground(Color.WHITE);
				}

				// if odd
				if (i % 2 == 1)
				{
					grid[i].setBackground(Color.BLACK);
				}
			}

			// if odd row
			if ((i / SIZE) % 2 == 1)
			{
				// if even
				if (i % 2 == 0)
				{
					grid[i].setBackground(Color.BLACK);

				}

				// if odd
				if (i % 2 == 1)
				{
					grid[i].setBackground(Color.WHITE);
				}
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
