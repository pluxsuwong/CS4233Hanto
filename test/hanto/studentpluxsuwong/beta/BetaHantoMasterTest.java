/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentpluxsuwong.beta;

import static hanto.common.HantoPieceType.*;
import static hanto.common.MoveResult.*;
import static hanto.common.HantoPlayerColor.*;
import static org.junit.Assert.*;
import hanto.common.*;
import hanto.studentpluxsuwong.HantoGameFactory;
import hanto.studentpluxsuwong.common.HantoCoordinateImpl;
import hanto.studentpluxsuwong.common.HantoPieceImpl;
import hanto.studentpluxsuwong.common.StrategyWalk;

import org.junit.*;

/**
 * Test cases for Beta Hanto.
 * @version Sep 14, 2014
 */
public class BetaHantoMasterTest
{
	/**
	 * Internal class for these test cases.
	 * @version Sep 13, 2014
	 */
	class TestHantoCoordinate implements HantoCoordinate
	{
		private final int x, y;
		
		public TestHantoCoordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		/*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		@Override
		public int getX()
		{
			return x;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		@Override
		public int getY()
		{
			return y;
		}

	}
	
	private static HantoGameFactory factory;
	private HantoGame game;
	private HantoGame game2;
	
	@BeforeClass
	public static void initializeClass()
	{
		factory = HantoGameFactory.getInstance();
	}
	
	@Before
	public void setup()
	{
		// By default, blue moves first.
		game = factory.makeHantoGame(HantoGameID.BETA_HANTO);
		game2 = factory.makeHantoGame(HantoGameID.BETA_HANTO, RED);
	}
	
	@Test	// 1
	public void bluePlacesInitialButterflyAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
	
	@Test(expected=HantoException.class)  // 2
	public void bluePlacesInitialButterflyOutsideOrigin() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
	}
	
	@Test  // 3
	public void bluePlacesInitialSwallowAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 4
	public void bluePlacesInitialInvalidPiece() throws HantoException
	{
		game.makeMove(CRANE, null, makeCoordinate(0, 0));
	}
	
	@Test  // 5
	public void redMakesValidMoveWithButterfly() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 1));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test  // 6
	public void redMakesValidMoveWithSparrow() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 1));
		assertEquals(RED, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 7
	public void redPlacesOnOccupiedCoordinate() throws HantoException
	{
		game.makeMove(BUTTERFLY,  null,  makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class)  // 8
	public void redPlacesOnInvalidCoordinate() throws HantoException
	{
		game.makeMove(BUTTERFLY,  null,  makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 2));
	}
	
	@Test	// 9
	public void redPlacesInitialButterflyAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 10
	public void redPlacesInitialButterflyOutsideOrigin() throws HantoException
	{
		game2.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
	}
	
	@Test  // 11
	public void redPlacesInitialSwallowAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 12
	public void redPlacesInitialInvalidPiece() throws HantoException
	{
		game2.makeMove(CRANE, null, makeCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class)  // 13
	public void multipleButterflies() throws HantoException {
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
	}
	
	@Test  // 14
	public void fourPiecesPlacedInStraightLine() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));
	}
	
	@Test  // 15
	public void bluesFourthTurnPlacesButterflyWithoutPlacedButterfly() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-3, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(3, 0));
	}
	
	@Test(expected=HantoException.class)  // 16
	public void bluesFourthTurnPlacesSparrowWithoutPlacedButterfly() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
	}
	
	@Test  // 17
	public void bluesFourthTurnPlacesSparrowWithPlacedButterfly() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
	}
	
	@Test  // 19
	public void redWinsBlueLoses() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		assertEquals(RED_WINS, mr);
	}
	
	@Test  // 18
	public void blueWinsRedLoses() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		assertEquals(BLUE_WINS, mr);
	}
	
	@Test // 20
	public void drawFromNoPiecesLeft() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-3, 0));
		game.makeMove(SPARROW, null, makeCoordinate(4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-4, 0));
		game.makeMove(SPARROW, null, makeCoordinate(5, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-5, 0));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(6, 0));
		assertEquals(DRAW, mr);
	}
	
	@Test  // 21
	public void drawFromSimultaneousLoss() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		assertEquals(DRAW, mr);
	}
	
	@Test(expected=HantoException.class)  // 22
	public void moveMadePostGame() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
	}
	
	@Test  // 23
	public void printBoardNotNull() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertNotNull(game.getPrintableBoard());
	}
	
	@Test  // 24
	public void hantoPieceImplEqualsTest() throws HantoException
	{
		HantoPieceImpl piece = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		assertFalse(piece.equals(null));
		assertFalse(piece.equals(game));
	}
	
	@Test  // 25
	public void hantoPieceImplHashCode() throws HantoException
	{
		HantoPieceImpl piece1 = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		HantoPieceImpl piece2 = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		HantoPieceImpl piece3 = new HantoPieceImpl(null, null, new StrategyWalk(1));
		assertEquals(piece1.hashCode(), piece2.hashCode());
		assertNotEquals(piece1.hashCode(), piece3.hashCode());
	}
	
	@Test  // 26
	public void hantoCoordinateImplEquals() throws HantoException
	{
		HantoCoordinateImpl coordinate = new HantoCoordinateImpl(0, 0);
		assertFalse(coordinate.equals(game));
	}
	
	@Test(expected=HantoException.class)
	public void placingPieceOnOccupiedTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class)
	public void placingPieceOnFarAwayTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 4));
	}
}
