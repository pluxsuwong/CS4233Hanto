/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentpluxsuwong.delta;

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
 * @author pluxsuwong
 *
 */
public class DeltaHantoMasterTest {

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
		game = factory.makeHantoGame(HantoGameID.DELTA_HANTO);
		game2 = factory.makeHantoGame(HantoGameID.DELTA_HANTO, RED);
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
	
	@Test  // 5
	public void blueStartsWithCrabAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(CRAB, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(CRAB, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 6
	public void blueStartsWithCrabNotAtOrigin() throws HantoException
	{
		game.makeMove(CRAB, null, makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)  // 7
	public void blueStartsWithCraneAtOrigin() throws HantoException
	{
		game.makeMove(CRANE, null, makeCoordinate(0, 0));
	}
	
	@Test	// 8
	public void redStartsWithButterflyAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 9
	public void redStartsWithButterflyNotAtOrigin() throws HantoException
	{
		game2.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
	}
	
	@Test  // 10
	public void redStartsWithSparrowAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 11
	public void redStartsWithSparrowNotAtOrigin() throws HantoException
	{
		game2.makeMove(SPARROW, null, makeCoordinate(0, 1));
	}
	
	@Test  // 12
	public void redStartsWithCrabAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(CRAB, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(CRAB, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 13
	public void redStartsWithCrabNotAtOrigin() throws HantoException
	{
		game2.makeMove(CRAB, null, makeCoordinate(0, 1));
	}
	
	@Test  // 14
	public void redPlacesPieceNextToBlueFirstPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 1));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 15
	public void redPlacesPieceNotNextToBlueFirstPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 2));
	}
	
