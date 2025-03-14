import java.util.ArrayList;

/**
 * A simple OthelloAI-implementation. The method to decide the next move just
 * returns the first legal move that it finds. (Copy of DumAI for now)
 * @author Carmen Alberte Nielsen & Daniel Sølvsten Millard
 * @version 14.3.2025
 */
public class OthelloAIHotello implements IOthelloAI{

	/**
	 * Returns first legal move
	 */
	public Position decideMove(GameState s){
		ArrayList<Position> moves = s.legalMoves();
		if ( !moves.isEmpty() )
			return moves.get(0);
		else
			return new Position(-1,-1);
	}
	
}
