package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Board;
import game.Piece;
import game.Position;

public class Window extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	final static int windowsize = 1000;
	final Dimension WINDOW_SIZE = new Dimension(windowsize, windowsize);

	final static int SIZE = 8;

	static JPanel game = new JPanel();
	static JButton[] grid = new JButton[SIZE * SIZE];

	Board board = new Board();

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

		updateBoard();

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
					grid[i].setBackground(Color.DARK_GRAY);
				}
			}

			// if odd row
			if ((i / SIZE) % 2 == 1)
			{
				// if even
				if (i % 2 == 0)
				{
					grid[i].setBackground(Color.DARK_GRAY);

				}

				// if odd
				if (i % 2 == 1)
				{
					grid[i].setBackground(Color.WHITE);
				}
			}

		}
	}

	public void updateBoard()
	{
		/*
		 * Check every position Find corresponding picture Update button
		 */

		for (int i = 0; i < SIZE * SIZE; i++)
		{
			Piece piece = board.getPiece(new Position(i));

			if (piece != null)
			{
				String iconName = piece.getIconName();
				if (piece.pieceColor)
					iconName = "../resources/White Pieces/" + iconName + "-icon.png";
				else
					iconName = "../resources/Black pieces/" + iconName + "-icon.png";

				try
				{
					Image img = ImageIO.read(getClass().getResource(iconName));
					Dimension dimension = grid[0].getSize();
					img = img.getScaledInstance((int) (dimension.getWidth() * 0.9), (int) (dimension.getHeight() * 0.9),
							Image.SCALE_SMOOTH);
					grid[i].setIcon(new ImageIcon(img));
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			} else
			{
				grid[i].setIcon(null);
			}

			// clear it

		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
