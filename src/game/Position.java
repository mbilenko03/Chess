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
		char letter = coor.charAt(0);
		char number = coor.charAt(1);

		int row = -number + 8;
		int column = 0;

		if (letter == 'A')
			column = 0;
		if (letter == 'B')
			column = 1;
		if (letter == 'C')
			column = 2;
		if (letter == 'D')
			column = 3;
		if (letter == 'E')
			column = 4;
		if (letter == 'F')
			column = 5;
		if (letter == 'G')
			column = 6;
		if (letter == 'H')
			column = 7;

		return convertToPosition(row, column);
	}

	public Boolean isSamePosition(Position other)
	{
		return _position == other._position;
	}

}
