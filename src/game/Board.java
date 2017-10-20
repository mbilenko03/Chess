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
		// piece can only move to null or opposite color except king
		Piece takePiece = pieces[position.getIndex()];

		if (piece.canMove(position))
			if ((takePiece == null || takePiece.pieceColor != piece.pieceColor && !(takePiece instanceof King)))
				return true;

		return false;
	}

	public List<Position> getMoves(Position position)
	{
		List<Position> moves = new ArrayList<Position>();

		Piece piece = getPiece(position);

		moves = piece.getMoves();

		// edit moves according to surroundings

		return moves;
	}

}
