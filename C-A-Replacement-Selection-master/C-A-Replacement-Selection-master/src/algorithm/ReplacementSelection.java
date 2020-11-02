package algorithm;

import datastructure.DynDSHeap;
import utility.FileInputReaderAndWriter;
import utility.ResultHandler;
import utility.RunCounter;

/**
 * Replacement selection algorithm based composed from the pseudo code provided in the slides from week 2
 */
public class ReplacementSelection {

    private int maxSize;
    private FileInputReaderAndWriter readerAndWriter;
    private RunCounter counter;
    private ResultHandler results;

    public ReplacementSelection(int maxSize, String pathToReadFrom) {
        this.maxSize = maxSize;
        readerAndWriter = new FileInputReaderAndWriter(pathToReadFrom);
        counter = new RunCounter();
        results = new ResultHandler();
    }

    public void run() {

        DynDSHeap dynDSHeap = new DynDSHeap(maxSize);
        // read M elements from IN into A
        while (!dynDSHeap.isFull() && readerAndWriter.hasLines()) {
            // buildHeap(A), H=M and D=0
            dynDSHeap.push(readerAndWriter.getElement());
        }
        readerAndWriter.writeRunCountToString(counter.getCurrentRunCount());
        // while IN is not empty
        while (readerAndWriter.hasLines()) {
            // remove smallest element from heap and write this to OUT
            int smallest = dynDSHeap.pop();
            readerAndWriter.writeSmallestToFile(smallest);

            results.increaseRunSize();
            // read next element from IN
            int nextElement = readerAndWriter.getElement();
            // if next >= smallest // next still fits into current run
            if (nextElement >= smallest) {
                // insert next into heap
                dynDSHeap.push(nextElement);
                // else // next does not fit into current run
            } else {
                // H-- and D++ // decrease heapSize, increase dead space
                // insert next into dead space // behind the heap
                dynDSHeap.insertIntoDeadSpace(nextElement);
            }
            // if H==0 // heap is empty
            if (dynDSHeap.isEmpty()) {
                results.addRunSize();
                //  buildHeap(dead space), H=M and D=0
                System.out.println("Building Heap from deadSpace");
                dynDSHeap = new DynDSHeap(dynDSHeap.buildHeapFromDeadSpace());
                readerAndWriter.writeRunCountToString(counter.getCurrentRunCount());

                results.resetRunSize();
            }
        }
        while (!dynDSHeap.isEmpty()) {
            // write rest of heap to OUT
            readerAndWriter.writeSmallestToFile(dynDSHeap.pop());

            results.increaseRunSize();
        }
        results.addRunSize();
        results.resetRunSize();
        // buildHeap(dead space)
        dynDSHeap = new DynDSHeap(dynDSHeap.buildHeapFromDeadSpace());

        if (!dynDSHeap.isEmpty()) {
            readerAndWriter.writeRunCountToString(counter.getCurrentRunCount());
            while (!dynDSHeap.isEmpty()) {
                //  write rest to OUT
                readerAndWriter.writeSmallestToFile(dynDSHeap.pop());

                results.increaseRunSize();
            }
            results.addRunSize();
        }
        readerAndWriter.writeAverageToString(counter.getTotalRunCount(), results.getTotalSumOfElements(), results.getAverage());
        readerAndWriter.closeWriter();
    }
}