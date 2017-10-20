package game;

public class Pawn extends Piece
{

	public Pawn(Position position, Boolean color)
	{
		super(position, color);

	}

	@Override
	public Boolean canMoveTo(Position position, Boolean color)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIconName()
	{
		return "Pawn";
	}

}
