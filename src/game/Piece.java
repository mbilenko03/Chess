package game;

import java.util.List;

abstract public class Piece
{
	public Position currentPosition;
	public Boolean pieceColor;

	public Piece(Position position, Boolean color)
	{
		currentPosition = position;
		pieceColor = color;
	}

	public void moveTo(Position position)
	{
		if (canMoveTo(position))
			currentPosition = position;
		else
			throw new IllegalArgumentException();
	}

	public abstract Boolean canMoveTo(Position position);

	public abstract String getIconName();

	public abstract Boolean canMove(Position position);

	public Boolean canTake(Position position)
	{
		return canMoveTo(position);
	}

	public abstract List<Position> getMoves();
}
