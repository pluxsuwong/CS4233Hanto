/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentpluxsuwong.gamma;

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

public class GammaHantoMasterTest
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
		game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO);
		game2 = factory.makeHantoGame(HantoGameID.GAMMA_HANTO, RED);
	}
	
	@Test	// 1
	public void blueStartsWithButterflyAtOrigin() throws HantoException
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
	public void blueStartsWithButterflyNotAtOrigin() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
	}
	
	@Test  // 3
	public void blueStartsWithSparrowAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 4
	public void blueStartsWithSparrowNotAtOrigin() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)  // 5
	public void blueStartsWithCraneAtOrigin() throws HantoException
	{
		game.makeMove(CRANE, null, makeCoordinate(0, 0));
	}
	
	@Test	// 6
	public void redStartsWithButterflyAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 7
	public void redStartsWithButterflyNotAtOrigin() throws HantoException
	{
		game2.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
	}
	
	@Test  // 8
	public void redStartsWithSparrowAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 9
	public void redStartsWithSparrowNotAtOrigin() throws HantoException
	{
		game2.makeMove(SPARROW, null, makeCoordinate(0, 1));
	}
	
	@Test  // 10
	public void redPlacesPieceNextToBlueFirstPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 1));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 11
	public void redPlacesPieceNotNextToBlueFirstPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 2));
	}
	
	@Test(expected=HantoException.class)  // 12
	public void bluePlacesSecondButterflyOnSecondTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 2));
	}
	
	@Test(expected=HantoException.class)  // 13
	public void bluePlacesSparrowNextToOnlyRedPieceOnSecondTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
	}
	
	@Test(expected=HantoException.class)  // 14
	public void bluePlacesSparrowOnOccupiedTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class)  // 15
	public void bluePlacesSparrowNextToBothRedAndBluePiecesOnSecondTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
	}
	
	@Test  // 16
	public void bluePlacesSparrowNextToOnlyBluePieceOnSecondTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, -1));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}

	@Test  // 17
	public void blueMovesFirstButterflyByOneOnSecondTurnAndDoesNotBreakGroup() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
		p = game.getPieceAt(makeCoordinate(1, 0));
		assertNull(p);
		MoveResult mr = game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(1, 0));
		assertEquals(OK, mr);
		p = game.getPieceAt(makeCoordinate(0, 0));
		assertNull(p);
		p = game.getPieceAt(makeCoordinate(1, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 18
	public void blueMovesFirstButterflyByOneOnSecondTurnAndBreaksGroup() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, -1));
	}
	
	@Test(expected=HantoException.class)  // 19
	public void blueMovesRedsPieceDuringHisTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 1), makeCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)  // 20
	public void blueMovesNonExistentPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(2, 1), makeCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)  // 21
	public void blueMovesPieceWithIncorrectType() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)  // 22
	public void blueMovesSparrowByOneWithNoButterfly() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(-1, 1));	
	}
	
	@Test  // 23
	public void blueMovesSparrowByOneWithButterfly() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		MoveResult mr = game.makeMove(SPARROW, makeCoordinate(1, -1), makeCoordinate(0, -1));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, -1));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
		
	}
	
	@Test(expected=HantoException.class)  // 24
	public void blueMovesSparrowByTwoWithButterfly() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, makeCoordinate(1, -1), makeCoordinate(-1, 0));	
	}
	
	@Test  // 25
	public void bluePlacesFirstButterflyOnFourthTurn() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, -3));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, -3));
		assertEquals(BLUE, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 26
	public void bluePlacesFourthSparrowOnFourthTurn() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(SPARROW, null, makeCoordinate(0, -3));
	}
	
	@Test  // 27
	public void redPlacesFirstButterflyOnFourthTurn() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -3));
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 4)); 
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, 4));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 28
	public void redPlacesFourthSparrowOnFourthTurn() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -3));
		game.makeMove(SPARROW, null, makeCoordinate(0, 4));
	}
	
	@Test  // 29
	public void bluePlacesSixthPieceOnBoard() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -3));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 4));
		game.makeMove(SPARROW, null, makeCoordinate(0, -4));
		game.makeMove(SPARROW, null, makeCoordinate(0, 5));
		MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, -5));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, -5));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test  // 30
	public void redPlacesSixthPieceOnBoard() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -3));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 4));
		game.makeMove(SPARROW, null, makeCoordinate(0, -4));
		game.makeMove(SPARROW, null, makeCoordinate(0, 5));
		game.makeMove(SPARROW, null, makeCoordinate(0, -5));
		MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 6));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, 6));
		assertEquals(RED, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 31
	public void bluePlacesSixthSparrowOnBoard() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -3));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 4));
		game.makeMove(SPARROW, null, makeCoordinate(0, -4));
		game.makeMove(SPARROW, null, makeCoordinate(0, 5));
		game.makeMove(SPARROW, null, makeCoordinate(0, -5));
		game.makeMove(SPARROW, null, makeCoordinate(0, 6));
		game.makeMove(SPARROW, null, makeCoordinate(0, -6));
	}
	
	@Test(expected=HantoException.class)  // 32
	public void blueMovesUnmovableSurroundedPieceByOne() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(-1, 2), makeCoordinate(-1, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, -1));
	}
	
	@Test  // 33
	public void blueMovesMovablePartiallySurroundedPieceByOne() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(-1, 2), makeCoordinate(-1, 1));
		MoveResult mr = game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(1, -1));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(1, -1));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test  // 34
	public void blueWins() throws HantoException
	{ 
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		final MoveResult mr = game.makeMove(SPARROW, makeCoordinate(1, -1), makeCoordinate(1, 0));
		assertEquals(BLUE_WINS, mr);
	}
	
	@Test  // 35
	public void redWins() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		final MoveResult mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 0));
		assertEquals(RED_WINS, mr);
	}
	
	@Test  // 36
	public void blueAndRedSurroundedSimultaneously() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 2));
		game.makeMove(SPARROW, makeCoordinate(2, -1), makeCoordinate(1, 0));
		MoveResult mr = game.makeMove(SPARROW, makeCoordinate(-2, 2), makeCoordinate(-1, 1));
		assertEquals(DRAW, mr);
	}
	
	@Test  // 37
	public void drawAfterTwentyTurns() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		MoveResult mr = game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		assertEquals(DRAW, mr);
	}
	
	@Test(expected=HantoException.class)  // 38
	public void moveMadeAfterGameOver() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(-1, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(0, -1));
	}
	
	@Test  // 39
	public void printBoardNotNull() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertNotNull(game.getPrintableBoard());
	}
	
	@Test  // 40
	public void hantoPieceImplEqualsTest() throws HantoException
	{
		HantoPieceImpl piece = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		assertFalse(piece.equals(null));
		assertFalse(piece.equals(game));
	}
	
	@Test  // 41
	public void hantoPieceImplHashCode() throws HantoException
	{
		HantoPieceImpl piece1 = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		HantoPieceImpl piece2 = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		HantoPieceImpl piece3 = new HantoPieceImpl(null, null, new StrategyWalk(1));
		assertEquals(piece1.hashCode(), piece2.hashCode());
		assertNotEquals(piece1.hashCode(), piece3.hashCode());
	}
	
	@Test  // 42
	public void hantoCoordinateImplEquals() throws HantoException
	{
		HantoCoordinateImpl coordinate = new HantoCoordinateImpl(0, 0);
		assertFalse(coordinate.equals(game));
	}
	
	@Test  // 43
	public void hantoBasicCoordinateHashCodeEquals() throws HantoException
	{
		HantoCoordinate coordinate1 = new HantoCoordinateImpl(0, 0).new HantoBasicCoordinate(0, 0);
		HantoCoordinate coordinate2 = new HantoCoordinateImpl(0, 0).new HantoBasicCoordinate(0, 0);
		assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
		assertFalse(coordinate1.equals(game));
		assertFalse(coordinate1.equals(null));
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