	@Test(expected=HantoException.class)  // 16
	public void bluePlacesSecondButterflyOnSecondTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
	}
	
	@Test(expected=HantoException.class)  // 17
	public void bluePlacesSparrowNextToOnlyRedPieceOnSecondTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
	}
	
	@Test(expected=HantoException.class)  // 18
	public void bluePlacesSparrowOnOccupiedTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class)  // 19
	public void bluePlacesSparrowNextToBothRedAndBluePiecesOnSecondTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
	}
	
	@Test  // 20
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

	@Test  // 21
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
	
	@Test(expected=HantoException.class)  // 22
	public void blueMovesFirstButterflyByOneOnSecondTurnAndBreaksGroup() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, -1));
	}
	
	@Test(expected=HantoException.class)  // 23
	public void blueMovesRedsPieceDuringHisTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 1), makeCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)  // 24
	public void blueMovesNonExistentPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(2, 1), makeCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)  // 25
	public void blueMovesButterflyOntoOccupiedTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)  // 26
	public void blueMovesPieceWithIncorrectType() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)  // 27
	public void blueMovesSparrowByOneWithNoButterfly() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(-1, 1));	
	}
	
	@Test  // 28
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
	
	@Test  // 29
	public void blueFliesSparrowWithButterfly() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		MoveResult mr = game.makeMove(SPARROW, makeCoordinate(1, -1), makeCoordinate(0, 2));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, 2));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 30
	public void blueFliesSparrowAndBreaksGroup() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, makeCoordinate(1, -1), makeCoordinate(0, -2));
	}
	
	@Test  // 31
	public void blueMovesCrabOneTileUnobstructedToGoodTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(CRAB, null, makeCoordinate(2, -2));
		game.makeMove(SPARROW, null, makeCoordinate(2, 1));
		MoveResult mr = game.makeMove(CRAB, makeCoordinate(2, -2), makeCoordinate(2, -1));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(2, -1));
		assertEquals(BLUE, p.getColor());
		assertEquals(CRAB, p.getType());
	}
	
	@Test  // 32
	public void blueMovesCrabThreeTilesUnobstructedToGoodTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(CRAB, null, makeCoordinate(2, -2));
		game.makeMove(SPARROW, null, makeCoordinate(2, 1));
		MoveResult mr = game.makeMove(CRAB, makeCoordinate(2, -2), makeCoordinate(3, 0));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(3, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(CRAB, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 33
	public void blueMovesCrabOneTileUnobstructedToBreakGroup() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(CRAB, null, makeCoordinate(2, -2));
		game.makeMove(SPARROW, null, makeCoordinate(2, 1));
		game.makeMove(CRAB, makeCoordinate(2, -2), makeCoordinate(2, -3));
	}
	
	@Test(expected=HantoException.class)  // 34
	public void blueMovesCrabThreeTilesUnobstructedToBreakGroup() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(CRAB, null, makeCoordinate(2, -2));
		game.makeMove(SPARROW, null, makeCoordinate(2, 1));
		game.makeMove(CRAB, makeCoordinate(2, -2), makeCoordinate(3, -1));
	}
	
	@Test(expected=HantoException.class)  // 35
	public void blueMovesCrabThreeTilesObstructedToGoodTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(CRAB, null, makeCoordinate(2, -2));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(CRAB, makeCoordinate(2, -2), makeCoordinate(1, 0));
	}
	
	@Test(expected=HantoException.class)  // 36
	public void blueMovesCrabFourTilesUnobstructedToGoodTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(CRAB, null, makeCoordinate(2, -2));
		game.makeMove(SPARROW, null, makeCoordinate(2, 0));
		game.makeMove(CRAB, makeCoordinate(2, -2), makeCoordinate(-1, 1));
	}
	
	@Test // 37
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
	
	@Test(expected=HantoException.class)  // 38
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
	
	@Test  // 39
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
	
	@Test(expected=HantoException.class)  // 40
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
	
	@Test  // 41
	public void playersPlaceTheirLastPiecesOnBoard() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(SPARROW, null, makeCoordinate(0, -3));
		game.makeMove(SPARROW, null, makeCoordinate(0, 4));
		game.makeMove(SPARROW, null, makeCoordinate(0, -4));
		game.makeMove(SPARROW, null, makeCoordinate(0, 5));
		game.makeMove(CRAB, null, makeCoordinate(0, -5));
		game.makeMove(CRAB, null, makeCoordinate(0, 6));
		game.makeMove(CRAB, null, makeCoordinate(0, -6));
		game.makeMove(CRAB, null, makeCoordinate(0, 7));
		game.makeMove(CRAB, null, makeCoordinate(0, -7));
		game.makeMove(CRAB, null, makeCoordinate(0, 8));
		game.makeMove(CRAB, null, makeCoordinate(0, -8));
		game.makeMove(CRAB, null, makeCoordinate(0, 9));
	}
	
	@Test(expected=HantoException.class)  // 42
	public void bluePlacesExtraCrabPieceOnBoard() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(SPARROW, null, makeCoordinate(0, -3));
		game.makeMove(SPARROW, null, makeCoordinate(0, 4));
		game.makeMove(SPARROW, null, makeCoordinate(0, -4));
		game.makeMove(SPARROW, null, makeCoordinate(0, 5));
		game.makeMove(CRAB, null, makeCoordinate(0, -5));
		game.makeMove(CRAB, null, makeCoordinate(0, 6));
		game.makeMove(CRAB, null, makeCoordinate(0, -6));
		game.makeMove(CRAB, null, makeCoordinate(0, 7));
		game.makeMove(CRAB, null, makeCoordinate(0, -7));
		game.makeMove(CRAB, null, makeCoordinate(0, 8));
		game.makeMove(CRAB, null, makeCoordinate(0, -8));
		game.makeMove(CRAB, null, makeCoordinate(0, 9));
		game.makeMove(CRAB, null, makeCoordinate(0, -9));
	}
	
	@Test(expected=HantoException.class)  // 42
	public void bluePlacesExtraSparrowPieceOnBoard() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(SPARROW, null, makeCoordinate(0, -3));
		game.makeMove(SPARROW, null, makeCoordinate(0, 4));
		game.makeMove(SPARROW, null, makeCoordinate(0, -4));
		game.makeMove(SPARROW, null, makeCoordinate(0, 5));
		game.makeMove(CRAB, null, makeCoordinate(0, -5));
		game.makeMove(CRAB, null, makeCoordinate(0, 6));
		game.makeMove(CRAB, null, makeCoordinate(0, -6));
		game.makeMove(CRAB, null, makeCoordinate(0, 7));
		game.makeMove(CRAB, null, makeCoordinate(0, -7));
		game.makeMove(CRAB, null, makeCoordinate(0, 8));
		game.makeMove(CRAB, null, makeCoordinate(0, -8));
		game.makeMove(CRAB, null, makeCoordinate(0, 9));
		game.makeMove(SPARROW, null, makeCoordinate(0, -9));
	}
	
	@Test(expected=HantoException.class)  // 43
	public void blueMovesUnmovableSurroundedCrabByTwo() throws HantoException
	{
		game.makeMove(CRAB, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(-1, 2), makeCoordinate(-1, 1));
		game.makeMove(CRAB, makeCoordinate(0, 0), makeCoordinate(0, -2));
	}
	
	@Test  // 43
	public void blueMovesMovableSurroundedSparrow() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(-1, 2), makeCoordinate(-1, 1));
		MoveResult mr = game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(0, -1));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, -1));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test  // 44
	public void blueMovesMovablePartiallySurroundedPieceByOne() throws HantoException
	{
		game.makeMove(CRAB, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(-1, 2), makeCoordinate(-1, 1));
		MoveResult mr = game.makeMove(CRAB, makeCoordinate(0, 0), makeCoordinate(1, -1));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(1, -1));
		assertEquals(BLUE, p.getColor());
		assertEquals(CRAB, p.getType());
	}
	
	@Test  // 45
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
	
	@Test  // 46
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
	
	@Test  // 47
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
		
	@Test(expected=HantoException.class)  // 48
	public void moveMadeAfterGameOver() throws HantoException
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
		game.makeMove(SPARROW,  makeCoordinate(1, 0), makeCoordinate(2, -1));
	}
	
	@Test  // 49
	public void printBoardNotNull() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertNotNull(game.getPrintableBoard());
	}
	
	@Test  // 50
	public void hantoPieceImplEqualsTest() throws HantoException
	{
		HantoPieceImpl piece = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		assertFalse(piece.equals(null));
		assertFalse(piece.equals(game));
	}
	
	@Test  // 51
	public void hantoPieceImplHashCode() throws HantoException
	{
		HantoPieceImpl piece1 = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		HantoPieceImpl piece2 = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		HantoPieceImpl piece3 = new HantoPieceImpl(null, null, new StrategyWalk(1));
		assertEquals(piece1.hashCode(), piece2.hashCode());
		assertNotEquals(piece1.hashCode(), piece3.hashCode());
	}
	
	@Test  // 52
	public void hantoCoordinateImplEquals() throws HantoException
	{
		HantoCoordinateImpl coordinate = new HantoCoordinateImpl(0, 0);
		HantoCoordinateImpl coordinate1 = new HantoCoordinateImpl(0, 1);
		HantoCoordinateImpl coordinate2 = new HantoCoordinateImpl(1, 0);
		assertFalse(coordinate.equals(game));
		assertFalse(coordinate.equals(coordinate1));
		assertFalse(coordinate.equals(coordinate2));
	}
	
	@Test  // 53
	public void hantoBasicCoordinateHashCodeEquals() throws HantoException
	{
		HantoCoordinate coordinate1 = new HantoCoordinateImpl(0, 0).new HantoBasicCoordinate(0, 0);
		HantoCoordinate coordinate2 = new HantoCoordinateImpl(0, 0).new HantoBasicCoordinate(0, 0);
		assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
		assertFalse(coordinate1.equals(game));
		assertFalse(coordinate1.equals(null));
	}
	
	@Test(expected=HantoException.class)  // 54
	public void placingPieceOnOccupiedTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
	}
	
	@Test  // 55
	public void blueResigns() throws HantoException
	{
		MoveResult mr = game.makeMove(null, null, null);
		assertEquals(mr, MoveResult.RED_WINS);
	}
	
	@Test(expected=HantoException.class)  // 56
	public void moveAfterResignation() throws HantoException
	{
		MoveResult mr = game.makeMove(null, null, null);
		assertEquals(mr, MoveResult.RED_WINS);
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class)  // 57
	public void placingPieceOnFarAwayTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 4));
	}
}
