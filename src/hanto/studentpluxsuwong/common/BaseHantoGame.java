/**
 * 
 */
package hanto.studentpluxsuwong.common;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;

/**
 * @author pluxsuwong
 *
 */
public abstract class BaseHantoGame implements HantoGame
{
	protected HantoPieceFactory factory;
	protected int turnLimit = -1;
	protected HantoPlayerColor firstPlayer = BLUE;
	protected HantoPlayerColor currentPlayer = BLUE;
	protected Board board = new Board();
	protected int turn = 0;
	protected boolean inGame = true;
	protected boolean validateResignation = true;
	
	/* (non-Javadoc)
	 * @see hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException
	{
		if (!inGame) {
			throw new HantoException("Game is already over");
		} else if (pieceType == null && from == null && to == null) {
			if (validateResignation && board.playerStillHasMove(currentPlayer, turn)) {
				throw new HantoPrematureResignationException();
			} else {
				inGame = false;
				return (currentPlayer == BLUE) ? MoveResult.RED_WINS : MoveResult.BLUE_WINS;
			}
		}
		
		HantoCoordinateImpl src = null;
		if (from != null) {
			src = new HantoCoordinateImpl(from);
		}
		if (to == null) {
			throw new HantoException("Invalid destination");
		}
		final HantoCoordinateImpl dest = new HantoCoordinateImpl(to);
		final HantoPieceImpl currentPiece = factory.makeHantoPiece(currentPlayer, pieceType);
		
		if (currentPiece == null) {
			throw new HantoException("Invalid piece");
		}
		
		if (newPieceUsed(src)) {
			placePieceRoutine(currentPiece, dest);
		} else {
			movePieceRoutine(currentPiece, src, dest);
		}
		
		MoveResult result = checkForWinner();
		
		return result;
	}

	/*
	 * @see hanto.common.HantoGame#getPrintableBoard()
	 */
	@Override
	public String getPrintableBoard()
	{
		final String hashBoard = board.toString();
		return hashBoard;
	}
	
	/*
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where)
	{
		final HantoCoordinateImpl coordinate = new HantoCoordinateImpl(where);
		final HantoPiece piece = board.getPieceAt(coordinate);
		return piece;
	}

	/**
	 * Allows the starting player to be set
	 * @param player to go first
	 */
	public void setFirstPlayer(HantoPlayerColor player)
	{
		firstPlayer = player;
		currentPlayer = player;
	}
	
	/**
	 * If there is no source, a new piece has been selected
	 * @param src
	 * @return true if the provided piece is new
	 */
	private boolean newPieceUsed(HantoCoordinateImpl src)
	{
		return src == null;
	}
	
	/**
	 * Check if there is a winner, or a draw
	 * @return MoveResult indicating the status of the last move
	 */
	private MoveResult checkForWinner()
	{
		int tmpVar = 1;
		tmpVar = checkIfButterfliesSurrounded();
		inGame = (tmpVar == 1);
		MoveResult result = tmpVar == 1 ? MoveResult.OK 
				: tmpVar == 2 ? MoveResult.RED_WINS 
				: tmpVar == 3 ? MoveResult.BLUE_WINS
				: MoveResult.DRAW;
		
		currentPlayer = currentPlayer == BLUE ? RED : BLUE;
		if (currentPlayer == firstPlayer) {
			turn += 1;
			if (turnLimit > 0 && turn >= turnLimit && result == MoveResult.OK) {
				result = MoveResult.DRAW;
				inGame = false;
			}
		}
		
		return result;
	}
	
	private int checkIfButterfliesSurrounded()
	{
		int tmpVar = 1;
		if (board.blueButterflyTileIsSurrounded()) {
			tmpVar *= 2;
		}
		if (board.redButterflyTileIsSurrounded()) {
			tmpVar *= 3;
		}
		return tmpVar;
	}
	
	/**
	 * Routine of functions run when a piece is selected to be placed on the board
	 * @param currentPiece
	 * @param dest
	 * @throws HantoException
	 */
	private void placePieceRoutine(final HantoPieceImpl currentPiece,
			final HantoCoordinateImpl dest) throws HantoException
	{
		if (firstTurn()) {
			firstTurnConditions(turn, dest);
		} else {
			if (currentPiece.getType() != BUTTERFLY && turn >= 3) {
				findButterfly();
			}
			checkPieceQuantityLimits(currentPiece);
			checkIfCanPlacePieceAt(dest);
		}
		board.placePiece(dest, currentPlayer, currentPiece);
	}
	
	/**
	 * Routine of functions run when a piece is selected to be moved 
	 * @param pieceType piece type to be moved
	 * @param src starting position
	 * @param dest ending position
	 * @throws HantoException
	 */
	private void movePieceRoutine(HantoPieceImpl pieceImpl, 
			HantoCoordinateImpl src, final HantoCoordinateImpl dest) throws HantoException
	{
		findButterfly();
		board.checkPieceOwnership(src, pieceImpl, currentPlayer);
		if (!pieceImpl.hasValidMove(src, dest, board)) {
			throw new HantoException("Not a valid move");
		}
		board.movePiece(src, dest, currentPlayer, pieceImpl);
	}
	
	/**
	 * @param currentPieceType
	 * @throws HantoException
	 */
	private void checkPieceQuantityLimits(HantoPieceImpl currentPieceType) throws HantoException
	{
		final Collection<HantoPieceImpl> placedPieces = board.getPlayedPieces();
		int placedPieceCntr = 0;
		for (HantoPieceImpl piece : placedPieces) {
			if (currentPieceType.equals(piece)) {
				placedPieceCntr += 1;
			}
		}
		if (atLimit(currentPieceType.getType(), placedPieceCntr)) {
			throw new HantoException("Piece of selected type cannot be placed anymore");
		}
	}
	
	/**
	 * Check to see if the current player's butterfly has been placed yet
	 * @throws HantoException
	 */
	private void findButterfly() throws HantoException 
	{
		if (!board.hasPlayerButterfly(currentPlayer)) {
			throw new HantoException("Butterfly must be placed first");
		}
	}

	/**
	 * @param turn
	 * @param dest
	 * @throws HantoException
	 */
	private void firstTurnConditions(int turn, HantoCoordinateImpl dest) throws HantoException
	{
		if (currentPlayer == firstPlayer && dest.isNotOrigin()) {
			throw new HantoException("Move not at origin" + dest + currentPlayer);
		}
		if (currentPlayer != firstPlayer && !dest.isAdjacentTo(new HantoCoordinateImpl(0, 0))) {
			throw new HantoException("Invalid movement");
		}
	}

	/**
	 * @return true if it is the first turn
	 */
	private boolean firstTurn()
	{
		return turn == 0;
	}
	
	/** Check if the quota for the piece type has been reached
	 * @param pieceType the piece type to check for
	 * @param count the current number of the piece type on the board
	 * @return true if piece shouldn't be placed
	 */
	protected abstract boolean atLimit(HantoPieceType pieceType, int count);
	
	/** Check if the current player can place a piece on the destination tile 
	 * @param dest the destination tile
	 * @throws HantoException
	 */
	protected abstract void checkIfCanPlacePieceAt(HantoCoordinateImpl dest) throws HantoException;

}
