package game;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	// TODO Add castle
	// TODO Add En Passant
	// TODO Add Piece Promotion

	Piece[] pieces = new Piece[64];

	List<Piece> whitePieces = new ArrayList<Piece>();
	List<Piece> blackPieces = new ArrayList<Piece>();

	public King whiteKing = null;
	public King blackKing = null;

	public Board()
	{
		for (Piece element : Preset.getInitialPieces())
		{
			this.addPiece(element);
		}
	}

	// Method to add a piece to the board
	public void addPiece(Piece piece)
	{
		// Add piece if king to king reference
		if (piece instanceof King)
		{
			if (piece.pieceColor)
				whiteKing = (King) piece;
			else
				blackKing = (King) piece;
		}

		// Add piece to the board as long as spot is empty
		if (getPiece(piece.currentPosition) == null)
		{
			pieces[piece.currentPosition.getIndex()] = piece;

			// Add to list of black and white pieces
			if (piece.pieceColor)
				whitePieces.add(piece);
			else
				blackPieces.add(piece);
		} else
			throw new IllegalArgumentException();

	}

	// Method to remove a piece
	public void removePiece(Piece piece)
	{
		// Set the taken piece to empty
		pieces[piece.currentPosition.getIndex()] = null;

		// Remove it from the white and black lists
		if (piece.pieceColor)
			whitePieces.remove(piece);
		else
			blackPieces.remove(piece);
	}

	// Method to get a piece at a particular position
	public Piece getPiece(Position position)
	{
		return pieces[position.getIndex()];
	}

	// Method to move a piece to a position
	public void movePiece(Piece piece, Position position)
	{
		// Check if the move is valid
		if (isValidMove(piece, position))
		{
			// When taking a piece remove it
			if (pieces[position.getIndex()] != null)
				removePiece(pieces[position.getIndex()]);

			// Set old position to empty
			pieces[piece.currentPosition.getIndex()] = null;

			// Set new position to piece
			pieces[position.getIndex()] = piece;

			// Tell the piece to move position
			piece.moveTo(position);
		} else
			throw new IllegalArgumentException();
	}

	// Method to temporarily move a piece without checking if valid
	private void tempMove(Piece piece, Position position)
	{
		// Set new and old position to empty
		pieces[position.getIndex()] = null;
		pieces[piece.currentPosition.getIndex()] = null;

		// Set new position to piece
		pieces[position.getIndex()] = piece;

		// Tell the piece to change position (regardless if valid)
		piece.setPosition(position);
	}

	// Method to check if a move is valid
	public Boolean isValidMove(Piece piece, Position position)
	{
		// Piece that is going to be replaced
		Piece newPlace = pieces[position.getIndex()];

		// Position before move
		Position currentPosition = piece.currentPosition;

		// Ask the piece if it can move
		if (piece.canMove(position))

			// Make sure it is moving to empty square
			if ((newPlace == null))
			{
				// Checks if the piece can move in relation to its surroundings
				if (!canMove(piece, position))
					return false;

				// Temporarily move the piece to make sure it does not result to check
				tempMove(piece, position);
				Boolean invalid = isKingAttacked(piece.pieceColor);
				tempMove(piece, currentPosition);
				if (invalid)
					return false;

				return true;
			}

		// Ask the piece if it can take
		if (piece.canTake(position))
		{
			// Make sure it is taking a piece
			if (newPlace != null && piece.pieceColor != newPlace.pieceColor && !(newPlace instanceof King))
			{
				// Check if the piece can take in relation to its surroundings
				if (!canTake(piece, position))
					return false;

				// Temporarily take the piece to make sure it does not result to check
				Piece takedPiece = getPiece(position);
				tempMove(piece, position);
				Boolean invalid = isKingAttacked(piece.pieceColor);
				tempMove(piece, currentPosition);
				tempMove(takedPiece, position);
				if (invalid)
					return false;

				return true;
			}
		}

		return false;
	}

	// Method to check if a piece can move in relation to other pieces
	public Boolean canMove(Piece piece, Position position)
	{
		Position currentPosition = piece.currentPosition;

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

		return true;
	}

	// Method to check if a piece can take in relation to other pieces
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

	// Method to check if a pawn move is valid
	public Boolean isPawnMoveValid(Position currentPosition, Position newPosition, Boolean pieceColor)
	{
		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		// When moving two squares, check to make sure it doesn't jump over piece
		if (pieceColor)
			if (pieces[(new Position(currentX, currentY - 1)).getIndex()] != null)
				return false;
		if (!pieceColor)
			if (pieces[(new Position(currentX, currentY + 1)).getIndex()] != null)
				return false;

		return true;
	}

	// Method to check if a rook move is valid
	public Boolean isRookMoveValid(Position currentPosition, Position newPosition, Boolean pieceColor)
	{
		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		int newX = newPosition.getX();
		int newY = newPosition.getY();

		// Make sure it does not jump over any pieces
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

	// Method to check if a bishop move is valid
	public Boolean isBishopMoveValid(Position currentPosition, Position newPosition, Boolean pieceColor)
	{
		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		int newX = newPosition.getX();
		int newY = newPosition.getY();

		int difference = Math.abs(newX - currentX);

		// Make sure it does not jump over any pieces
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

	// Method to check if a particular piece is being attacked
	public Boolean isPieceAttacked(Piece piece)
	{
		// Iterate through the opposite color pieces and check if it can take the piece
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

	// Method to check if the king is being attacked
	public Boolean isKingAttacked(Boolean isWhite)
	{
		// Call the isPieceAttacked method on the king depending on color
		if (isWhite)
			return isPieceAttacked(whiteKing);
		else
			return isPieceAttacked(blackKing);
	}

	// Method to check if a piece has any moves
	public Boolean canPieceMove(Piece piece)
	{
		// Get the moves and check if the result is empty
		List<Position> moves = getMoves(piece.currentPosition);
		if (moves.isEmpty())
			return true;
		else
			return false;
	}

	// Method to check if all the pieces of a color can move
	public Boolean canAnyPieceMove(Boolean isWhite)
	{
		// Iterate with respect to color and check if any piece can move
		if (isWhite)
		{
			for (Piece element : whitePieces)
			{
				if (!getMoves(element.currentPosition).isEmpty())
					return true;

			}
		} else
		{
			for (Piece element : blackPieces)
			{
				if (!getMoves(element.currentPosition).isEmpty())
					return true;
			}
		}

		return false;
	}

	// Method which gets the moves of a piece at a position
	public List<Position> getMoves(Position position)
	{
		List<Position> moves = new ArrayList<Position>();

		Piece piece = getPiece(position);

		if (piece == null)
			return moves;

		moves = piece.getMoves();

		// Iterate through all the positions to check if the piece can move there
		for (int i = 0; i < 64; i++)
		{
			if (isValidMove(piece, new Position(i)) && moves != null)
				moves.add(new Position(i));
		}

		return moves;
	}

}