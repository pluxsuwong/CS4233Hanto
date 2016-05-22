/*
 * Epsilon Hanto Piece Factory.
 */
package hanto.studentpluxsuwong.epsilon;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentpluxsuwong.common.HantoPieceFactory;
import hanto.studentpluxsuwong.common.HantoPieceImpl;
import hanto.studentpluxsuwong.common.StrategyFly;
import hanto.studentpluxsuwong.common.StrategyJump;
import hanto.studentpluxsuwong.common.StrategyWalk;

/**
 * @author pluxsuwong
 * Epsilon Hanto Piece Factory for good designs
 */
public class EpsilonHantoPieceFactory implements HantoPieceFactory
{
	private static final EpsilonHantoPieceFactory instance = new EpsilonHantoPieceFactory();
	
	/**
	 * Default private descriptor.
	 */
	private EpsilonHantoPieceFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static EpsilonHantoPieceFactory getInstance()
	{
		return instance;
	}
	
	public HantoPieceImpl makeHantoPiece(HantoPlayerColor color, HantoPieceType type)
	{
		HantoPieceImpl piece = null;
		switch (type) {
			case BUTTERFLY:
				piece = new HantoPieceImpl(color, type, new StrategyWalk(1));
				break;
			case SPARROW:
				piece = new HantoPieceImpl(color, type, new StrategyFly(4));
				break;
			case CRAB:
				piece = new HantoPieceImpl(color, type, new StrategyWalk(1));
				break;
			case HORSE:
				piece = new HantoPieceImpl(color, type, new StrategyJump());
				break;
			default:
				break;
		}
		return piece;
	}
}
