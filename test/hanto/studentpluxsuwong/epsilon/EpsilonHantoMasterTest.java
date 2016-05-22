package hanto.studentpluxsuwong.epsilon;

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

public class EpsilonHantoMasterTest 
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
		game = factory.makeHantoGame(HantoGameID.EPSILON_HANTO);
		game2 = factory.makeHantoGame(HantoGameID.EPSILON_HANTO, RED);
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
	
	@Test  // 7
	public void blueStartsWithHorseAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(HORSE, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(HORSE, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 8
	public void blueStartsWithHorseNotAtOrigin() throws HantoException
	{
		game.makeMove(HORSE, null, makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)  // 9
	public void blueStartsWithCraneAtOrigin() throws HantoException
	{
		game.makeMove(CRANE, null, makeCoordinate(0, 0));
	}
	
	@Test	// 10
	public void redStartsWithButterflyAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 11
	public void redStartsWithButterflyNotAtOrigin() throws HantoException
	{
		game2.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
	}
	
	@Test  // 12
	public void redStartsWithSparrowAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 13
	public void redStartsWithSparrowNotAtOrigin() throws HantoException
	{
		game2.makeMove(SPARROW, null, makeCoordinate(0, 1));
	}
	
	@Test  // 14
	public void redStartsWithCrabAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(CRAB, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(CRAB, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 15
	public void redStartsWithCrabNotAtOrigin() throws HantoException
	{
		game2.makeMove(CRAB, null, makeCoordinate(0, 1));
	}
	
	@Test  // 16
	public void redStartsWithHorseAtOrigin() throws HantoException
	{
		final MoveResult mr = game2.makeMove(HORSE, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game2.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(HORSE, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 17
	public void redStartsWithHorseNotAtOrigin() throws HantoException
	{
		game2.makeMove(HORSE, null, makeCoordinate(0, 1));
	}
	
	@Test  // 18
	public void redPlacesPieceNextToBlueFirstPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 1));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 19
	public void redPlacesPieceNotNextToBlueFirstPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 2));
	}
	
	@Test(expected=HantoException.class)  // 20
	public void bluePlacesSecondButterflyOnSecondTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
	}
	
	@Test(expected=HantoException.class)  // 21
	public void bluePlacesSparrowNextToOnlyRedPieceOnSecondTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
	}
	
	@Test(expected=HantoException.class)  // 22
	public void bluePlacesSparrowOnOccupiedTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class)  // 23
	public void bluePlacesSparrowNextToBothRedAndBluePiecesOnSecondTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
	}
	
	@Test  // 24
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

	@Test  // 25
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
	
	@Test(expected=HantoException.class)  // 26
	public void blueMovesFirstButterflyByOneOnSecondTurnAndBreaksGroup() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, -1));
	}
	
	@Test(expected=HantoException.class)  // 27
	public void blueMovesRedsPieceDuringHisTurn() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 1), makeCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)  // 28
	public void blueMovesNonExistentPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(2, 1), makeCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)  // 29
	public void blueMovesButterflyOntoOccupiedTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 1));
	}
	
	@Test(expected=HantoException.class)  // 30
	public void blueMovesPieceWithIncorrectType() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(-1, 1));
	}
	
	@Test(expected=HantoException.class)  // 31
	public void blueMovesSparrowByOneWithNoButterfly() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(-1, 1));	
	}
	
	@Test  // 32
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
	
	@Test  // 33
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
	
	@Test(expected=HantoException.class)  // 34
	public void blueFliesSparrowAndBreaksGroup() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, makeCoordinate(1, -1), makeCoordinate(0, -2));
	}
	
	@Test  // 35
	public void blueFliesSparrowFourTiles() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(0, 3));
	}
	
	@Test(expected=HantoException.class)  // 36
	public void blueFliesSparrowFiveTiles() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(CRAB, null, makeCoordinate(0, -1));
		game.makeMove(CRAB, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(SPARROW, makeCoordinate(0, -2), makeCoordinate(1, 2));
	}
	
	@Test  // 37
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
	
	@Test(expected=HantoException.class)  // 38
	public void blueMovesHorseOneTileOverZeroTilesInLine() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(HORSE, null, makeCoordinate(2, -2));
		game.makeMove(SPARROW, null, makeCoordinate(2, 1));
		game.makeMove(HORSE, makeCoordinate(2, -2), makeCoordinate(2, -1));
	}
	
	@Test  // 39
	public void blueMovesHorseTwoTilesOverOneTileInLine() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(-1, 0));
		game.makeMove(HORSE, null, makeCoordinate(0, 2));
		MoveResult mr = game.makeMove(HORSE, makeCoordinate(-1, 0), makeCoordinate(1, 0));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(-1, 0));
		assertNull(p);
		p = game.getPieceAt(makeCoordinate(1, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(HORSE, p.getType());
	}
	
	@Test  // 40
	public void blueMovesHorseThreeTilesOverTwoTilesInLine() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(1, 1));
		MoveResult mr = game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(0, 2));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, -1));
		assertNull(p);
		p = game.getPieceAt(makeCoordinate(0, 2));
		assertEquals(BLUE, p.getColor());
		assertEquals(HORSE, p.getType());
	}
	
	@Test  // 41
	public void blueMovesHorseFiveTilesOverFourTilesInLine() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(0, 2));
		game.makeMove(HORSE, null, makeCoordinate(0, -2));
		game.makeMove(HORSE, null, makeCoordinate(1, 2));
		MoveResult mr = game.makeMove(HORSE, makeCoordinate(0, -2), makeCoordinate(0, 3));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, -2));
		assertNull(p);
		p = game.getPieceAt(makeCoordinate(0, 3));
		assertEquals(BLUE, p.getColor());
		assertEquals(HORSE, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 42
	public void blueMovesHorseTwoTilesOverZeroTilesInLineAndBreakGroup() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(0, 2));
		game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(0, -3));
	}
	
	@Test(expected=HantoException.class)  // 43
	public void blueMovesHorseFourTilesOverTwoTileInLineAndNoBreakGroup() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(-1, 0));
		game.makeMove(HORSE, null, makeCoordinate(-1, 2));
		game.makeMove(HORSE, null, makeCoordinate(-1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(HORSE, makeCoordinate(-1, -1), makeCoordinate(-1, 3));
	}
	
	@Test(expected=HantoException.class)  // 44
	public void blueMovesHorseNotInLine() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(0, 2));
		game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(1, 0));
	}
	
	@Test // 45
	public void bluePlacesFirstButterflyOnFourthTurn() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(CRAB, null, makeCoordinate(0, -2));
		game.makeMove(CRAB, null, makeCoordinate(0, 3));
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, -3));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, -3));
		assertEquals(BLUE, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 46
	public void bluePlacesFourthCrabOnFourthTurn() throws HantoException
	{
		game.makeMove(CRAB, null, makeCoordinate(0, 0));
		game.makeMove(CRAB, null, makeCoordinate(0, 1));
		game.makeMove(CRAB, null, makeCoordinate(0, -1));
		game.makeMove(CRAB, null, makeCoordinate(0, 2));
		game.makeMove(CRAB, null, makeCoordinate(0, -2));
		game.makeMove(CRAB, null, makeCoordinate(0, 3));
		game.makeMove(CRAB, null, makeCoordinate(0, -3));
	}
	
	@Test  // 47
	public void redPlacesFirstButterflyOnFourthTurn() throws HantoException
	{
		game.makeMove(CRAB, null, makeCoordinate(0, 0));
		game.makeMove(CRAB, null, makeCoordinate(0, 1));
		game.makeMove(CRAB, null, makeCoordinate(0, -1));
		game.makeMove(CRAB, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -3));
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 4)); 
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, 4));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)  // 48
	public void redPlacesFourthCrabOnFourthTurn() throws HantoException
	{
		game.makeMove(CRAB, null, makeCoordinate(0, 0));
		game.makeMove(CRAB, null, makeCoordinate(0, 1));
		game.makeMove(CRAB, null, makeCoordinate(0, -1));
		game.makeMove(CRAB, null, makeCoordinate(0, 2));
		game.makeMove(CRAB, null, makeCoordinate(0, -2));
		game.makeMove(CRAB, null, makeCoordinate(0, 3));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -3));
		game.makeMove(CRAB, null, makeCoordinate(0, 4));
	}
	
	@Test  // 49
	public void playersPlaceTheirLastPiecesOnBoard() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(CRAB, null, makeCoordinate(0, -3));
		game.makeMove(CRAB, null, makeCoordinate(0, 4));
		game.makeMove(CRAB, null, makeCoordinate(0, -4));
		game.makeMove(CRAB, null, makeCoordinate(0, 5));
		game.makeMove(CRAB, null, makeCoordinate(0, -5));
		game.makeMove(CRAB, null, makeCoordinate(0, 6));
		game.makeMove(CRAB, null, makeCoordinate(0, -6));
		game.makeMove(CRAB, null, makeCoordinate(0, 7));
		game.makeMove(CRAB, null, makeCoordinate(0, -7));
		game.makeMove(CRAB, null, makeCoordinate(0, 8));
		game.makeMove(CRAB, null, makeCoordinate(0, -8));
		game.makeMove(CRAB, null, makeCoordinate(0, 9));
		game.makeMove(HORSE, null, makeCoordinate(0, -9));
		game.makeMove(HORSE, null, makeCoordinate(0, 10));
		game.makeMove(HORSE, null, makeCoordinate(0, -10));
		game.makeMove(HORSE, null, makeCoordinate(0, 11));
		game.makeMove(HORSE, null, makeCoordinate(0, -11));
		game.makeMove(HORSE, null, makeCoordinate(0, 12));
		game.makeMove(HORSE, null, makeCoordinate(0, -12));
		game.makeMove(HORSE, null, makeCoordinate(0, 13));
	}
	
	@Test(expected=HantoException.class)  // 50
	public void bluePlacesExtraHorsePieceOnBoard() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(CRAB, null, makeCoordinate(0, -3));
		game.makeMove(CRAB, null, makeCoordinate(0, 4));
		game.makeMove(CRAB, null, makeCoordinate(0, -4));
		game.makeMove(CRAB, null, makeCoordinate(0, 5));
		game.makeMove(CRAB, null, makeCoordinate(0, -5));
		game.makeMove(CRAB, null, makeCoordinate(0, 6));
		game.makeMove(CRAB, null, makeCoordinate(0, -6));
		game.makeMove(CRAB, null, makeCoordinate(0, 7));
		game.makeMove(CRAB, null, makeCoordinate(0, -7));
		game.makeMove(CRAB, null, makeCoordinate(0, 8));
		game.makeMove(CRAB, null, makeCoordinate(0, -8));
		game.makeMove(CRAB, null, makeCoordinate(0, 9));
		game.makeMove(HORSE, null, makeCoordinate(0, -9));
		game.makeMove(HORSE, null, makeCoordinate(0, 10));
		game.makeMove(HORSE, null, makeCoordinate(0, -10));
		game.makeMove(HORSE, null, makeCoordinate(0, 11));
		game.makeMove(HORSE, null, makeCoordinate(0, -11));
		game.makeMove(HORSE, null, makeCoordinate(0, 12));
		game.makeMove(HORSE, null, makeCoordinate(0, -12));
		game.makeMove(HORSE, null, makeCoordinate(0, 13));
		game.makeMove(HORSE, null, makeCoordinate(0, -13));
	}
	
	@Test(expected=HantoException.class)  // 51
	public void blueMovesUnmovableSurroundedCrab() throws HantoException
	{
		game.makeMove(CRAB, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(-1, 2), makeCoordinate(-1, 1));
		game.makeMove(CRAB, makeCoordinate(0, 0), makeCoordinate(0, -1));
	}
	
	@Test  // 52
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
	
	@Test  // 53
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
	
	@Test  // 54
	public void blueWins() throws HantoException
	{ 
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(-1, 0), makeCoordinate(-1, 1));
		game.makeMove(CRAB, null, makeCoordinate(1, 1));
		final MoveResult mr = game.makeMove(SPARROW, makeCoordinate(1, -1), makeCoordinate(1, 0));
		assertEquals(BLUE_WINS, mr);
	}
	
	@Test  // 55
	public void redWins() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, makeCoordinate(-1, 2), makeCoordinate(-1, 1));
		game.makeMove(CRAB, null, makeCoordinate(0, -2));
		final MoveResult mr = game.makeMove(SPARROW, makeCoordinate(1, 1), makeCoordinate(1, 0));
		assertEquals(RED_WINS, mr);
	}
	
	@Test  // 56
	public void blueAndRedSurroundedSimultaneously() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(-1, 0));
		game.makeMove(HORSE, null, makeCoordinate(-1, 2));
		game.makeMove(CRAB, null, makeCoordinate(0, -1));
		game.makeMove(CRAB, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 2));
		game.makeMove(SPARROW, makeCoordinate(2, -1), makeCoordinate(1, 0));
		MoveResult mr = game.makeMove(SPARROW, makeCoordinate(-2, 2), makeCoordinate(-1, 1));
		assertEquals(DRAW, mr);
	}
		
	@Test(expected=HantoException.class)  // 57
	public void moveMadeAfterGameOver() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(-1, 0));
		game.makeMove(HORSE, null, makeCoordinate(-1, 2));
		game.makeMove(CRAB, null, makeCoordinate(0, -1));
		game.makeMove(CRAB, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(2, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 2));
		game.makeMove(SPARROW, makeCoordinate(2, -1), makeCoordinate(1, 0));
		MoveResult mr = game.makeMove(SPARROW, makeCoordinate(-2, 2), makeCoordinate(-1, 1));
		assertEquals(DRAW, mr);
		game.makeMove(HORSE, null, makeCoordinate(-2, 0));
	}
	
	@Test  // 58
	public void printBoardNotNull() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertNotNull(game.getPrintableBoard());
	}
	
	@Test  // 59
	public void hantoPieceImplEqualsTest() throws HantoException
	{
		HantoPieceImpl piece = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		assertFalse(piece.equals(null));
		assertFalse(piece.equals(game));
	}
	
	@Test  // 60
	public void hantoPieceImplHashCode() throws HantoException
	{
		HantoPieceImpl piece1 = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		HantoPieceImpl piece2 = new HantoPieceImpl(BLUE, BUTTERFLY, new StrategyWalk(1));
		HantoPieceImpl piece3 = new HantoPieceImpl(null, null, new StrategyWalk(1));
		assertEquals(piece1.hashCode(), piece2.hashCode());
		assertNotEquals(piece1.hashCode(), piece3.hashCode());
	}
	
	@Test  // 61
	public void hantoCoordinateImplEquals() throws HantoException
	{
		HantoCoordinateImpl coordinate = new HantoCoordinateImpl(0, 0);
		HantoCoordinateImpl coordinate1 = new HantoCoordinateImpl(0, 1);
		HantoCoordinateImpl coordinate2 = new HantoCoordinateImpl(1, 0);
		assertFalse(coordinate.equals(game));
		assertFalse(coordinate.equals(coordinate1));
		assertFalse(coordinate.equals(coordinate2));
	}
	
	@Test  // 62
	public void hantoBasicCoordinateHashCodeEquals() throws HantoException
	{
		HantoCoordinate coordinate1 = new HantoCoordinateImpl(0, 0).new HantoBasicCoordinate(0, 0);
		HantoCoordinate coordinate2 = new HantoCoordinateImpl(0, 0).new HantoBasicCoordinate(0, 0);
		assertEquals(coordinate1.hashCode(), coordinate2.hashCode());
		assertFalse(coordinate1.equals(game));
		assertFalse(coordinate1.equals(null));
	}
	
	@Test(expected=HantoException.class)  // 63
	public void placingPieceOnOccupiedTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
	}
	
	@Test  // 64
	public void blueResignsWithNoValidMoves() throws HantoException
	{
		game.makeMove(CRAB, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(1, 0));
		game.makeMove(CRAB, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, 1));
		game.makeMove(CRAB, null, makeCoordinate(-2, 2));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 1), makeCoordinate(1, 0));
		game.makeMove(CRAB, null, makeCoordinate(-2, 1));
		game.makeMove(CRAB, makeCoordinate(0, 0), makeCoordinate(-1, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, 1));
		game.makeMove(CRAB, null, makeCoordinate(1, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 1), makeCoordinate(0, 2));
		game.makeMove(HORSE, null, makeCoordinate(2, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 2), makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 3));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 1), makeCoordinate(1, 0));
		game.makeMove(SPARROW, makeCoordinate(-2, 3), makeCoordinate(2, 0));
		MoveResult mr = game.makeMove(null, null, null);
		assertEquals(mr, MoveResult.RED_WINS);
	}
	
	@Test(expected=HantoPrematureResignationException.class)  // 65
	public void blueResignsWithValidMoves() throws HantoException
	{
		MoveResult mr = game.makeMove(null, null, null);
	}
	
	@Test(expected=HantoException.class)  // 66
	public void moveAfterResignation() throws HantoException
	{
		game.makeMove(CRAB, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(BUTTERFLY, makeCoordinate(1, -1), makeCoordinate(1, 0));
		game.makeMove(CRAB, null, makeCoordinate(-1, 2));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, 1));
		game.makeMove(CRAB, null, makeCoordinate(-2, 2));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 1), makeCoordinate(1, 0));
		game.makeMove(CRAB, null, makeCoordinate(-2, 1));
		game.makeMove(CRAB, makeCoordinate(0, 0), makeCoordinate(-1, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 2), makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(1, 1));
		game.makeMove(CRAB, null, makeCoordinate(1, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 1), makeCoordinate(0, 2));
		game.makeMove(HORSE, null, makeCoordinate(2, -1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 2), makeCoordinate(1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(-2, 3));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 1), makeCoordinate(1, 0));
		game.makeMove(SPARROW, makeCoordinate(-2, 3), makeCoordinate(2, 0));
		MoveResult mr = game.makeMove(null, null, null);
		assertEquals(mr, MoveResult.RED_WINS);
		game.makeMove(HORSE, null, makeCoordinate(-2, 3));
	}
	
	@Test(expected=HantoException.class)  // 67
	public void placingPieceOnFarAwayTile() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 4));
	}

	@Test(expected=HantoException.class)  // 68
	public void bluePlacesExtraCrabPieceOnBoard() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(CRAB, null, makeCoordinate(0, -3));
		game.makeMove(CRAB, null, makeCoordinate(0, 4));
		game.makeMove(CRAB, null, makeCoordinate(0, -4));
		game.makeMove(CRAB, null, makeCoordinate(0, 5));
		game.makeMove(CRAB, null, makeCoordinate(0, -5));
		game.makeMove(CRAB, null, makeCoordinate(0, 6));
		game.makeMove(CRAB, null, makeCoordinate(0, -6));
		game.makeMove(CRAB, null, makeCoordinate(0, 7));
		game.makeMove(CRAB, null, makeCoordinate(0, -7));
		game.makeMove(CRAB, null, makeCoordinate(0, 8));
		game.makeMove(CRAB, null, makeCoordinate(0, -8));
		game.makeMove(CRAB, null, makeCoordinate(0, 9));
		game.makeMove(HORSE, null, makeCoordinate(0, -9));
		game.makeMove(HORSE, null, makeCoordinate(0, 10));
		game.makeMove(HORSE, null, makeCoordinate(0, -10));
		game.makeMove(HORSE, null, makeCoordinate(0, 11));
		game.makeMove(HORSE, null, makeCoordinate(0, -11));
		game.makeMove(HORSE, null, makeCoordinate(0, 12));
		game.makeMove(HORSE, null, makeCoordinate(0, -12));
		game.makeMove(HORSE, null, makeCoordinate(0, 13));
		game.makeMove(CRAB, null, makeCoordinate(0, -13));
	}
	
	@Test(expected=HantoException.class)  // 69
	public void bluePlacesExtraSparrowPieceOnBoard() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3));
		game.makeMove(CRAB, null, makeCoordinate(0, -3));
		game.makeMove(CRAB, null, makeCoordinate(0, 4));
		game.makeMove(CRAB, null, makeCoordinate(0, -4));
		game.makeMove(CRAB, null, makeCoordinate(0, 5));
		game.makeMove(CRAB, null, makeCoordinate(0, -5));
		game.makeMove(CRAB, null, makeCoordinate(0, 6));
		game.makeMove(CRAB, null, makeCoordinate(0, -6));
		game.makeMove(CRAB, null, makeCoordinate(0, 7));
		game.makeMove(CRAB, null, makeCoordinate(0, -7));
		game.makeMove(CRAB, null, makeCoordinate(0, 8));
		game.makeMove(CRAB, null, makeCoordinate(0, -8));
		game.makeMove(CRAB, null, makeCoordinate(0, 9));
		game.makeMove(HORSE, null, makeCoordinate(0, -9));
		game.makeMove(HORSE, null, makeCoordinate(0, 10));
		game.makeMove(HORSE, null, makeCoordinate(0, -10));
		game.makeMove(HORSE, null, makeCoordinate(0, 11));
		game.makeMove(HORSE, null, makeCoordinate(0, -11));
		game.makeMove(HORSE, null, makeCoordinate(0, 12));
		game.makeMove(HORSE, null, makeCoordinate(0, -12));
		game.makeMove(HORSE, null, makeCoordinate(0, 13));
		game.makeMove(SPARROW, null, makeCoordinate(0, -13));
	}
	
	@Test(expected=HantoPrematureResignationException.class)  // 70
	public void redResignsWithNoValidMovesHorseTest0() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(0, 2));
		game.makeMove(HORSE, makeCoordinate(0, -1), makeCoordinate(0, 3));
		MoveResult mr = game.makeMove(null, null, null);
	}
	
	@Test(expected=HantoPrematureResignationException.class)  // 71
	public void redResignsWithNoValidMovesHorseTest1() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
		game.makeMove(HORSE, null, makeCoordinate(-1, 0));
		game.makeMove(HORSE, null, makeCoordinate(2, 0));
		game.makeMove(HORSE, makeCoordinate(-1, 0), makeCoordinate(3, 0));
		MoveResult mr = game.makeMove(null, null, null);
	}
	
	@Test(expected=HantoPrematureResignationException.class)  // 72
	public void redResignsWithNoValidMovesHorseTest2() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 1));
		game.makeMove(HORSE, null, makeCoordinate(1, -1));
		game.makeMove(HORSE, null, makeCoordinate(-2, 2));
		game.makeMove(HORSE, makeCoordinate(1, -1), makeCoordinate(-3, 3));
		MoveResult mr = game.makeMove(null, null, null);
	}
	
	@Test(expected=HantoPrematureResignationException.class)  // 73
	public void redResignsWithNoValidMovesHorseTest3() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(HORSE, null, makeCoordinate(0, 1));
		game.makeMove(HORSE, null, makeCoordinate(0, -2));
		game.makeMove(HORSE, makeCoordinate(0, 1), makeCoordinate(0, -3));
		MoveResult mr = game.makeMove(null, null, null);
	}
	
	@Test(expected=HantoPrematureResignationException.class)  // 74
	public void redResignsWithNoValidMovesHorseTest4() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0));
		game.makeMove(HORSE, null, makeCoordinate(1, 0));
		game.makeMove(HORSE, null, makeCoordinate(-2, 0));
		game.makeMove(HORSE, makeCoordinate(1, 0), makeCoordinate(-3, 0));
		MoveResult mr = game.makeMove(null, null, null);
	}
	
	@Test(expected=HantoPrematureResignationException.class)  // 75
	public void redResignsWithNoValidMovesHorseTest5() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, -1));
		game.makeMove(HORSE, null, makeCoordinate(-1, 1));
		game.makeMove(HORSE, null, makeCoordinate(2, -2));
		game.makeMove(HORSE, makeCoordinate(-1, 1), makeCoordinate(3, -3));
		MoveResult mr = game.makeMove(null, null, null);
	}
	
	@Test(expected=HantoPrematureResignationException.class)  // 76
	public void redResignsWithNoValidMovesSparrowTest() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(0, 3));
		MoveResult mr = game.makeMove(null, null, null);
	}
	
}
