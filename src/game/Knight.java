package game;

import java.util.List;

public class Knight extends Piece
{

	public Knight(Position position, Boolean color)
	{
		super(position, color);

	}

	@Override
	public String getIconName()
	{
		return "Knight";
	}

	@Override
	public Boolean canMove(Position position)
	{
		Position currentPosition = this.currentPosition;

		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		int newX = position.getX();
		int newY = position.getY();

		if ((newY == currentY + 2 && newX == currentX + 1) || (newY == currentY + 2 && newX == currentX - 1)
				|| (newY == currentY - 2 && newX == currentX + 1) || (newY == currentY - 2 && newX == currentX - 1)
				|| (newY == currentY + 1 && newX == currentX + 2) || (newY == currentY + 1 && newX == currentX - 2)
				|| (newY == currentY - 1 && newX == currentX + 2) || (newY == currentY - 1 && newX == currentX - 2))
			return true;

		return false;
	}

	@Override
	public List<Position> getMoves()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
