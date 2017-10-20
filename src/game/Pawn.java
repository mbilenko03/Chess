package game;

import java.util.List;

public class Pawn extends Piece
{

	public Pawn(Position position, Boolean color)
	{
		super(position, color);

	}

	@Override
	public Boolean canMoveTo(Position position)
	{
		Position currentPosition = this.currentPosition;

		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		int newX = position.getX();
		int newY = position.getY();

		// if white
		if (this.pieceColor)
		{
			if (currentY == 2)
			{
				if (newY == currentY - 2 || newY == currentY - 1)
					return true;
			}
		}

		/*
		 * is white or black if white if in row 2 check if position is equal to
		 * currentPosition +2 column
		 * 
		 * check if row+1
		 */

		return false;
	}

	@Override
	public String getIconName()
	{
		return "Pawn";
	}

	@Override
	public List<Position> getMoves()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
