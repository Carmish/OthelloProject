# OthelloAIHotello

## Search algorithm
To search for the best move our implementation of the AI uses Heuristic-Alpha-Beta-Search which is a version of the minimax algorithm with alpha-beta pruning and a heuristic evaluation function.

The minimax algorithm is a recursive decision-making algorithm that is used to choose the best move for a player assuming that the opponent is also playing optimally. The algorithm searches through the game tree to a specific set depth to find the best move to make, and it alternates between maximizing the score for the player and minimizing the score for the opponent according to the evaluation function at each level of the tree.

## Alpha-Beta Pruning Implementation
Alpha-beta pruning is integrated into the Minimax search to enhance efficiency by eliminating branches that cannot influence the final decision given that the player plays optimally according to the evaluation function. The algorithm maintains two values:

Alpha (α): The best value found so far for the maximizing player.

Beta (β): The best value found so far for the minimizing player.

During the search, if the maxValue function finds a value greater than or equal to β, further exploration of that branch is unnecessary, as the minimizing player would never allow this scenario and instead play the move with a lesser evaluation value. Similarly, in minValue, if a value less than or equal to α is found, the maximizing player would never choose this path, so further search is cut off.

## Cutoff Conditions
To prevent excessive computation and stay within the time limits, the search tree is cut off under the following conditions:

Game Completion: If the game has reached a terminal state (win, loss, or draw as determined by the GameState interface), further exploration is unnecessary.

Maximum Depth Reached: The recursive search terminates at a pre-defined depth (our default is 7).

When a cutoff occurs, the eval function is used to estimate the value of the current board position instead of continuing deeper into the tree.

## Evaluation function
The evaluation function is the core of the AI(algorithm). It is used to evaluate the current state of the game and determine the best move to make. The evaluation function is used to assign a value to each possible move ordering them from best to worst.

Our implementation uses a simple greedy evaluation function that calculates the difference between the number of pieces of the player and the opponent player. 

The function returns:

- A positive value if the AI has more pieces than the opponent.

- A negative value if the opponent has more pieces.

- Zero if both players have an equal number of pieces.

The AI will choose the move that maximizes the difference between the player's score and the opponent's score and similarly it expects the opponent to choose the move that minimize that difference or in other words maximize the negative of that difference.

This simple heuristic provides a basic measure of board control and maintains correctness in terms of which player wins in a terminal game state. However, it is not very sophisticated and does not directly take into account other factors such as control of corners and edges or minimizing the number of possible moves for the opponent. This means the AI is greedy and does not just focus on winning but winning with the biggest margin possible. It might not be the best strategy against an opponent that is evaluating game states focusing on controlling the board and securing long term advantages.

