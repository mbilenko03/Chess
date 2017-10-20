package game;

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
		if (canMoveTo(position, pieceColor))
			currentPosition = position;
		else
			throw new IllegalArgumentException();
	}

	public abstract Boolean canMoveTo(Position position, Boolean color);

	public abstract String getIconName();
}
