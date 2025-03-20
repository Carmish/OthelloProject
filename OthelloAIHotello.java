/**
 * A simple OthelloAI-implementation. The method to decide the next move implements the minimax algorithm with alpha-beta pruning.
 * The utility is calculated by counting the number of tokens of the player and the opponent and returning the difference.
 * @author Carmen Alberte Nielsen & Daniel Sølvsten Millard
 * @version 14.3.2025
 */

public class OthelloAIHotello implements IOthelloAI{

	private class Decision {
		public final int evaluation;
		public final Position move;

		public Decision(int evaluation, Position move) {
			this.evaluation = evaluation;
			this.move = move;
		}
	}

	/**
	 * The maximum depth of the search tree.
	 * All values above 5 easily beats DumAI within the time restrictions up to boards with size 16x16 and likely beyond.
	 * A depth of 8 likely stays within the time limit on 8x8 board but has some cases nearing 30 seconds.
	 * A depth of 6 or 7 is a good balance between speed and performance.
	 * 8 or above is required to beat itself as the starting player on an 8x8 board
	 */
	private int maxDepth = 6;

	// Tracking the "max" player and the "min" player to evaluate from the correct perspective
	private int originalPlayer;
	private int opponent;
	
	public Position decideMove(GameState s){
		// Store the original player when search begins
		originalPlayer = s.getPlayerInTurn();
		opponent = originalPlayer % 2 + 1;
		Decision d = maxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE, maxDepth);
		return d.move;
	}

	private Decision maxValue(GameState s, int alpha, int beta, int depth) {
		if (isCutOff(s, depth)) {
			return new Decision(eval(s), new Position(-1,-1));
		}

		var moves = s.legalMoves();
		
		// Case where the player has no legal moves (pass turn to the other player)
		if (moves.isEmpty()) {
			s.changePlayer();
			return minValue(s, alpha, beta, depth);
		}

		int v = Integer.MIN_VALUE;
		Position maxMove = moves.get(0);

		for (Position move : moves) {
			GameState resultState = new GameState(s.getBoard(), s.getPlayerInTurn());
			resultState.insertToken(move);

			int v2 = minValue(resultState, alpha, beta, depth - 1).evaluation;
			if (v2 > v) {
				v = v2;
				maxMove = move;
				alpha = Math.max(alpha, v);
			}
			if (v >= beta) {
				return new Decision(v, maxMove);
			}
		}
		return new Decision(v, maxMove);
	}

	private Decision minValue(GameState s, int alpha, int beta, int depth) {
		if (isCutOff(s, depth)) {
			return new Decision(eval(s), new Position(-1,-1));
		}

		var moves = s.legalMoves();
		
		// Case where the player has no legal moves (pass turn to the other player)
		if (moves.isEmpty()) {
			s.changePlayer();
			return maxValue(s, alpha, beta, depth);
		}

		int v = Integer.MAX_VALUE;
		Position minMove = moves.get(0);

		for (Position move : moves) {
			GameState resultState = new GameState(s.getBoard(), s.getPlayerInTurn());
			resultState.insertToken(move);

			int v2 = maxValue(resultState, alpha, beta, depth - 1).evaluation;
			if (v2 < v) {
				v = v2;
				minMove = move;
				beta = Math.min(beta, v);
			}
			if (v <= alpha) {
				return new Decision(v, minMove);
			}
		}
		return new Decision(v, minMove);
	}

	private int eval(GameState s) {
		int[] tokens = s.countTokens();
		int playerTokens = tokens[originalPlayer - 1];
		int opponentTokens = tokens[opponent - 1];
		return playerTokens - opponentTokens;
	}

	private boolean isCutOff(GameState s, int depth) {
		return s.isFinished() || depth == 0;
	}
	
}

