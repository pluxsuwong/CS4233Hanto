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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentpluxsuwong.common.HantoPieceFactory;
import hanto.studentpluxsuwong.common.HantoPieceImpl;
import hanto.studentpluxsuwong.common.StrategyWalk;

/**
 * @author pluxsuwong
 * Beta implementation for the HantoPieceFactory
 */
public class BetaHantoPieceFactory implements HantoPieceFactory
{
	private static final BetaHantoPieceFactory instance = new BetaHantoPieceFactory();
	
	/**
	 * Default private descriptor.
	 */
	private BetaHantoPieceFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static BetaHantoPieceFactory getInstance()
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
				piece = new HantoPieceImpl(color, type, new StrategyWalk(1));
				break;
			default:
				break;
		}
		return piece;
	}
}
