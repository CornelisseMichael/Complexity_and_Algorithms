package Algorithms;

import java.util.Arrays;
import java.util.Random;

public class SwapPermutation {
    private final int n;
    private int[] elements;
    private Random rand;

    public SwapPermutation(int n) {
        this.n = n;
        this.elements = new int[n];
        rand = new Random();
    }

    public void fill() {

        for (int i = 0; i < n; i++) {
            elements[i] = (i + 1);

            int randomInt = genRandomInt(i);

            int temp = elements[randomInt];

            elements[randomInt] = (i + 1);

            elements[i] = temp;

        }

       // System.out.println(Arrays.toString(elements)); //just for checking on a smaller number

    }

    private int genRandomInt(int i) {
        return this.rand.nextInt(i + 1);
    }

    public int[] getElements() {
        return this.elements;
    }
}
