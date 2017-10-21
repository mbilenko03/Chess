package game;

import java.util.List;

public class Rook extends Piece
{

	public Rook(Position position, Boolean color)
	{
		super(position, color);

	}

	@Override
	public String getIconName()
	{
		return "Rook";
	}

	@Override
	public Boolean canMove(Position position)
	{
		Position currentPosition = this.currentPosition;

		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		int newX = position.getX();
		int newY = position.getY();

		// can move horizontally or vertically
		for (int i = 1; i < 8; i++)
		{
			if ((newY == currentY + i && newX == currentX) || (newY == currentY - i && newX == currentX)
					|| (newY == currentY && newX == currentX + i) || (newY == currentY && newX == currentX - i))
				return true;
		}

		return false;
	}

	@Override
	public List<Position> getMoves()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
