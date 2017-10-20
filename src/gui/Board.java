package gui;

import game.Pawn;
import game.Piece;

public class Board
{
	Piece[] whitePieces = new Piece[16];
	Piece[] blackPieces = new Piece[16];

	public Board()
	{
		whitePieces[0] = new Pawn(0, null);
	}

	public void setInitialPositions() 
	{
		
	}

}
