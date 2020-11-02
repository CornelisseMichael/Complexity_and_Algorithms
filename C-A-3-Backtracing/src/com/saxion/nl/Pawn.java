package com.saxion.nl;

import java.util.ArrayList;

public class Pawn {

    private Node currentLocation;
    private ArrayList<Node> visitedNodes = new ArrayList<>();

    public Pawn(Node currentLocation) {
        this.currentLocation = currentLocation;
        this.visitedNodes.add(this.currentLocation);
    }

    /**
     * Returns array of lines.
     *
     * @return lines
     */
    public ArrayList<Line> getLines() {
        return currentLocation.getLines();
    }

    /**
     * Returns nodeColor of current location on the maze.
     *
     * @return current location nodeColor
     */
    public String getColor() {
        return currentLocation.getNodeColor();
    }

    /**
     * Returns current location.
     *
     * @return currentLocation
     */
    public Node getCurrentLocation() {
        return this.currentLocation;
    }

    public boolean isOn(Node node) {
        return this.currentLocation.equals(node);
    }

    public void moveOver(Line line) {
        if (!this.visitedNodes.contains(currentLocation)) {
            this.visitedNodes.add(currentLocation);
        }
        this.currentLocation = line.getEndNode();
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "currentLocation=" + currentLocation +
                ", visitedNodes=" + visitedNodes +
                '}';
    }
}
