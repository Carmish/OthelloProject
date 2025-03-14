
/**
 * A simple OthelloAI-implementation. The method to decide the next move depends on the minimax algorithm,
 * looking at the possible moves and choosing the move that results in the highest utility (reachable in the range of the depth).
 * The utility is calculated by counting the number of tokens of the player and the opponent and
 * returning the difference.
 * @author Carmen Alberte Nielsen & Daniel Sølvsten Millard
 * @version 14.3.2025
 */
public class OthelloAIHotello implements IOthelloAI{

	private int depth;

	private class Decision {
		public final int utility;
		public final Position move;

		public Decision(int utility, Position move) {
			this.utility = utility;
			this.move = move;
		}
	}
	
	public Position decideMove(GameState s){
		Decision d = maxValue(s);
		depth = 0;
		return d.move;
	}

	private Decision maxValue(GameState s) {
		depth++;
		if (s.isFinished() || depth > 20) {
			return new Decision(utility(s), null);
		}

		int v = Integer.MIN_VALUE;
		Position maxMove = null;

		for (Position move : s.legalMoves()) {
			GameState resultState = new GameState(s.getBoard(), s.getPlayerInTurn());
			resultState.insertToken(move);

			int v2 = minValue(resultState).utility;
			if (v2 > v) {
				v = v2;
				maxMove = move;
			}
		}
		return new Decision(v, maxMove);
	}

	private Decision minValue(GameState s) {
		if (s.isFinished() || depth > 20) {
			return new Decision(utility(s), null);
		}

		int v = Integer.MAX_VALUE;
		Position minMove = null;

		for (Position move : s.legalMoves()) {
			GameState resultState = new GameState(s.getBoard(), s.getPlayerInTurn());
			resultState.insertToken(move);

			int v2 = maxValue(resultState).utility;
			if (v2 < v) {
				v = v2;
				minMove = move;
			}
		}
		return new Decision(v, minMove);
	}

	private int utility(GameState s) {
		int[] tokens = s.countTokens();
		int player = s.getPlayerInTurn();
		int opponent = player % 2 + 1;
		int playerTokens = tokens[player - 1];
		int opponentTokens = tokens[opponent - 1];
		return playerTokens - opponentTokens;
	}
	
}

