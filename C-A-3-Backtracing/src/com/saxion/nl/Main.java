package com.saxion.nl;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        (new Main()).run();
    }

    private void run() {
        // read in the files that contain the board
        BufferedReader nodesReader = null;
        BufferedReader mazeReader = null;
        try {
            nodesReader = this.getFileReader("src/nodes.csv");
            mazeReader = this.getFileReader("src/maze.csv");
        } catch (FileNotFoundException e) {
            System.out.println("[WARN] could not find file, message: " + e.getMessage());
            System.exit(-1);
        }

        ArrayList<Node> nodes = this.buildMaze(nodesReader, mazeReader);

        // Make a new pawn and give it starting position 1
        Pawn pawnOne = new Pawn(nodes.get(0));
        // Make a new pawn and give it starting position 2
        Pawn pawnTwo = new Pawn(nodes.get(1));
        State beginState = new State(pawnOne, pawnTwo);
        Maze maze = new Maze(beginState);

        ArrayList<State> pathTakenToFinish = maze.run();
        for (int i = 0; i < pathTakenToFinish.size(); i++) {
            System.out.println(i + " " + pathTakenToFinish.get(i));
        }
    }

    /**
     * Builds maze from CSV file.
     *
     * @param nodesReader BufferedReader
     * @param mazeReader  BufferedReader
     * @return nodes
     */
    private ArrayList<Node> buildMaze(BufferedReader nodesReader, BufferedReader mazeReader) {
        ArrayList<Node> nodes = new ArrayList<>();
        // loop through each line in the file
        try {
            for (String line = nodesReader.readLine(); line != null; line = nodesReader.readLine()) {
                // create node from line
                String nodeNumber = line.split("\",\"")[0].replace("\"", "");
                String nodeColor = line.split("\",\"")[1].replace("\"", "");
                Node currentNode = new Node(nodeNumber, nodeColor);
                nodes.add(currentNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for (String mazeLine = mazeReader.readLine(); mazeLine != null; mazeLine = mazeReader.readLine()) {
                String[] mazeLineRow = mazeLine.split("\",\"");
                String start = mazeLineRow[0].replace("\"", "");
                String end = mazeLineRow[1].replace("\"", "");
                String color = mazeLineRow[2].replace("\"", "");

                // add the lines between the nodes
                for (Node startNode : nodes) {
                    if (startNode.getNodeNumber().equals(start)) {
                        for (Node endNode : nodes) {
                            if (endNode.getNodeNumber().equals(end)) {
                                startNode.addLine(new Line(startNode, endNode, color));
                            }
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodes;
    }

    /**
     * Getter for fileReader
     *
     * @param path String
     * @return FileReader
     * @throws FileNotFoundException
     */
    private BufferedReader getFileReader(String path) throws FileNotFoundException {
        return new BufferedReader(new FileReader(
                new File(path)));
    }
}
