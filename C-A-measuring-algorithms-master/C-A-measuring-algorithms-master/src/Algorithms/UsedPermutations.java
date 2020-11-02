package Algorithms;

import java.util.Arrays;
import java.util.Random;

public class UsedPermutations {
    private final int n;
    private int[] elements;
    private boolean[] used;

    private Random rand;


    public UsedPermutations(int n) {
        this.n = n;
        this.elements = new int[n];
        this.used = new boolean[elements.length];
        this.rand = new Random();
    }

    public void fill(){
        int randomInt = -1;

        for (int i = 0; i < elements.length ; i++) {
            boolean unique = false;
            while (!unique){
                randomInt =  genRandomInt();
                unique = !used[randomInt];
            }
            used[randomInt] = true;
            elements[i] = (randomInt + 1);
        }

        // System.out.println(Arrays.toString(elements));// just for checking on a smaller number
    }

    private int genRandomInt() {
        return this.rand.nextInt(n);
    }

    public int[] getElements() {
        return elements;
    }

    public boolean[] getUsed() {
        return used;
    }
}
