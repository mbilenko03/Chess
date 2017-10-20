package game;

public class King extends Piece
{

	public King(Position position, Boolean color)
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
		return "King";
	}

}
