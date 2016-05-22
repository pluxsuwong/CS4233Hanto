/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentpluxsuwong.common;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * @author pluxsuwong
 * HantoPieceFactory interface
 */
public interface HantoPieceFactory
{

	/** The factory method for making a hanto piece
	 * @param currentPlayer the color of the piece
	 * @param pieceType the type of the piece
	 * @return the piece
	 */
	HantoPieceImpl makeHantoPiece(HantoPlayerColor currentPlayer, HantoPieceType pieceType);

}
