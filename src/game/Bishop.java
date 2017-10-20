package game;

public class Bishop extends Piece
{

	public Bishop(Position position, Boolean color)
	{
		super(position, color);

	}

	@Override
	public Boolean canMoveTo(Position position, Boolean color)
	{
		// logic
		return null;
	}

	@Override
	public String getIconName()
	{
		return "Bishop";
	}

}
