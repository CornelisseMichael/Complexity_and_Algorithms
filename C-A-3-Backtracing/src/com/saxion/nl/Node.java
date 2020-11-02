package com.saxion.nl;

import java.util.ArrayList;

public class Node {

    private boolean isFinish;
    private String nodeNumber;
    private String nodeColor;

    /**
     * The connections to the other nodes
     */
    private ArrayList<Line> lines = new ArrayList<>();

    /**
     * @param nodeNumber number representing this node on the board
     * @param nodeColor  color that the node has on the board
     */
    Node(String nodeNumber, String nodeColor) {
        this.nodeColor = nodeColor;
        this.nodeNumber = nodeNumber;

        if (this.nodeNumber.equals("FINISH")) {
            this.isFinish = true;
        }
    }

    /**
     * Returns node color.
     *
     * @return nodeColor
     */
    public String getNodeColor() {
        return nodeColor;
    }

    /**
     * Set nodeColor.
     *
     * @param nodeColor String
     */
    public void setNodeColor(String nodeColor) {
        this.nodeColor = nodeColor;
    }

    /**
     * Returns node number.
     *
     * @return nodeNumber
     */
    public String getNodeNumber() {
        return nodeNumber;
    }

    /**
     * Set nodeNumber.
     *
     * @param nodeNumber String
     */
    public void setNodeNumber(String nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    /**
     * Adds a line to the other lines indicating that
     * their is a new connection to another node
     *
     * @param line Line
     */
    public void addLine(Line line) {
        this.lines.add(line);
    }

    /**
     * Returns a list of all lines.
     *
     * @return lines
     */
    public ArrayList<Line> getLines() {
        return this.lines;
    }

    /**
     * Validates whether finish is reached.
     *
     * @return isFinish
     */
    public boolean isFinish() {
        return this.isFinish;
    }

    /**
     * @return String representing the state of the current node
     */
    @Override
    public String toString() {
        if (this.isFinish) {
            return "FINISH";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(this.nodeNumber).append(" ").append(Color.getFullColor(nodeColor)).append(" [ ");
        for (Line line : this.lines) {
            builder.append(line);
        }
        builder.append(" ]");
        return builder.toString();
    }
}
