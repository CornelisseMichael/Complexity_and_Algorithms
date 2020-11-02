package com.saxion.nl;

import java.util.ArrayList;

public class State {
    private Pawn pawnOne, pawnTwo;

    public State(Pawn pawnOne, Pawn pawnTwo) {
        this.pawnOne = pawnOne;
        this.pawnTwo = pawnTwo;
    }

    public static boolean hasReachedFinish(State state) {
        return state.pawnOne.getCurrentLocation().isFinish() || state.pawnTwo.getCurrentLocation().isFinish();
    }

    /**
     * Getter for pawnOne.
     *
     * @return pawnOne
     */
    public Pawn getPawnOne() {
        return this.pawnOne;
    }

    /**
     * Getter for pawnTwo
     *
     * @return pawnTwo
     */
    public Pawn getPawnTwo() {
        return this.pawnTwo;
    }

    /**
     * Returns list of all possible moves.
     *
     * @ list of possible moves
     */
    public ArrayList<Line> getPossibleMoves() {
        ArrayList<Line> possibleMoves = new ArrayList<>();

        ArrayList<Line> pawnOneLines = this.getPawnOne().getLines();
        ArrayList<Line> pawnTwoLines = this.getPawnTwo().getLines();

        for (Line line : pawnOneLines) {
            if (line.getColor().equals(pawnTwo.getColor())) {
                possibleMoves.add(line);
            }
        }

        for (Line line : pawnTwoLines) {
            if (line.getColor().equals(pawnOne.getColor())) {
                possibleMoves.add(line);
            }
        }
        return possibleMoves;
    }

    @Override
    public String toString() {
        return "State{" +
                "pawnOne=" + pawnOne.getCurrentLocation() +
                ", pawnTwo=" + pawnTwo.getCurrentLocation() +
                '}';
    }
}
