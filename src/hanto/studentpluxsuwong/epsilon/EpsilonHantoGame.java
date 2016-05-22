/*
 * Epsilon Hanto Game Implementation.
 */
package hanto.studentpluxsuwong.epsilon;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.HantoPieceType.CRAB;
import static hanto.common.HantoPieceType.HORSE;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentpluxsuwong.common.BaseHantoGame;
import hanto.studentpluxsuwong.common.HantoCoordinateImpl;
import hanto.tournament.HantoMoveRecord;

/**
 * @author pluxsuwong
 * Epsilon Hanto Game class
 */
public class EpsilonHantoGame extends BaseHantoGame
{
	public EpsilonHantoGame()
	{
		factory = EpsilonHantoPieceFactory.getInstance();
	}
	
	/**
	 * Method that checks to see if the piece has reached its maximum allowed quota of pieces
	 * @param count current number of the type of piece on the board
	 * @return true if the quota has been reached
	 */
	@Override
	protected boolean atLimit(HantoPieceType pieceType, int count)
	{
		boolean atLimit = false;
		if (pieceType == BUTTERFLY) {
			atLimit = (count >= 1);
		} else if (pieceType == SPARROW) {
			atLimit = (count >= 2);
		} else if (pieceType == CRAB) {
			atLimit = (count >= 6);
		} else if (pieceType == HORSE) {
			atLimit = (count >= 4);
		}
		
		return atLimit;
	}

	@Override
	protected void checkIfCanPlacePieceAt(HantoCoordinateImpl dest) throws HantoException
	{
		if (board.hasOccupiedTile(dest)) {
			throw new HantoException("Destination tile is occupied, cannot place");
		}
		if (dest.adjacentNeighborNum(null, board) == 0) {
			throw new HantoException("Destination tile has no neighbors, cannot place");
		}
		if (dest.hasBadNeighbor(currentPlayer, board)) {
			throw new HantoException("Destination tile has adjacent enemy, cannot place");
		}
	}

	/** Return the super highly recommended next move the player should take
	 * @param myColor the player
	 * @return move record indicating the move
	 */
	public HantoMoveRecord getNextMove(HantoPlayerColor myColor)
	{
		HantoMoveRecord theMove = board.getAnyPossibleMove(myColor, turn); 
		HantoMoveRecord resignMove = new HantoMoveRecord(null, null, null);
		return (theMove == null) ? resignMove : theMove;
	}
}
