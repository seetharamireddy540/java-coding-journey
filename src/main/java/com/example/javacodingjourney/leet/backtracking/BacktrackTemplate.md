What is Backtracking?
Backtracking is an algorithmic technique that considers searching every possible combination to solve a computational problem. It builds solutions incrementally and abandons ("backtracks") partial solutions when they cannot lead to a valid complete solution.

Key Concepts
Choice: At each step, make a choice from available options
Constraint: Check if the current choice satisfies problem constraints
Goal: Check if we've reached a solution
Backtrack: If a choice leads to a dead end, undo it and try another option

public class BacktrackingTemplate {
public void backtrack(State state) {
// Base case: if we found a solution
if (isSolution(state)) {
processSolution(state);
return;
}

        // Try all possible choices
        for (Choice choice : getPossibleChoices(state)) {
            if (isValid(state, choice)) {
                // Make the choice
                makeChoice(state, choice);
                
                // Recurse with the new state
                backtrack(state);
                
                // Undo the choice (backtrack)
                undoChoice(state, choice);
            }
        }
    }
}