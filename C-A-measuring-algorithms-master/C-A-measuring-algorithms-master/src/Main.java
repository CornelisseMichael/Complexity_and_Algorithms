import Algorithms.RandomPermutations;
import Algorithms.SwapPermutation;
import Algorithms.UsedPermutations;

import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        (new Main()).run();
    }

    /**
     * All the n numbers the algorithms are going through
     */
    private final int[] nSequence = {

            5000,
            10000,
            50000,
            100000,
            500000,
           // 1000000,
           // 5000000,
           // 10000000,
         //   50000000,
        //    100000000
    };


    /**
     * Times the algorithms are being run per n
     */
    private final int iterations = 10;

    /**
     * Storage of the miliseconds of the current iteration of the algorithm
     */
    private long[] currentNSequenceTimes = new long[iterations];

    /**
     * Algorithm selection options
     */
    private static final int RANDOM_PERMUTATION = 1, USED_PERMUTATION = 2, SWAP_PERMUTATION = 3;

    /**
     * Scanner for program options
     */
    private Scanner input = new Scanner(System.in);

    /**
     * Keeps state of the program
     */
    private boolean isRunning = true;


    private void run() {
        int low = 1;
        int high = 3;

        while (isRunning) {
            printMenu();
            int select = geValidInput(low, high);

            long startOfCurrentIteration;
            long totalRunTime = 0;

            for (int currentN : nSequence) {
                System.out.println("Starting nSequence " + currentN);
                for (int j = 0; j < iterations; j++) {
                    // Keep track of the start time of the current iteration
                    startOfCurrentIteration = System.currentTimeMillis();

                    this.startUserSelectedAlgorithm(select, currentN);

                    // calculate total elapsed time of each run
                    long elapsedTime = (System.currentTimeMillis() - startOfCurrentIteration);

                    totalRunTime += elapsedTime;
                    this.currentNSequenceTimes[j] = elapsedTime;
                    System.out.println("elapsed time: " + elapsedTime + "ms");
                }
                System.out.println(Arrays.toString(this.currentNSequenceTimes));
                System.out.println("Average of nSequence " + currentN + " is " + this.getAverageOfArray(this.currentNSequenceTimes));
            }
            System.out.println("All tests together took: " + totalRunTime + "ms");

            // overwrite higher bound for second option menu
            high = 2;

            System.out.println("Do you want to run tests again?\n" +
                    "1) Yes\n" +
                    "2) No");
            System.out.print(": ");
            int choice = geValidInput(low, high);
            if (choice == 1) {
                run();
            } else {
                isRunning = false;
                System.out.println("Exiting program..");
            }
        }
    }

    /**
     *
     * @param select option number in user facing part of app
     * @param currentN
     */
    private void startUserSelectedAlgorithm(int select, int currentN) {
        if (select == RANDOM_PERMUTATION) {
            RandomPermutations randomPermutations = new RandomPermutations(currentN);
            randomPermutations.fill();
        } else if (select == USED_PERMUTATION) {
            UsedPermutations usedPermutations = new UsedPermutations(currentN);
            usedPermutations.fill();
        } else if (select == SWAP_PERMUTATION) {
            SwapPermutation swapPermutation = new SwapPermutation(currentN);
            swapPermutation.fill();
        }
    }


    private int getAverageOfArray(long[] currentNSequenceTimes) {
        Arrays.sort(currentNSequenceTimes);
        int total = 0;
        for (int i = 1; i < currentNSequenceTimes.length - 1; i++) {
            total += currentNSequenceTimes[i];
        }
        return total / (currentNSequenceTimes.length - 2);
    }

    private int geValidInput(int lowerBound, int higherBound) {
        int choice = input.nextInt();
        while (choice < lowerBound || choice > higherBound) {
            System.out.print("Invalid input! Try again: ");
            choice = input.nextInt();
        }
        return choice;
    }

    private void printMenu() {
        System.out.println
                ("Please select which algorithm you want to run..\n" +
                        "1) Random Permutation\n" +
                        "2) Used Permutation\n" +
                        "3) Swap Permutation");

        System.out.print(": ");
    }
}
