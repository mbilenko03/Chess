package gui;

import game.Piece;

public class Board
{
	Piece[] whitePieces = new Piece[16];
	Piece[] blackPieces = new Piece[16];

	public Board()
	{
		setInitialPositions();
	}

	public void setInitialPositions()
	{
		setInitialPawns();
		setInitialRooks();
		setInitialKnights();
		setInitialBishops();
		setInitialQueens();
		setInitialKings();
	}

	private void setInitialKings()
	{
		// TODO Auto-generated method stub

	}

	private void setInitialQueens()
	{
		// TODO Auto-generated method stub

	}

	private void setInitialBishops()
	{
		// TODO Auto-generated method stub

	}

	private void setInitialKnights()
	{
		// TODO Auto-generated method stub

	}

	private void setInitialRooks()
	{
		// TODO Auto-generated method stub

	}

	private void setInitialPawns()
	{
		for (int i = 0; i < 8; i++)
	}

}
