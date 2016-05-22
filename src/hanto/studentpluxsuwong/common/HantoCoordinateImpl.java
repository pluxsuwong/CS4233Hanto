/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2015 Gary F. Pollice
 *******************************************************************************/

package hanto.studentpluxsuwong.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPlayerColor;

/**
 * The implementation for my version of Hanto.
 * @version Mar 2, 2016
 */
public class HantoCoordinateImpl implements HantoCoordinate
{
	List<HantoBasicCoordinate> neighbors = new ArrayList<HantoBasicCoordinate>();
	
	/**
	 * @author pluxsuwong
	 * Nested Class to prevent StackOverflow from occurring due to neighbors attribute
	 */
	public class HantoBasicCoordinate implements HantoCoordinate
	{
		private final int x, y;
		
		/**
		 * Constructor for BasicCoordinate class
		 * @param x
		 * @param y
		 */
		public HantoBasicCoordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		/**
		 * Overloaded constructor to allow for easy translation to/from the main implementation
		 * @param coordinate
		 */
		public HantoBasicCoordinate(HantoCoordinate coordinate)
		{
			this(coordinate.getX(), coordinate.getY());
		}

		@Override
		public int getX()
		{
			return x;
		}

		@Override
		public int getY()
		{
			return y;
		}
		
		/*
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		/*
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj)
		{
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof HantoBasicCoordinate)) {
				return false;
			}
			final HantoBasicCoordinate other = (HantoBasicCoordinate) obj;
			if (x != other.x) {
				return false;
			}
			if (y != other.y) {
				return false;
			}
			return true;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "[x=" + x + ", y=" + y + "]";
	}

	private final int x, y;
	
	/**
	 * The only constructor.
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	public HantoCoordinateImpl(int x, int y)
	{
		this.x = x;
		this.y = y;
		neighbors.add(new HantoBasicCoordinate(x, y + 1));
		neighbors.add(new HantoBasicCoordinate(x + 1, y));
		neighbors.add(new HantoBasicCoordinate(x + 1, y - 1));
		neighbors.add(new HantoBasicCoordinate(x, y - 1));
		neighbors.add(new HantoBasicCoordinate(x - 1, y));
		neighbors.add(new HantoBasicCoordinate(x - 1, y + 1));
	}
	
	/**
	 * Copy constructor that creates an instance of HantoCoordinateImpl from an
	 * object that implements HantoCoordinate.
	 * @param coordinate an object that implements the HantoCoordinate interface.
	 */
	public HantoCoordinateImpl(HantoCoordinate coordinate)
	{
			this(coordinate.getX(), coordinate.getY());
	}
	
	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}
	
	/**
	 * Check is coordinate is not at origin
	 * @return true if coordinate is not (0, 0)
	 */
	public boolean isNotOrigin()
	{
		return x != 0 || y != 0;
	}
	
	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof HantoCoordinateImpl)) {
			return false;
		}
		final HantoCoordinateImpl other = (HantoCoordinateImpl) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	/**
	 * Indicate if the provided coordinate is adjacent to the object tile
	 * @param coordinate
	 * @return true if the instance is the tile's neighbor
	 */
	public boolean isAdjacentTo(HantoCoordinate coordinate)
	{
		return neighbors.contains(new HantoBasicCoordinate(coordinate));
	}
	
	/**
	 * Indicate if the object tile has any neighboring tiles containing the other player's pieces 
	 * @param player the current player
	 * @param board data structure (HashMap) used to store the tiles and pieces
	 * @return true if the waiting player's pieces are on any of the neighboring tiles
	 */
	public boolean hasBadNeighbor(HantoPlayerColor player, Board board)
	{
		boolean foundBadNeighbor = false;
		for (HantoBasicCoordinate tile : neighbors) {
			HantoCoordinateImpl currTile = new HantoCoordinateImpl(tile);
			if (board.hasOccupiedTile(currTile)) {
				if (player != board.getPieceColorAt(currTile)) {
					foundBadNeighbor = true;
					break;
				}
			}
		}
		
		return foundBadNeighbor;
	}
	
	/**
	 * Indicate if the tile is surrounded by 6 occupied neighboring tiles 
	 * @param board
	 * @return true if there are no vacant tiles around the tile
	 */
	public boolean isSurrounded(Map<HantoCoordinateImpl, HantoPieceImpl> board)
	{
		boolean isSurrounded = true;
		
		for (HantoBasicCoordinate tile : neighbors) {
			HantoCoordinateImpl currTile = new HantoCoordinateImpl(tile);
			if (!board.containsKey(currTile)) {
				isSurrounded = false;
			}
		}
		
		return isSurrounded;
	}
	
	/**
	 * Primarily used to detect when moving to a tile will cause a breakage in the group
	 * @param src the piece's current tile
	 * @param board
	 * @return the number of occupied tiles next to a destination tile, exclusive of the
	 * piece's current tile
	 */
	public int adjacentNeighborNum(HantoCoordinateImpl src, Board board)
	{
		int neighborNum = 0;
		for (HantoBasicCoordinate tile : neighbors) {
			HantoCoordinateImpl currTile = new HantoCoordinateImpl(tile);
			if (board.hasOccupiedTile(currTile)) {
				if (!currTile.equals(src)) {
					neighborNum += 1;
				}
			}
		}
		
		return neighborNum;
	}
	
	/**
	 * Indicate if a piece can move, when some of its neighboring tiles are occupied
	 * @param dest destination tile
	 * @param board
	 * @return true if the piece can slide through any gaps
	 */
	public boolean canSlideTo(HantoCoordinateImpl dest, Board board)
	{
		boolean slidable = false;
		final int listIndex = neighbors.indexOf(new HantoBasicCoordinate(dest));
		final HantoCoordinateImpl leftTile = new HantoCoordinateImpl(neighbors.get((listIndex + 5) % 6));
		final HantoCoordinateImpl rightTile = new HantoCoordinateImpl(neighbors.get((listIndex + 7) % 6));
		if (board.getPieceAt(leftTile) == null || board.getPieceAt(rightTile) == null) {
			slidable = true;
		}
		return slidable;
	}

	/** Return a set containing neighbor tiles that can be sled to
	 * @param board the board state
	 * @return the neighbors that can be sled to
	 */
	public List<HantoCoordinateImpl> slidableNeighbors(Board board)
	{
		List<HantoCoordinateImpl> reachableNeighbors = new ArrayList<HantoCoordinateImpl>();
		for (HantoBasicCoordinate n : neighbors) {
			HantoCoordinateImpl curN = new HantoCoordinateImpl(n);
			if (canSlideTo(curN, board) && !board.hasOccupiedTile(curN)) {
				reachableNeighbors.add(curN);
			}
		}
		return reachableNeighbors;
	}

	/** Return a set containing a tile's neighbors
	 * @return a set of HantoCoordinateImpl objects
	 */
	public Set<HantoCoordinateImpl> getNeighbors()
	{
		Set<HantoCoordinateImpl> neighbors = new HashSet<HantoCoordinateImpl>();
		for (HantoBasicCoordinate n : this.neighbors) {
			neighbors.add(new HantoCoordinateImpl(n));
		}
		return neighbors;
	}
	
}
