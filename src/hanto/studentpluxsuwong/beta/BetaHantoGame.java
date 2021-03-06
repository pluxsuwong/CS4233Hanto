/*******************************************************************************
 * This file was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Peerapat Luxsuwong
 *******************************************************************************/

package hanto.studentpluxsuwong.beta;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;

import hanto.common.*;
import hanto.studentpluxsuwong.common.BaseHantoGame;
import hanto.studentpluxsuwong.common.HantoCoordinateImpl;

/**
 * Implementation of Beta Hanto
 * @version Mar 16, 2016
 */
public class BetaHantoGame extends BaseHantoGame
{
	
	public BetaHantoGame()
	{
		factory = BetaHantoPieceFactory.getInstance();
		turnLimit = 6;
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
	}
	
}
