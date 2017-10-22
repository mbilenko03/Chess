package game;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece
{

	public Bishop(Position position, Boolean color)
	{
		super(position, color);

	}

	@Override
	public String getIconName()
	{
		return "Bishop";
	}

	@Override
	public Boolean canMove(Position position)
	{
		Position currentPosition = this.currentPosition;

		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		int newX = position.getX();
		int newY = position.getY();

		// can move diagonally
		for (int i = 1; i < 8; i++)
		{
			if ((newY == currentY - i && newX == currentX - i) || (newY == currentY + i && newX == currentX + i)
					|| (newY == currentY - i && newX == currentX + i) || (newY == currentY + i && newX == currentX - i))
				return true;
		}

		return false;
	}

	@Override
	public List<Position> getMoves()
	{
		List<Position> moves = new ArrayList<Position>();

		// figure out every possible move regardless of surroundings

		return moves;
	}

}
