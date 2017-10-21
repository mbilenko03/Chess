package game;

import java.util.List;

public class Queen extends Piece
{

	public Queen(Position position, Boolean color)
	{
		super(position, color);

	}

	@Override
	public String getIconName()
	{
		return "Queen";
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
