# OthelloAIHotello

## Search algorithm (minimax)
Our implementation of the AI uses the minimax algorithm to search for the best move. 
The minimax algorithm is a recursive algorithm that is used to choose the best move for a player assuming that the opponent is also playing optimally. The algorithm searches through the game tree to find the best move to make, and it alternates between maximizing the score for the player and minimizing the score for the opponent at each level of the tree.

## Evaluation function (utility function)
The evaluation function is the core of the AI. It is used to evaluate the current state of the game and determine the best move to make. The evaluation function is used to assign a score to each possible move, and the move with the highest score is chosen. 

Our evaluation function is called utility and returns an integer. 
It determines the utility of a given state of the game by calculating the difference between the number of pieces of the player and the opponent player.
This means that the AI will not just try to maximize its own score, but maximize the difference between its score and the opponent's score, since the goal of the game is to have more pieces on the board than the opponent.

## Cut-off function (alpha-beta pruning)
To optimize the search algorithm, we implemented alpha-beta pruning. Alpha-beta pruning is a search algorithm that reduces the number of nodes that are evaluated in the search tree. It is based on the observation that we do not need to evaluate all nodes in the search tree to find the best move. We can prune nodes that are guaranteed to be worse than the best move found so far.
