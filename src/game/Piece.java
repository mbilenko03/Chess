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
		if (canMove(position) || canTake(position))
			setPosition(position);
		else
			throw new IllegalArgumentException();
	}

	public void setPosition(Position position)
	{
		currentPosition = position;
	}

	public abstract String getIconName();

	public abstract Boolean canMove(Position position);

	public Boolean canTake(Position position)
	{
		return canMove(position);
	}

	public abstract List<Position> getMoves();
}
