/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentpluxsuwong.delta;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.HantoPieceType.CRAB;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.studentpluxsuwong.common.BaseHantoGame;
import hanto.studentpluxsuwong.common.HantoCoordinateImpl;

/**
 * @author pluxsuwong
 * Delta implemntation of the BaseHantoGame
 */
public class DeltaHantoGame extends BaseHantoGame
{
	public DeltaHantoGame()
	{
		factory = DeltaHantoPieceFactory.getInstance();
		validateResignation = false;
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
			atLimit = (count >= 4);
		} else if (pieceType == CRAB) {
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
}
