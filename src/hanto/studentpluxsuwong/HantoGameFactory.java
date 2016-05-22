/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentpluxsuwong;

import hanto.common.*;
import hanto.studentpluxsuwong.beta.BetaHantoGame;
import hanto.studentpluxsuwong.delta.DeltaHantoGame;
import hanto.studentpluxsuwong.epsilon.EpsilonHantoGame;
import hanto.studentpluxsuwong.gamma.GammaHantoGame;

/**
 * This is a singleton class that provides a factory to create an instance of any version
 * of a Hanto game.
 * 
 * @author gpollice
 * @version Feb 5, 2013
 */
public class HantoGameFactory
{
	private static final HantoGameFactory instance = new HantoGameFactory();
	
	/**
	 * Default private descriptor.
	 */
	private HantoGameFactory()
	{
		// Empty, but the private constructor is necessary for the singleton.
	}

	/**
	 * @return the instance
	 */
	public static HantoGameFactory getInstance()
	{
		return instance;
	}
	
	/**
	 * Create the specified Hanto game version with the Blue player moving
	 * first.
	 * @param gameId the version desired.
	 * @return the game instance
	 */
	public HantoGame makeHantoGame(HantoGameID gameId)
	{
		return makeHantoGame(gameId, HantoPlayerColor.BLUE);
	}
	
	/**
	 * Factory method that returns the appropriately configured Hanto game.
	 * @param gameId the version desired.
	 * @param movesFirst the player color that moves first
	 * @return the game instance
	 */
	public  HantoGame makeHantoGame(HantoGameID gameId, HantoPlayerColor movesFirst)
	{
		HantoGame game = null;
		switch (gameId) {
			case BETA_HANTO:
				BetaHantoGame modBetaGame = new BetaHantoGame();
				modBetaGame.setFirstPlayer(movesFirst);
				game = modBetaGame;
				break;
			case GAMMA_HANTO:
				GammaHantoGame modGammaGame = new GammaHantoGame();
				modGammaGame.setFirstPlayer(movesFirst);
				game = modGammaGame;
				break;
			case DELTA_HANTO:
				DeltaHantoGame modDeltaGame = new DeltaHantoGame();
				modDeltaGame.setFirstPlayer(movesFirst);
				game = modDeltaGame;
				break;
			case EPSILON_HANTO:
				EpsilonHantoGame modEpsilonGame = new EpsilonHantoGame();
				modEpsilonGame.setFirstPlayer(movesFirst);
				game = modEpsilonGame;
				break;
			default:
				break;
		}
		return game;
	}
}
