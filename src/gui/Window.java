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
	// TODO fix red checking

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

		add(game);

		setVisible(true);

		updateBoard();

	}

	// Method to create checker board pattern of buttons
	public void initCheckerBoard()
	{
		for (int i = 0; i < SIZE * SIZE; i++)
		{
			// Populate buttons to board
			grid[i] = new JButton();
			game.add(grid[i]);
			grid[i].addActionListener(this);

			setCheckerBoardColor(i);

		}
	}

	// Method to find the checker board color at a position
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

	// Method to update the icon of a button at a position
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

	// Method to show the options of a position
	public void showOptions(Position position)
	{
		possibleChoices = board.getMoves(position);

		if (possibleChoices != null)
			for (Position element : possibleChoices)
			{
				grid[element.getIndex()].setBackground(Color.BLUE);
			}
	}

	// Method to clear the options of a position
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

	// Method to update images on board
	public void updateBoard()
	{
		// Iterate through all buttons
		for (int i = 0; i < SIZE * SIZE; i++)
		{
			updateAtPosition(new Position(i));
		}

	}

	// Method to disable buttons at game end
	public void endGame()
	{
		unSelectPiece();

		for (int i = 0; i < SIZE * SIZE; i++)
		{
			grid[i].setEnabled(false);
		}
	}

	// Method to show stale mate message
	private void endStaleGame()
	{
		endGame();
		JOptionPane.showMessageDialog(null, "Stalemate!");
		this.dispose();
	}

	// Method to show check mate message
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

	// Method that attempts to select the piece
	private void selectPiece(int i)
	{
		// Check if selected piece is not empty and correct color
		if (board.getPiece(new Position(i)) != null && board.getPiece(new Position(i)).pieceColor == isWhiteTurn)
		{
			// Get the selected piece
			selectedPiece = board.getPiece(new Position(i));

			// Set that a piece is selected to true
			isPieceSelected = true;

			// Display options
			showOptions(new Position(i));
		}
	}

	// Method that disables the selection
	private void unSelectPiece()
	{
		// Reset the selected piece
		selectedPiece = null;

		// Set that piece is not selected
		isPieceSelected = false;

		// Clear the visible options
		clearOptions();
	}

	// Method to show a king is checked
	private void showKingChecked(Boolean isWhite)
	{
		if (isWhite)
		{
			grid[board.whiteKing.currentPosition.getIndex()].setBackground(Color.RED);

		}
		if (!isWhite)
		{
			grid[board.blackKing.currentPosition.getIndex()].setBackground(Color.RED);
		}
	}

	// Method to revert king is checked
	private void revertShowKingChecked()
	{
		setCheckerBoardColor(board.whiteKing.currentPosition.getIndex());
		setCheckerBoardColor(board.blackKing.currentPosition.getIndex());
	}

	@Override
	public void actionPerformed(ActionEvent source)
	{
		for (int i = 0; i < SIZE * SIZE; i++)
		{
			// Check what piece was clicked on
			if (grid[i] == source.getSource())
			{
				// Check if piece is already selected
				if (isPieceSelected)
				{
					// Check if move is valid
					if (board.isValidMove(selectedPiece, new Position(i)))
					{
						// Save the previous position
						Position previousPosition = selectedPiece.currentPosition;

						// Move the piece
						board.movePiece(selectedPiece, new Position(i));

						// Update the board at the changed positions
						updateAtPosition(previousPosition);
						updateAtPosition(selectedPiece.currentPosition);

						// Change turn
						isWhiteTurn = !isWhiteTurn;

						revertShowKingChecked();

						// Show color of king square depending if checked
						if (board.isKingAttacked(isWhiteTurn))
							showKingChecked(isWhiteTurn);

						// Check if every piece can not move
						if (!board.canAnyPieceMove(isWhiteTurn))
						{
							// Check if king is attacked
							if (board.isKingAttacked(isWhiteTurn))
								endCheckGame();
							else
								endStaleGame();
						}
					}

					// Turn off selections
					unSelectPiece();
				}

				// Select the piece if it can
				selectPiece(i);

				break;
			}

		}
	}

}