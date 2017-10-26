package game;

import java.util.ArrayList;
import java.util.List;

public class Preset
{
	public static List<Piece> getInitialPieces()
	{
		List<Piece> pieces = new ArrayList<Piece>();
		pieces.add(new Pawn(new Position("a2"), true));
		pieces.add(new Pawn(new Position("b2"), true));
		pieces.add(new Pawn(new Position("c2"), true));
		pieces.add(new Pawn(new Position("d2"), true));
		pieces.add(new Pawn(new Position("e2"), true));
		pieces.add(new Pawn(new Position("f2"), true));
		pieces.add(new Pawn(new Position("g2"), true));
		pieces.add(new Pawn(new Position("h2"), true));

		pieces.add(new Knight(new Position("b1"), true));
		pieces.add(new Knight(new Position("g1"), true));

		pieces.add(new Bishop(new Position("c1"), true));
		pieces.add(new Bishop(new Position("f1"), true));

		pieces.add(new Rook(new Position("a1"), true));
		pieces.add(new Rook(new Position("h1"), true));

		pieces.add(new Queen(new Position("d1"), true));

		pieces.add(new King(new Position("e1"), true));

		// Black Piece
		pieces.add(new Pawn(new Position("a7"), false));
		pieces.add(new Pawn(new Position("b7"), false));
		pieces.add(new Pawn(new Position("c7"), false));
		pieces.add(new Pawn(new Position("d7"), false));
		pieces.add(new Pawn(new Position("e7"), false));
		pieces.add(new Pawn(new Position("f7"), false));
		pieces.add(new Pawn(new Position("g7"), false));
		pieces.add(new Pawn(new Position("h7"), false));

		pieces.add(new Knight(new Position("b8"), false));
		pieces.add(new Knight(new Position("g8"), false));

		pieces.add(new Bishop(new Position("c8"), false));
		pieces.add(new Bishop(new Position("f8"), false));

		pieces.add(new Rook(new Position("a8"), false));
		pieces.add(new Rook(new Position("h8"), false));

		pieces.add(new Queen(new Position("d8"), false));

		pieces.add(new King(new Position("e8"), false));

		return pieces;
	}

	public static List<Piece> getStaleMate()
	{
		List<Piece> pieces = new ArrayList<Piece>();

		pieces.add(new King(new Position("f8"), false));
		pieces.add(new Pawn(new Position("f7"), true));
		pieces.add(new King(new Position("f5"), true));

		return pieces;
	}
}
