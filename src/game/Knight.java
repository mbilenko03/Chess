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
	public List<Position> getMoves()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean canMove(Position position)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
