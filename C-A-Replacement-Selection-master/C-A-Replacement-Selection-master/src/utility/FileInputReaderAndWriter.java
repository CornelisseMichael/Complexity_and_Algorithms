package utility;

import java.io.*;

/**
 * Utility class that performs file reading and writing functionality
 */
public class FileInputReaderAndWriter {
    private String line;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * @param pathToReadFrom filename of the input file we are going to use
     */
    public FileInputReaderAndWriter(String pathToReadFrom) {
        try {
            in = new BufferedReader(new java.io.FileReader("data/" + pathToReadFrom));
            out = new PrintWriter("output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates whether numbers are still present in the input file
     *
     * @return true // the reader can keep reading // false // reader stops reading
     */
    public boolean hasLines() {
        try {
            return (line = in.readLine()) != null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param input to write to file
     */
    private void writeToFile(String input) {
        this.out.print(input);
        this.out.flush();
    }

    /**
     * @param element we want to write to file
     */
    public void writeSmallestToFile(int element) {
        writeToFile(element + "\n");
    }

    /**
     * add to the number of elements
     * and return  the current line to int
     *
     * @return int number of elements in current line
     */
    public int getElement() {
        return Integer.parseInt(line);
    }

    /**
     * write the current run count to a the file
     */
    public void writeRunCountToString(int runCount) {
        if (runCount == 1) {
            writeToFile("Run: " + runCount + "\n");
        } else {
            writeToFile("\nRun: " + runCount + "\n");
        }
    }

    /**
     * Write the average to the file
     */
    public void writeAverageToString(int totalRuns, int totalSum, double average) {
        writeToFile("\nTotal number of runs: " + totalRuns + "\n" +
                "Total number of elements read: " + totalSum + "\n" +
                "Average number of elements read: " + average);
    }

    /**
     * close the writer
     */
    public void closeWriter() {
        this.out.close();
    }
}

