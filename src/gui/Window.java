package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Board;
import game.Piece;
import game.Position;

public class Window extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	final Dimension WINDOW_SIZE = new Dimension(1000, 1000);

	final static int SIZE = 8;

	JPanel game = new JPanel();
	JButton[] grid = new JButton[SIZE * SIZE];

	Board board = new Board();

	Piece selectedPiece = null;
	Boolean isPieceSelected = false;

	List<Position> possibleChoices = new ArrayList<Position>();

	Boolean isWhiteTurn = true;

	public Window()
	{
		super("Chess");

		// TODO Make full screen later
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

	// Create checker board pattern of buttons
	public void initCheckerBoard()
	{
		for (int i = 0; i < SIZE * SIZE; i++)
		{
			// populate buttons to board
			grid[i] = new JButton();
			game.add(grid[i]);
			grid[i].addActionListener(this);

			setCheckerBoardColor(i);

		}
	}

	public void setCheckerBoardColor(int i)
	{
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

	public void updateAtPosition(Position position)
	{
		Piece piece = board.getPiece(position);

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
				img = img.getScaledInstance((int) (dimension.getWidth() * 0.75), (int) (dimension.getHeight() * 0.75),
						Image.SCALE_SMOOTH);
				grid[position.getIndex()].setIcon(new ImageIcon(img));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else
		{
			grid[position.getIndex()].setIcon(null);
		}
	}

	public void showOptions(Position position)
	{

		possibleChoices = board.getMoves(position);

		if (possibleChoices != null)
			for (Position element : possibleChoices)
			{
				grid[element.getIndex()].setBackground(Color.BLUE);
			}
	}

	public void clearOptions()
	{
		if (possibleChoices != null)
		{
			for (Position element : possibleChoices)
			{
				setCheckerBoardColor(element.getIndex());
			}

			possibleChoices.clear();
		}
	}

	// Update images on board
	public void updateBoard()
	{
		// Iterate through all buttons
		for (int i = 0; i < SIZE * SIZE; i++)
		{
			updateAtPosition(new Position(i));
		}

	}

	public void endGame()
	{
		for (int i = 0; i < SIZE * SIZE; i++)
		{
			grid[i].setEnabled(false);
		}
	}

	private void endStaleGame()
	{
		endGame();
		JOptionPane.showMessageDialog(null, "Stalemate!");
		this.dispose();
	}

	private void endCheckGame()
	{
		endGame();
		if (isWhiteTurn)
		{
			JOptionPane.showMessageDialog(null, "Checkmate! Black wins!");
			this.dispose();
		} else
		{
			JOptionPane.showMessageDialog(null, "Checkmate! White wins!");
			this.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent source)
	{

		// change color to potential squares to move to
		// move piece if potential square is clicked

		for (int i = 0; i < SIZE * SIZE; i++)
		{
			// check what piece was clicked on
			if (grid[i] == source.getSource())
			{
				if (!isPieceSelected)
				{
					if (board.getPiece(new Position(i)) != null
							&& board.getPiece(new Position(i)).pieceColor == isWhiteTurn)
					{
						selectedPiece = board.getPiece(new Position(i));
						isPieceSelected = true;
						showOptions(new Position(i));

					} else
					{
						selectedPiece = null;
					}
				} else
				{
					if (board.isValidMove(selectedPiece, new Position(i)))
					{
						Position previousPosition = selectedPiece.currentPosition;
						board.movePiece(selectedPiece, new Position(i));
						updateAtPosition(previousPosition);
						updateAtPosition(selectedPiece.currentPosition);
						isWhiteTurn = !isWhiteTurn;
					}

					selectedPiece = null;
					isPieceSelected = false;
					clearOptions();
				}

				// TODO fix cananypiece move logic
				if (!board.canAnyPieceMove(isWhiteTurn))
				{
					if (board.isKingAttacked(isWhiteTurn))
						endCheckGame();
					else
						endStaleGame();
				}

				break;
			}

		}
	}

}
