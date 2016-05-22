/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentpluxsuwong.common;

import hanto.tournament.HantoMoveRecord;

/**
 * @author pluxsuwong
 * MoveStrategy Interface
 */
public interface MoveStrategy
{
	/** Return true if the proposed move is possible for the move strategy
	 * @param src the tile to move from
	 * @param dest the tile to move to
	 * @param board the board state
	 * @return true if the proposed move is possible
	 */
	boolean isValidFor(HantoCoordinateImpl src, HantoCoordinateImpl dest, Board board);
	
	/** Return a move record indicating the player's possible move using the provided tile
	 * @param src the provided tile
	 * @param board the state of the board
	 * @return a possible move record or null if not
	 */
	HantoMoveRecord aPossibleMove(HantoCoordinateImpl src, Board board);

}
