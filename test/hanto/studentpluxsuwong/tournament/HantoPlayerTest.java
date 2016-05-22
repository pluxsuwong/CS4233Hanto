package hanto.studentpluxsuwong.tournament;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentpluxsuwong.common.HantoCoordinateImpl;
import hanto.tournament.HantoMoveRecord;

public class HantoPlayerTest
{
	private HantoCoordinate origin;
	private HantoPlayer hantoPlayer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{}

	@Before
	public void setUp() throws Exception {
		origin = new HantoCoordinateImpl(0, 0);
		hantoPlayer = new HantoPlayer();
	}


	@Test
	public void testHantoPlayerIMoveFirst() {
		hantoPlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		HantoMoveRecord myFirstMove = hantoPlayer.makeMove(null);
		assertNotNull(myFirstMove.getPiece());
		assertNull(myFirstMove.getFrom());
		assertEquals(new HantoCoordinateImpl(myFirstMove.getTo()), origin);
	}
	
	@Test
	public void testHantoPlayerIMoveFirstRed() {
		hantoPlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, true);
		HantoMoveRecord myFirstMove = hantoPlayer.makeMove(null);
		assertNotNull(myFirstMove.getPiece());
		assertNull(myFirstMove.getFrom());
		assertEquals(new HantoCoordinateImpl(myFirstMove.getTo()), origin);
	}
	
	@Test
	public void testHantoPlayerIMoveSecond() {
		hantoPlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, false);
		
		HantoMoveRecord myFirstMove = hantoPlayer.makeMove(
				new HantoMoveRecord(HantoPieceType.CRAB, null, origin));
		assertNotNull(myFirstMove.getPiece());
		assertNull(myFirstMove.getFrom());
		assertNotEquals(new HantoCoordinateImpl(myFirstMove.getTo()), origin);
	}
	
	@Test
	public void testHantoPlayerIMoveSecondRed() {
		hantoPlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
		
		HantoMoveRecord myFirstMove = hantoPlayer.makeMove(
				new HantoMoveRecord(HantoPieceType.CRAB, null, origin));
		assertNotNull(myFirstMove.getPiece());
		assertNull(myFirstMove.getFrom());
		assertNotEquals(new HantoCoordinateImpl(myFirstMove.getTo()), origin);
	}
	
	@Test
	public void testOpponentMakesInvalidMove() {
		hantoPlayer.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, false);
		HantoMoveRecord myFirstMove = hantoPlayer.makeMove(new HantoMoveRecord(HantoPieceType.CRAB, null, null));
		assertNotNull(myFirstMove.getPiece());
		assertNull(myFirstMove.getFrom());
		assertEquals(new HantoCoordinateImpl(myFirstMove.getTo()), origin);
	}
	
	@Test
	public void testAgainstSelf1() {
		HantoPlayer player1 = new HantoPlayer();
        HantoPlayer player2 = new HantoPlayer();
        
        player1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
        player2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
        
        HantoMoveRecord record = player1.makeMove(null);
        for (int i = 0; i < 100; i++) {
            switch (i % 2) {
            case 0:
                record = player2.makeMove(record);
                break;
            case 1:
                record = player1.makeMove(record);
                break;
            }
        }
	}
	
	@Test
    public void testAgainstSelf2() {
        HantoPlayer player1 = new HantoPlayer();
        HantoPlayer player2 = new HantoPlayer();
        
        player1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, true);
        player2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, false);
        
        HantoMoveRecord record = player1.makeMove(null);
        for (int i = 0; i < 100; i++) {
            switch (i % 2) {
            case 0:
                record = player2.makeMove(record);
                break;
            case 1:
                record = player1.makeMove(record);
                break;
            }
        }
    }
	
}
