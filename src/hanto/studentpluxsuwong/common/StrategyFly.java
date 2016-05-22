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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hanto.tournament.HantoMoveRecord;

/**
 * @author pluxsuwong
 * Concrete implementation of the flying movement strategy
 */
public class StrategyFly implements MoveStrategy
{
	private int distance;
	
	/** Constructor for the flying strategy
	 * @param distance the maximum number of steps that can be taken
	 */
	public StrategyFly(int distance)
	{
		this.distance = distance;
	}
	
	public StrategyFly()
	{
		this(-1);
	}
	
	@Override
	public boolean isValidFor(HantoCoordinateImpl src, HantoCoordinateImpl dest, Board board) 
	{
		if (distance == -1) {
			return (!board.hasOccupiedTile(dest))
					&& board.allowsMoveWithoutBreaking(src, dest);
		}
		
		Set<HantoCoordinateImpl> closed = new HashSet<HantoCoordinateImpl>();
		Set<HantoCoordinateImpl> open = new HashSet<HantoCoordinateImpl>();
		open.add(src);
		for (int stepsTaken = 0; stepsTaken < distance; stepsTaken++) {
			Set<HantoCoordinateImpl> openBuf = new HashSet<HantoCoordinateImpl>();
			for (HantoCoordinateImpl openTile : open) {
				Set<HantoCoordinateImpl> curNeighbors = openTile.getNeighbors();
				for (HantoCoordinateImpl curN : curNeighbors) {
					if (!closed.contains(curN) && !open.contains(curN)) {
						openBuf.add(curN);
					}
				}
				closed.add(openTile);
			}
			open = openBuf;
		}

		return (closed.contains(dest) || open.contains(dest))
				&& (!board.hasOccupiedTile(dest))
				&& board.allowsMoveWithoutBreaking(src, dest);
	}

	@Override
	public HantoMoveRecord aPossibleMove(HantoCoordinateImpl src, Board board)
	{
		HantoMoveRecord theMove = null;
		if (board.allowsMoveWithoutBreaking(src, null)) {
			HantoCoordinateImpl curTile = getFlyableTile(src, board);
			theMove = new HantoMoveRecord(board.getPieceTypeAt(src), src, curTile);
		}
		return theMove;
	}

	private HantoCoordinateImpl getFlyableTile(HantoCoordinateImpl src, Board board)
	{
		HantoCoordinateImpl theTile = null;
		List<HantoCoordinateImpl> closed = new ArrayList<HantoCoordinateImpl>();
		List<HantoCoordinateImpl> open = new ArrayList<HantoCoordinateImpl>();
		open.add(src);
		for (int stepsTaken = 0; stepsTaken < distance; stepsTaken++) {
			List<HantoCoordinateImpl> openBuf = new ArrayList<HantoCoordinateImpl>();
			for (HantoCoordinateImpl openTile : open) {
				Set<HantoCoordinateImpl> curNeighbors = openTile.getNeighbors();
				for (HantoCoordinateImpl curN : curNeighbors) {
					if (!closed.contains(curN) && !open.contains(curN)) {
						openBuf.add(curN);
					}
				}
				closed.add(openTile);
			}
			open = openBuf;
		}
		closed.addAll(open);
		
		for (HantoCoordinateImpl tile : closed) {
			if (!board.hasOccupiedTile(tile) && board.allowsMoveWithoutBreaking(src, tile)) {
				theTile = tile;
				break;
			}
		}

		return theTile;
	}
}
