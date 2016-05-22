/**
 * 
 */
package hanto.studentpluxsuwong.gamma;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.studentpluxsuwong.common.BaseHantoGame;
import hanto.studentpluxsuwong.common.HantoCoordinateImpl;

/**
 * @author pluxsuwong
 * The GammaHantoGame implementation
 */
/**
 * @author pluxsuwong
 *
 */
public class GammaHantoGame extends BaseHantoGame
{
	public GammaHantoGame()
	{
		factory = GammaHantoPieceFactory.getInstance();
		turnLimit = 20;
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
			atLimit = (count >= 5);
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

}
