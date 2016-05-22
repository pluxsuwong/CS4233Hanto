/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentpluxsuwong.tournament;

import static hanto.common.HantoPlayerColor.*;

import hanto.common.*;
import hanto.studentpluxsuwong.epsilon.EpsilonHantoGame;
import hanto.tournament.*;

/**
 * Description
 * @version Oct 13, 2014
 */
public class HantoPlayer implements HantoGamePlayer
{
	private EpsilonHantoGame game;
	private HantoPlayerColor myColor;
	boolean doIMoveFirst;
	
	/*
	 * @see hanto.tournament.HantoGamePlayer#startGame(hanto.common.HantoGameID, hanto.common.HantoPlayerColor, boolean)
	 */
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor,
			boolean doIMoveFirst)
	{
		this.doIMoveFirst = doIMoveFirst;
		this.myColor = myColor;
		game = new EpsilonHantoGame();
		if ((!doIMoveFirst && myColor == BLUE) || ((doIMoveFirst) && myColor == RED)) {
			game.setFirstPlayer(RED);
		}
	}

	/*
	 * @see hanto.tournament.HantoGamePlayer#makeMove(hanto.tournament.HantoMoveRecord)
	 */
	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove)
	{
		MoveResult mr = MoveResult.OK;
		if (opponentsMove != null) {
			try {
				mr = game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
			} catch (HantoException e) {
				System.out.println("Invalid opponent move: " + e.getMessage());
			}
		}
		
		HantoMoveRecord myMove = game.getNextMove(myColor);
		if (mr == MoveResult.OK) {
			try {
				game.makeMove(myMove.getPiece(), myMove.getFrom(), myMove.getTo());
			} catch (HantoException e) {
				System.out.println("Invalid move performed by myself: " + e.getMessage());
			}
		}
		return myMove;
	}

}