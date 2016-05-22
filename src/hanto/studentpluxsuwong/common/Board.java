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

import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.tournament.HantoMoveRecord;

import static hanto.common.HantoPieceType.*;

/**
 * @author pluxsuwong
 * Board class to store the board state
 */
public class Board
{
	private HantoCoordinateImpl blueButterflyTile = null, redButterflyTile = null; 
	private final Map<HantoCoordinateImpl, HantoPieceImpl> occupiedTiles = 
			new Hashtable<HantoCoordinateImpl, HantoPieceImpl>();
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Board [blueButterflyTile=" + blueButterflyTile + ", redButterflyTile=" 
	+ redButterflyTile + ", occupiedTiles=" + occupiedTiles + "]";
	}
	
	/*
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	public HantoPiece getPieceAt(HantoCoordinate where)
	{
		final HantoCoordinateImpl coordinate = new HantoCoordinateImpl(where);
		final HantoPieceImpl piece = occupiedTiles.get(coordinate);
		return piece;
	}

	/** Check if blue butterfly is surrounded
	 * @return true if blue butterfly is surrounded
	 */
	public boolean blueButterflyTileIsSurrounded()
	{
		if (blueButterflyTile != null) {
			return blueButterflyTile.isSurrounded(occupiedTiles);
		}
		return false;
	}
	
	/** Check if red butterfly is surrounded
	 * @return true if red buttefly is surrounded
	 */
	public boolean redButterflyTileIsSurrounded()
	{
		if (redButterflyTile != null) {
			return redButterflyTile.isSurrounded(occupiedTiles);
		}
		return false;
	}

	/** Place piece on the board
	 * @param dest the destination tile
	 * @param currentPlayer the current player
	 * @param currentPiece the piece to place
	 */
	public void placePiece(HantoCoordinateImpl dest, 
			HantoPlayerColor currentPlayer, HantoPieceImpl currentPiece)
	{
		if (currentPiece.getType() == BUTTERFLY) {
			blueButterflyTile = currentPlayer == BLUE ? dest : blueButterflyTile;
			redButterflyTile = currentPlayer == RED ? dest : redButterflyTile;
		}
		occupiedTiles.put(dest, currentPiece);
	}
	
	/** Move piece on the board
	 * @param src the piece's current location
	 * @param dest the piece's destination
	 * @param currentPlayer the current player
	 * @param pieceImpl the piece to place
	 */
	public void movePiece(HantoCoordinateImpl src, HantoCoordinateImpl dest, 
			HantoPlayerColor currentPlayer, HantoPieceImpl pieceImpl)
	{
		if (pieceImpl.getType() == BUTTERFLY) {
			blueButterflyTile = currentPlayer == BLUE ? dest : blueButterflyTile;
			redButterflyTile = currentPlayer == RED ? dest : redButterflyTile;
		}
		occupiedTiles.put(dest, occupiedTiles.remove(src));
	}

	/** Check if the provided information gives authority to move the piece
	 * @param src the tile the piece should be on
	 * @param pieceRef the provided piece to be moved
	 * @param currentPlayer the provided owner of the piece
	 * @throws HantoException
	 */
	public void checkPieceOwnership(HantoCoordinateImpl src, 
			HantoPieceImpl pieceRef, HantoPlayerColor currentPlayer)  throws HantoException
	{
		final HantoPieceImpl piece = occupiedTiles.get(src);
		if (piece == null) {
			throw new HantoException("Non-existent piece");
		}
		if (piece.getColor() != currentPlayer) {
			throw new HantoException("Piece belongs to other player");
		}
		if (piece.getType() != pieceRef.getType()) {
			throw new HantoException("Piece is of incorrect type");
		}
	}

	/** Check if the player has placed a butterfly yet
	 * @param currentPlayer the player to check for
	 * @return true if the player has already played a butterfly piece
	 */
	public boolean hasPlayerButterfly(HantoPlayerColor currentPlayer)
	{
		if (currentPlayer == BLUE) {
			return (blueButterflyTile == null) ? false : true;
		} else {
			return (redButterflyTile == null) ? false : true;
		}
	}

	public Collection<HantoPieceImpl> getPlayedPieces()
	{
		return occupiedTiles.values();
	}

	/** Check if the tile is occupied
	 * @param dest the tile to check for
	 * @return true if the tile is occupied
	 */
	public boolean hasOccupiedTile(HantoCoordinateImpl dest)
	{
		return occupiedTiles.containsKey(dest);
	}

	/** Check the owner of the piece on the tile
	 * @param currTile the tile to check for
	 * @return the player who owns the piece
	 */
	public HantoPlayerColor getPieceColorAt(HantoCoordinateImpl currTile)
	{
		return occupiedTiles.get(currTile).getColor();
	}
	
