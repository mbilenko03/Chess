package game;

import java.util.ArrayList;
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
				if ((newY == currentY - 2 && newX == currentX) || (newY == currentY - 1 && newX == currentX))
					return true;
			} else
			{
				if ((newY == currentY - 1 && newX == currentX) || (newY == currentY - 1 && newX == currentX - 1)
						|| (newY == currentY - 1 && newX == currentX + 1))
					return true;
			}
		}

		// if black
		if (!this.pieceColor)
		{
			if (currentY == 7)
			{
				if ((newY == currentY + 2 && newX == currentX) || (newY == currentY + 1 && newX == currentX))
					return true;
			} else
			{
				if ((newY == currentY + 1 && newX == currentX) || (newY == currentY + 1 && newX == currentX + 1)
						|| (newY == currentY + 1 && newX == currentX - 1))
					return true;
			}
		}

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
		List<Position> moves = new ArrayList<Position>();

		// figure out every possible move regardless of surroundings

		return moves;
	}

	@Override
	public Boolean canTake(Position position)
	{
		// diagnol stuff
		return canMoveTo(position);
	}

}
