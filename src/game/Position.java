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

	public Position(String coor)
	{
		_position = coorToPosition(coor);
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

	private static int coorToPosition(String coor)
	{
		coor = coor.toUpperCase();
		char letter = coor.charAt(0);
		char number = coor.charAt(1);

		int row = letter - 'A';
		int column = -(number - '0') + 8;

		return convertToPosition(row, column);
	}

	public Boolean isSamePosition(Position other)
	{
		return _position == other._position;
	}

	public int getIndex()
	{
		return _position;
	}

	public int getXCoor(int pos)
	{
		return pos / 8;
	}

	public int getYCoor(int pos)
	{
		return pos % 8;
	}

}
