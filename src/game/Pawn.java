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
		Position currentPosition = this.currentPosition;

		/*
		 * is white or black if white if in row 2 check if position is equal to
		 * currentPosition +2 column
		 * 
		 * check if row+1
		 */

		return null;
	}

	@Override
	public String getIconName()
	{
		return "Pawn";
	}

}
