package game;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	Piece[] pieces = new Piece[64];

	public Board()
	{
		setInitialPositions();
	}

	public void setInitialPositions()
	{
		// White Piece
		this.addPiece(new Pawn(new Position("a2"), true));
		this.addPiece(new Pawn(new Position("b2"), true));
		this.addPiece(new Pawn(new Position("c2"), true));
		this.addPiece(new Pawn(new Position("d2"), true));
		this.addPiece(new Pawn(new Position("e2"), true));
		this.addPiece(new Pawn(new Position("f2"), true));
		this.addPiece(new Pawn(new Position("g2"), true));
		this.addPiece(new Pawn(new Position("h2"), true));

		this.addPiece(new Knight(new Position("b1"), true));
		this.addPiece(new Knight(new Position("g1"), true));

		this.addPiece(new Bishop(new Position("c1"), true));
		this.addPiece(new Bishop(new Position("f1"), true));

		this.addPiece(new Rook(new Position("a1"), true));
		this.addPiece(new Rook(new Position("h1"), true));

		this.addPiece(new Queen(new Position("d1"), true));

		this.addPiece(new King(new Position("e1"), true));

		// Black Piece
		this.addPiece(new Pawn(new Position("a7"), false));
		this.addPiece(new Pawn(new Position("b7"), false));
		this.addPiece(new Pawn(new Position("c7"), false));
		this.addPiece(new Pawn(new Position("d7"), false));
		this.addPiece(new Pawn(new Position("e7"), false));
		this.addPiece(new Pawn(new Position("f7"), false));
		this.addPiece(new Pawn(new Position("g7"), false));
		this.addPiece(new Pawn(new Position("h7"), false));

		this.addPiece(new Knight(new Position("b8"), false));
		this.addPiece(new Knight(new Position("g8"), false));

		this.addPiece(new Bishop(new Position("c8"), false));
		this.addPiece(new Bishop(new Position("f8"), false));

		this.addPiece(new Rook(new Position("a8"), false));
		this.addPiece(new Rook(new Position("h8"), false));

		this.addPiece(new Queen(new Position("d8"), false));

		this.addPiece(new King(new Position("e8"), false));
	}

	public void addPiece(Piece piece)
	{
		if (getPiece(piece.currentPosition) == null)
		{
			pieces[piece.currentPosition.getIndex()] = piece;
		} else
			throw new IllegalArgumentException();
	}

	public Piece getPiece(Position position)
	{
		return pieces[position.getIndex()];
	}

	public void movePiece(Piece piece, Position position)
	{
		if (isValidMove(piece, position))
		{
			pieces[piece.currentPosition.getIndex()] = null;
			pieces[position.getIndex()] = piece;
			piece.moveTo(position);
		} else
			throw new IllegalArgumentException();
	}

	public Boolean isValidMove(Piece piece, Position position)
	{
		Piece newPlace = pieces[position.getIndex()];

		Position currentPosition = piece.currentPosition;

		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		int newX = position.getX();
		int newY = position.getY();

		// Check if piece can move
		if (piece.canMove(position))

			// Check if there is no piece in the way
			if ((newPlace == null))
			{
				// Rules for pawn:
				if (piece instanceof Pawn)
				{
					// Make sure there are no pieces in the way
					if (piece.pieceColor)
						if (pieces[(new Position(currentX, currentY - 1)).getIndex()] != null)
							return false;
					if (!piece.pieceColor)
						if (pieces[(new Position(currentX, currentY + 1)).getIndex()] != null)
							return false;
				}

				if (piece instanceof Rook)
				{
					// if moving horrizontally
					// check if null for current x to new x exclusively

					if (currentY == newY)
					{
						if (currentX < newX)
						{
							for (int i = currentX + 1; i < newX; i++)
							{
								if (pieces[(new Position(i, currentY)).getIndex()] != null)
									return false;
							}
						} else
						{
							for (int i = newX + 1; i < currentX; i++)
							{
								if (pieces[(new Position(i, currentY)).getIndex()] != null)
									return false;
							}
						}
					}

					// if moving vertically
					// check if null for current y to new y exclusively

					if (currentX == newX)
					{
						if (currentY < newY)
						{
							for (int i = currentY + 1; i < newY; i++)
							{
								if (pieces[(new Position(currentX, i)).getIndex()] != null)
									return false;
							}
						} else
						{
							for (int i = newY + 1; i < currentY; i++)
							{
								if (pieces[(new Position(currentX, i)).getIndex()] != null)
									return false;
							}
						}
					}
				}

				// if (piece instanceof Bishop)
				// {
				//
				// if (currentX < newX && currentY < newY)
				// {
				// for (int i = currentX + 1; i < newX; i++)
				// {
				// int j = currentY + 1 + i;
				// if (pieces[(new Position(i, j)).getIndex()] != null)
				// return false;
				// }
				// } else if (currentX > newX && currentY > newY)
				// {
				// for (int i = newX + 1; i < currentX; i++)
				// {
				// int j = currentY - 1 - i;
				// if (pieces[(new Position(i, j)).getIndex()] != null)
				// return false;
				// }
				// } else if (currentX > newX && currentY < newY)
				// {
				// for (int i = newX + 1; i < currentX; i++)
				// {
				// int j = currentY + 1 + i;
				// if (pieces[(new Position(i, j)).getIndex()] != null)
				// return false;
				// }
				// } else if (currentX < newX && currentY > newY)
				// {
				// for (int i = newX + 1; i < currentX; i++)
				// {
				// int j = currentY - 1 - i;
				// if (pieces[(new Position(i, j)).getIndex()] != null)
				// return false;
				// }
				// }
				// }

				return true;
			}

		// Check if piece can take
		if (piece.canTake(position))
		{
			// Check to make sure there exists a piece, it is of opposite color, and it is
			// not a king
			if (newPlace != null && newPlace.pieceColor != piece.pieceColor && !(newPlace instanceof King))
				return true;
		}

		return false;
	}

	public List<Position> getMoves(Position position)
	{
		List<Position> moves = new ArrayList<Position>();

		Piece piece = getPiece(position);

		moves = piece.getMoves();

		// edit moves according to surroundings
		if (piece instanceof Pawn)
		{
		}

		return moves;
	}

}
