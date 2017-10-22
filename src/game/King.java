package game;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{
	public Boolean isChecked = false;

	public King(Position position, Boolean color)
	{
		super(position, color);

	}

	@Override
	public String getIconName()
	{
		return "King";
	}

	@Override
	public Boolean canMove(Position position)
	{
		Position currentPosition = this.currentPosition;

		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();

		int newX = position.getX();
		int newY = position.getY();

		if ((newY == currentY + 1 && newX == currentX + 1) || (newY == currentY + 1 && newX == currentX - 1)
				|| (newY == currentY - 1 && newX == currentX + 1) || (newY == currentY - 1 && newX == currentX - 1)
				|| (newY == currentY + 1 && newX == currentX) || (newY == currentY - 1 && newX == currentX)
				|| (newY == currentY && newX == currentX + 1) || (newY == currentY && newX == currentX - 1))
			return true;

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
