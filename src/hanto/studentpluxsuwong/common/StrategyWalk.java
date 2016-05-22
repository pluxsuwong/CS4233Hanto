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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hanto.tournament.HantoMoveRecord;

/**
 * @author pluxsuwong
 * Concrete implementation of the walking movement strategy
 */
public class StrategyWalk implements MoveStrategy
{
	private int distance;

	/** Constructor for the walking strategy
	 * @param distance the maximum number of steps that can be taken
	 */
	public StrategyWalk(int distance)
	{
		this.distance = distance;
	}
	
	@Override
	public boolean isValidFor(HantoCoordinateImpl src, HantoCoordinateImpl dest, Board board)
	{
		Set<HantoCoordinateImpl> closed = new HashSet<HantoCoordinateImpl>();
		Set<HantoCoordinateImpl> open = new HashSet<HantoCoordinateImpl>();
		open.add(src);
		for (int stepsTaken = 0; stepsTaken < distance; stepsTaken++) {
			Set<HantoCoordinateImpl> openBuf = new HashSet<HantoCoordinateImpl>();
			for (HantoCoordinateImpl openTile : open) {
				List<HantoCoordinateImpl> slidableNeighbors = openTile.slidableNeighbors(board);
				for (HantoCoordinateImpl slidableN : slidableNeighbors) {
					if (!closed.contains(slidableN) && !open.contains(slidableN)) {
						openBuf.add(slidableN);
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
		List<HantoCoordinateImpl> slidableNeighbors = src.slidableNeighbors(board);
		for (HantoCoordinateImpl slidableN : slidableNeighbors) {
			if (board.allowsMoveWithoutBreaking(src, slidableN)) {
				return new HantoMoveRecord(board.getPieceTypeAt(src), src, slidableN);
			}
		}
		return null;
	}

}
