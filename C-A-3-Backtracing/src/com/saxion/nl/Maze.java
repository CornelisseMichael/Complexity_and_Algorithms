package com.saxion.nl;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final State beginState;
    private ArrayList<State> states = new ArrayList<>();
    private ArrayList<State> solution = new ArrayList<>();

    public Maze(State beginState) {
        this.beginState = beginState;
    }

    public ArrayList<State> run() {
        return dfs(beginState, this.states);
    }

    /**
     * Main algorithm of the program performing depth first search
     *
     * @param start   State
     * @param visited ArrayList
     * @return solution when found
     */
    private ArrayList<State> dfs(State start, ArrayList<State> visited) {

        visited.add(start);

        boolean isStartGoal = isGoalState(start);
        if (isStartGoal) {
            solution.add(start);
            return solution;
        } else {
            List<State> neighbours = getNeighbours(start);
            for (State neighbour : neighbours) {
                if (!visitedContainsNeighbour(visited, neighbour)) {
                    solution = dfs(neighbour, visited);
                    if (goalIsReached(solution)) {
                        solution.add(0, start);
                        return solution;
                    }
                }
            }
        }
        return solution;
    }

    /**
     * Validates whether visited nodes contain neighbours
     *
     * @param visited    ArrayList
     * @param checkState State
     * @return true or false
     */
    private boolean visitedContainsNeighbour(ArrayList<State> visited, State checkState) {
        for (State state : visited) {
            String visitedPawnLocation = state.getPawnOne().getCurrentLocation().getNodeNumber();
            String visitedPawnTwoLocation = state.getPawnTwo().getCurrentLocation().getNodeNumber();

            String statePawn = checkState.getPawnOne().getCurrentLocation().getNodeNumber();
            String statePawnTwo = checkState.getPawnTwo().getCurrentLocation().getNodeNumber();
            if (
                    (visitedPawnLocation.equals(statePawn) || visitedPawnTwoLocation.equals(statePawn))
                            &&
                            (visitedPawnLocation.equals(statePawnTwo) || visitedPawnTwoLocation.equals(statePawnTwo))
            ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates whether goal is reached.
     *
     * @param solution ArrayList
     * @return true or false
     */
    private boolean goalIsReached(ArrayList<State> solution) {
        if (solution.size() == 0) {
            return false;
        }
        State state = solution.get(solution.size() - 1);
        return state.getPawnOne().getCurrentLocation().isFinish() || state.getPawnTwo().getCurrentLocation().isFinish();

    }

    /**
     * Returns list of all possible neighbours of the current position.
     *
     * @param currentState State
     * @return neighbours
     */
    private List<State> getNeighbours(State currentState) {
        ArrayList<State> neighbours = new ArrayList<>();
        ArrayList<Line> lines = currentState.getPossibleMoves();
        for (Line line : lines) {
            if (currentState.getPawnOne().getCurrentLocation().equals(line.getStartNode())) {
                Pawn newPawnPosition = new Pawn(line.getEndNode());
                neighbours.add(new State(newPawnPosition, currentState.getPawnTwo()));
            }
            if (currentState.getPawnTwo().getCurrentLocation().equals(line.getStartNode())) {
                Pawn newPawnPosition = new Pawn(line.getEndNode());
                neighbours.add(new State(currentState.getPawnOne(), newPawnPosition));
            }
        }
        return neighbours;
    }

    /**
     * Validates whether goal state is reached.
     *
     * @param start State
     * @return goalState (true or false)
     */
    private boolean isGoalState(State start) {
        return start.getPawnOne().getCurrentLocation().isFinish() ||
                start.getPawnTwo().getCurrentLocation().isFinish();
    }
}
