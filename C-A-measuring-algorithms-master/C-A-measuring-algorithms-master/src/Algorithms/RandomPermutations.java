package Algorithms;

import java.util.Arrays;
import java.util.Random;

public class RandomPermutations {

    private final int n;
    private int[] elements;
    private Random rand;
    public RandomPermutations(int n) {
        this.n = n;
        this.elements = new int[n];
        rand = new Random();
    }


    public void fill() {
        for (int i = 0; i < this.n; i++) {

            int randomInt = -1;

            boolean unique = false;

            while (!unique) {
                unique = true;
                randomInt = this.genRandomInt();

                for (int integer : this.elements) {

                    if (randomInt == integer) {
                        unique = false;
                        break;
                    }
                }
            }
            this.elements[i] = randomInt;
        }
      //  System.out.println(Arrays.toString(elements));
    }

    private int genRandomInt() {
        return this.rand.nextInt(this.n) + 1;
    }

    public int[] getElements() {
        return this.elements;
    }
}