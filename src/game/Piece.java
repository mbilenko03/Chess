package game;

abstract public class Piece
{
	public int currentPosition;

	public Piece(int position)
	{
		currentPosition = position;
	}

	public void moveTo(int position)
	{
		if (canMoveTo(position))
			currentPosition = position;
		else
			throw new IllegalArgumentException();
	}

	public abstract Boolean canMoveTo(int position);
}
