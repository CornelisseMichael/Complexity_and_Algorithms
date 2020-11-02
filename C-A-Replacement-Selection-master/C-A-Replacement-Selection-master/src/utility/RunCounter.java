package utility;

/**
 * Utility class to manage runCount
 */
public class RunCounter {
    private int runCount;

    public RunCounter() {
        this.runCount = 0;
    }

    /**
     * Gets the runCount
     * @return current runCount
     */
    public int getCurrentRunCount() {
        // we increase runCount whenever we get current runCount to avoid redundancy
        this.incrementRunCount();
        return runCount;
    }

    /**
     * Returns total runCount
     * @return final runCount
     */
    public final int getTotalRunCount(){
        return runCount;
    }

    /**
     * Increases runCount
     */
    private void incrementRunCount(){
        this.runCount++;
    }
}
