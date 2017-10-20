package game;

public class Rook extends Piece
{

	public Rook(Position position, Boolean color)
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
		return "Rook";
	}

}
