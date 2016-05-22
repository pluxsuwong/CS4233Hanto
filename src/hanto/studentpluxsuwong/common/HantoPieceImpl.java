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

import hanto.common.*;
import hanto.tournament.HantoMoveRecord;

/**
 * Implementation of the HantoPiece.
 * @version Mar 2,2016
 */
public class HantoPieceImpl implements HantoPiece
{
	private final HantoPlayerColor color;
	private final HantoPieceType type;
	private final MoveStrategy moveStrategy;
	
	/**
	 * Deafault constructor
	 * @param color the piece color
	 * @param type the piece type
	 * @param moveStrategy the implemented strategy to be used for the piece
	 */
	public HantoPieceImpl(HantoPlayerColor color, HantoPieceType type, MoveStrategy moveStrategy)
	{
		this.color = color;
		this.type = type;
		this.moveStrategy = moveStrategy;
	}
	/*
	 * @see hanto.common.HantoPiece#getColor()
	 */
	@Override
	public HantoPlayerColor getColor()
	{
		return color;
	}

	/*
	 * @see hanto.common.HantoPiece#getType()
	 */
	@Override
	public HantoPieceType getType()
	{
		return type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof HantoPieceImpl)) {
			return false;
		}
		final HantoPieceImpl other = (HantoPieceImpl) obj;
		if (color != other.color) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "[color=" + color + ", type=" + type + "]\n";
	}
	
	/** Return true if the move is possible with the implemented move strategy
	 * @param src the tile to move from
	 * @param dest the tile to move to
	 * @param board the board state
	 * @return true if the move is possible
	 */
	public boolean hasValidMove(HantoCoordinateImpl src, HantoCoordinateImpl dest, Board board)
	{
		return moveStrategy.isValidFor(src, dest, board);
	}
	
	/** Return a move record indicating the player's possible move using the provided tile
	 * @param src the provided tile
	 * @param board the state of the board
	 * @return a possible move record or null if not
	 */
	public HantoMoveRecord aPossibleMove(HantoCoordinateImpl src, Board board)
	{
		return moveStrategy.aPossibleMove(src, board);
	}
	
}
