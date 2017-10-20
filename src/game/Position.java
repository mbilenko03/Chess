package game;

public class Position
{
	public int _position;
	final static int SIZE = 8;

	public Position(int position)
	{
		_position = position;
	}

	public Position(int x, int y)
	{
		_position = convertToPosition(x, y);
	}

	public int getX()
	{
		return _position % SIZE;
	}

	public int getY()
	{
		return _position / SIZE;
	}

	public static int convertToPosition(int x, int y)
	{
		return y * SIZE + x;
	}

	public Boolean isSamePosition(Position other)
	{
		return _position == other._position;
	}

}
