package com.saxion.nl;

public class Line {
    private final Node startNode;
    private final Node endNode;
    private final String color;


    public Line(Node startNode, Node endNode, String color) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.color = color;
    }

    /**
     * Returns line color.
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns start node position.
     * @return startNode
     */
    public Node getStartNode() {
        return this.startNode;
    }

    /**
     * Returns ending node.
     * @return endNode
     */
    public Node getEndNode() {
        return endNode;
    }

    @Override
    public String toString() {
        return this.startNode.getNodeNumber() + " -> " +
                this.endNode.getNodeNumber() + " " +
                Color.getFullColor(this.color) + " ";
    }
}
