package game;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	Piece[] pieces = new Piece[64];

	List<Piece> whitePieces = new ArrayList<Piece>();
	List<Piece> blackPieces = new ArrayList<Piece>();

	King whiteKing = null;
	King blackKing = null;

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
		if (piece instanceof King)
		{
			if (piece.pieceColor)
				whiteKing = (King) piece;
			else
				blackKing = (King) piece;
		}
		if (getPiece(piece.currentPosition) == null)
		{
			pieces[piece.currentPosition.getIndex()] = piece;
			if (piece.pieceColor)
				whitePieces.add(piece);
			else
				blackPieces.add(piece);
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

	private void tempMove(Piece piece, Position position)
	{
		pieces[piece.currentPosition.getIndex()] = null;
		pieces[position.getIndex()] = piece;
		piece.setPosition(position);
	}

	public Boolean isValidMove(Piece piece, Position position)
	{
		Piece newPlace = pieces[position.getIndex()];

		Position currentPosition = piece.currentPosition;

		// Check if piece can move
		if (piece.canMove(position))
			if ((newPlace == null))
			{
				// TODO check if king is checked
				// if it is only allow move to position to uncheck it
				// if no moves to uncheck end game

				// Rules for pieces:
				if (piece instanceof Pawn)
				{
					if (!isPawnMoveValid(currentPosition, position, piece.pieceColor))
						return false;
				}

				if (piece instanceof Rook)
				{
					if (!isRookMoveValid(currentPosition, position, piece.pieceColor))
						return false;
				}

				if (piece instanceof Bishop)
				{
					if (!isBishopMoveValid(currentPosition, position, piece.pieceColor))
						return false;
				}

				if (piece instanceof Queen)
				{
					if (!isBishopMoveValid(currentPosition, position, piece.pieceColor))
						return false;
					if (!isRookMoveValid(currentPosition, position, piece.pieceColor))
						return false;
				}

				tempMove(piece, position);
				Boolean invalid = isKingAttacked(piece.pieceColor);
				tempMove(piece, currentPosition);
				if (invalid)
					return false;

				return true;
			}

		// Check if piece can take
		if (piece.canTake(position))
		{
			// Check to make sure there exists a piece, it is of opposite color, and it is
			// not a king
			if (newPlace != null)
			{
				if (!canTake(piece, position))
					return false;

				if (newPlace != null && newPlace.pieceColor != piece.pieceColor && !(newPlace instanceof King))
					return true;
			}
		}

		return false;
	}

	public Boolean isPawnMoveValid(Position currentPosition, Position newPosition, Boolean pieceColor)
	{
		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		if (pieceColor)
			if (pieces[(new Position(currentX, currentY - 1)).getIndex()] != null)
				return false;
		if (!pieceColor)
			if (pieces[(new Position(currentX, currentY + 1)).getIndex()] != null)
				return false;

		return true;
	}

	public Boolean isRookMoveValid(Position currentPosition, Position newPosition, Boolean pieceColor)
	{
		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		int newX = newPosition.getX();
		int newY = newPosition.getY();

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

		return true;
	}

	public Boolean isBishopMoveValid(Position currentPosition, Position newPosition, Boolean pieceColor)
	{
		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		int newX = newPosition.getX();
		int newY = newPosition.getY();

		int difference = Math.abs(newX - currentX);

		if (currentX < newX && currentY < newY)
		{
			for (int i = 1; i < difference; i++)
			{
				if (pieces[(new Position(currentX + i, currentY + i)).getIndex()] != null)
					return false;
			}
		} else if (currentX > newX && currentY > newY)
		{
			for (int i = 1; i < difference; i++)
			{
				if (pieces[(new Position(currentX - i, currentY - i)).getIndex()] != null)
					return false;
			}
		} else if (currentX > newX && currentY < newY)
		{
			for (int i = 1; i < difference; i++)
			{
				if (pieces[(new Position(currentX - i, currentY + i)).getIndex()] != null)
					return false;
			}
		} else if (currentX < newX && currentY > newY)
		{
			for (int i = 1; i < difference; i++)
			{
				if (pieces[(new Position(currentX + i, currentY - i)).getIndex()] != null)
					return false;
			}
		}

		return true;
	}

	public Boolean canTake(Piece piece, Position position)
	{
		Piece newPlace = pieces[position.getIndex()];
		Position currentPosition = piece.currentPosition;

		if (!piece.canTake(position))
			return false;

		if (piece.pieceColor == newPlace.pieceColor)
			return false;

		if (piece instanceof Rook)
		{
			if (!isRookMoveValid(currentPosition, position, piece.pieceColor))
				return false;
		}

		if (piece instanceof Bishop)
		{
			if (!isBishopMoveValid(currentPosition, position, piece.pieceColor))
				return false;
		}

		if (piece instanceof Queen)
		{
			if (!isBishopMoveValid(currentPosition, position, piece.pieceColor))
				return false;
			if (!isRookMoveValid(currentPosition, position, piece.pieceColor))
				return false;
		}

		return true;
	}

	public Boolean isPieceAttacked(Piece piece)
	{
		if (piece.pieceColor)
		{
			for (Piece element : blackPieces)
			{
				if (canTake(element, piece.currentPosition))
					return true;
			}
		}

		if (!piece.pieceColor)
		{
			for (Piece element : whitePieces)
			{
				if (canTake(element, piece.currentPosition))
					return true;
			}
		}

		return false;
	}

	public Boolean isKingAttacked(Boolean isWhite)
	{
		if (isWhite)
			return isPieceAttacked(whiteKing);
		else
			return isPieceAttacked(blackKing);
	}

	public Boolean canPieceMove(Piece piece)
	{
		List<Position> moves = getMoves(piece.currentPosition);
		if (moves.isEmpty())
			return true;
		else
			return false;
	}

	public Boolean isKingCheckMate(Boolean isWhite)
	{
		if (isWhite)
		{
			if (isKingAttacked(isWhite) && !canPieceMove(whiteKing))
				return true;
			else
				return false;
		} else
		{
			if (isKingAttacked(isWhite) && !canPieceMove(blackKing))
				return true;
			else
				return false;
		}

	}

	public Boolean isStaleMate(Boolean isWhite)
	{
		List<Position> moves = new ArrayList<Position>();
		if (isWhite)
		{
			for (Piece element : whitePieces)
			{
				moves.addAll(getMoves(element.currentPosition));
			}

			if (moves.isEmpty())
				return true;
		} else
		{
			for (Piece element : blackPieces)
			{
				moves.addAll(getMoves(element.currentPosition));
			}

			if (moves.isEmpty())
				return true;
		}

		return false;
	}

	public List<Position> getMoves(Position position)
	{
		List<Position> moves = new ArrayList<Position>();

		Piece piece = getPiece(position);

		moves = piece.getMoves();

		for (int i = 0; i < 64; i++)
		{
			if (isValidMove(piece, new Position(i)) && moves != null)
				moves.add(new Position(i));
		}

		return moves;
	}

}