	/** BFS to check if the proposed move breaks the piece group
	 * @param src the old tile
	 * @param dest the new tile
	 * @return true if the move results in one piece group
	 */
	public boolean allowsMoveWithoutBreaking(HantoCoordinateImpl src, HantoCoordinateImpl dest)
	{
		Set<HantoCoordinateImpl> unvisited = new HashSet<HantoCoordinateImpl>();
		unvisited.addAll(occupiedTiles.keySet());

		Set<HantoCoordinateImpl> border = new HashSet<HantoCoordinateImpl>();
		unvisited.remove(src);
		Set<HantoCoordinateImpl> nBuf;
		if (dest != null) {
			nBuf = dest.getNeighbors();
			for (HantoCoordinateImpl c : nBuf) {
				if (unvisited.contains(c)) {
					unvisited.remove(c);
					border.add(c);
				}
			}
		} else {
			nBuf = src.getNeighbors();
			for (HantoCoordinateImpl c : nBuf) {
				if (unvisited.contains(c)) {
					unvisited.remove(c);
					border.add(c);
					break;
				}
			}
		}
		while (!border.isEmpty()) {
			Set<HantoCoordinateImpl> nextBorder = new HashSet<HantoCoordinateImpl>();
			for (HantoCoordinateImpl c1 : border) {
				nBuf = c1.getNeighbors();
				for (HantoCoordinateImpl c2 : nBuf) {
					if (unvisited.contains(c2)) {
						unvisited.remove(c2);
						nextBorder.add(c2);
					}
				}
			}
			border = nextBorder;
		}
				
		return (unvisited.size() == 0);
	}

	/** Checks if the player still has a possible move
	 * @param player the player to check for
	 * @param turn the player's turn
	 * @return true if the player can still make a move
	 */
	public boolean playerStillHasMove(HantoPlayerColor player, int turn)
	{
		boolean playerHasPossibleMove = true;
		if (getAnyPossibleMove(player, turn) == null) {
			playerHasPossibleMove = false;
		}
		return playerHasPossibleMove;
	}

	/** Return a move record indicating any possible move
	 * @param myColor the player's color
	 * @param turn the current turn
	 * @return a move record indicating any possible move
	 */
	public HantoMoveRecord getAnyPossibleMove(HantoPlayerColor myColor, int turn)
	{
		HantoMoveRecord theMove = null;
		List<HantoCoordinateImpl> playerCoordSet = getPlayerTiles(myColor);

		if (turn == 0) {
			if (!occupiedTiles.containsKey(new HantoCoordinateImpl(0, 0))) {
				theMove = new HantoMoveRecord(BUTTERFLY,
												null,
												new HantoCoordinateImpl(0, 0));
			} else {
				theMove = new HantoMoveRecord(BUTTERFLY,
												null,
												new HantoCoordinateImpl(0, 1));
			}
		} else {
			HantoPieceType pieceType = getAPlaceableType(playerCoordSet);
			for (HantoCoordinateImpl coord : playerCoordSet) {
				HantoCoordinateImpl placeableTile = null;
				for (HantoCoordinateImpl neighbor : coord.getNeighbors()) {
					if (!neighbor.hasBadNeighbor(myColor, this) && !hasOccupiedTile(neighbor)) {
						placeableTile = neighbor;
						break;
					}
				}
				if (placeableTile != null && pieceType != null) {
					theMove = new HantoMoveRecord(pieceType,
													null,
													placeableTile);
					break;
				}
				HantoPieceImpl curPiece = occupiedTiles.get(coord);
				theMove = curPiece.aPossibleMove(coord, this);
				if (theMove != null) {
					break;
				}
			}
		}
			
		return theMove;
	}

	private HantoPieceType getAPlaceableType(List<HantoCoordinateImpl> playerCoordSet)
	{
		int butterflyCnt = 0;
		int crabCnt = 0;
		int sparrowCnt = 0;
		int horseCnt = 0;
		
		for (HantoCoordinateImpl tile : playerCoordSet) {
			switch (getPieceTypeAt(tile)) {
				case BUTTERFLY:
					butterflyCnt += 1;
					break;
				case CRAB:
					crabCnt += 1;
					break;
				case SPARROW:
					sparrowCnt += 1;
					break;
				case HORSE:
					horseCnt += 1;
					break;
				default:
					break;
			}
		}
		
		return (butterflyCnt < 1) ? BUTTERFLY
				: (crabCnt < 6) ? CRAB
				: (sparrowCnt < 2) ? SPARROW
				: (horseCnt < 4) ? HORSE
				: null;
	}

	/** Return the piece on the tile
	 * @param currTile the provided tile to check
	 * @return the piece type
	 */
	public HantoPieceType getPieceTypeAt(HantoCoordinateImpl currTile) {
		return occupiedTiles.get(currTile).getType();
	}
	
	/** Return the tiles occupied by the player
	 * @param player the player color
	 * @return a List of the player's occupied tiles
	 */
	public List<HantoCoordinateImpl> getPlayerTiles(HantoPlayerColor player)
	{
		List<HantoCoordinateImpl> playerTiles = new ArrayList<HantoCoordinateImpl>();
		Set<HantoCoordinateImpl> aSet = occupiedTiles.keySet();
		for (HantoCoordinateImpl tile : aSet) {
			if (occupiedTiles.get(tile).getColor() == player) {
				playerTiles.add(tile);
			}
		}
		return playerTiles;
	}

}
