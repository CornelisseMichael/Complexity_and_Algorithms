package utility;

import java.util.ArrayList;

/**
 * Utility class that handles results produced by the algorithm
 */
public class ResultHandler {
    private int runSize;
    private ArrayList<Integer> runSizes;

    public ResultHandler() {
        this.runSize = 0;
        this.runSizes = new ArrayList<>();
    }

    public void increaseRunSize() {
        this.runSize++;
    }

    /**
     * reset the runSize
     */
    public void resetRunSize() {
        this.runSize = 0;
    }

    /**
     * add a runSize to the runSizes
     */
    public void addRunSize() {
        this.runSizes.add(runSize);
    }

    public int getRunSize() {
        return runSize;
    }

    /**
     * Get the total of elements read
     *
     * @return total sum of elements
     */
    public int getTotalSumOfElements() {
        int sum = 0;
        for (int total : runSizes) {
            sum += total;
        }
        return sum;
    }

    /**
     * Get the average of all runs
     *
     * @return computed average
     */
    public double getAverage() {
        int sum = getTotalSumOfElements();
        return sum / (double) runSizes.size();
    }
}
