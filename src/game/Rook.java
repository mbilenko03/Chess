package game;

import java.util.List;

public class Rook extends Piece
{

	public Rook(Position position, Boolean color)
	{
		super(position, color);

	}

	@Override
	public Boolean canMoveTo(Position position)
	{
		// logic
		return null;
	}

	@Override
	public String getIconName()
	{
		return "Rook";
	}

	@Override
	public List<Position> getMoves()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
