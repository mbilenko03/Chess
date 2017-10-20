package game;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	List<Piece> whitePieces = new ArrayList<Piece>();
	List<Piece> blackPieces = new ArrayList<Piece>();

	public Board()
	{
		setInitialPositions();
	}

	public void setInitialPositions()
	{
		Pawn testPawn = new Pawn(new Position(10), true);
		this.addPiece(testPawn);
	}

	public void addPiece(Piece piece)
	{
		if (piece.pieceColor)
			whitePieces.add(piece);
		else if (!piece.pieceColor)
			blackPieces.add(piece);

	}

	public Piece getPiece(Position position)
	{
		for (Piece piece : whitePieces)
		{
			if (piece.currentPosition.isSamePosition(position))
				return piece;
		}
		for (Piece piece : blackPieces)
		{
			if (piece.currentPosition.isSamePosition(position))
				return piece;
		}

		return null;
	}

	public void movePiece()
	{

	}

	public Boolean isValidMove()
	{
		return null;
	}

}
