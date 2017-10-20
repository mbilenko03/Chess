package game;

public class Queen extends Piece
{

	public Queen(Position position, Boolean color)
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
		return "Queen";
	}

}
