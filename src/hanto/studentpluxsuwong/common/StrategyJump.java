/*
 * Concrete implementation of the jump strategy.
 */
package hanto.studentpluxsuwong.common;

import java.util.Set;

import hanto.tournament.HantoMoveRecord;

/**
 * @author pluxsuwong
 * Concrete implementation of the jump strategy.
 */
public class StrategyJump implements MoveStrategy
{
	private int distance;

	/** Constructor for the jumping strategy
	 * @param distance the maximum number of steps that can be taken
	 */
	public StrategyJump(int distance)
	{
		this.distance = distance;
	}
	
	public StrategyJump()
	{
		this(-1);
	}
	
	@Override
	public boolean isValidFor(HantoCoordinateImpl src, HantoCoordinateImpl dest, Board board)
	{
		boolean hasValidPath = false;
		
		int dx = dest.getX() - src.getX();
		int dy = dest.getY() - src.getY();
		
		if (dest.isAdjacentTo(src)) {
			hasValidPath = false;
		} else if (dx == 0) {
			if (dy > 0) {
				hasValidPath = (dest.equals(furthestTile(0, 1, src, board))) ? true : false;
			} else if (dy < 0) {
				hasValidPath = (dest.equals(furthestTile(0, -1, src, board))) ? true : false;
			}
		} else if (dy == 0) {
			if (dx > 0) {
				hasValidPath = (dest.equals(furthestTile(1, 0, src, board))) ? true : false;
			} else if (dx < 0) {
				hasValidPath = (dest.equals(furthestTile(-1, 0, src, board))) ? true : false;
			}
		} else if (dx + dy == 0) {
			if (dx > 0) {
				hasValidPath = (dest.equals(furthestTile(1, -1, src, board))) ? true : false;
			} else if (dx < 0) {
				hasValidPath = (dest.equals(furthestTile(-1, 1, src, board))) ? true : false;
			}
		}
		
		return hasValidPath && board.allowsMoveWithoutBreaking(src, dest);
	} 
	
	private HantoCoordinateImpl furthestTile(int dx, int dy, HantoCoordinateImpl src, Board board)
	{
		HantoCoordinateImpl curTile = src;
		while (board.hasOccupiedTile(curTile)) {
			HantoCoordinateImpl newTile = new HantoCoordinateImpl(curTile.getX() + dx, 
																	curTile.getY() + dy);
			curTile = newTile;
		}
		return curTile;
	}

	@Override
	public HantoMoveRecord aPossibleMove(HantoCoordinateImpl src, Board board)
	{
		HantoMoveRecord theMove = null;
		Set<HantoCoordinateImpl> adjacentTiles = src.getNeighbors();
		
		for (HantoCoordinateImpl neighbor : adjacentTiles) {
			if (board.hasOccupiedTile(neighbor)) {
				int dx = neighbor.getX() - src.getX();
				int dy = neighbor.getY() - src.getY();
				HantoCoordinateImpl anEdgeTile = furthestTile(dx, dy, src, board);
				if (board.allowsMoveWithoutBreaking(src, anEdgeTile)) {
					theMove = new HantoMoveRecord(board.getPieceTypeAt(src), src, anEdgeTile);
					break;
				}
			}
		}
		
		return theMove;
	}

}
