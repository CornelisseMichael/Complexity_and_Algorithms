package main;

import algorithm.ReplacementSelection;
import test.ConsoleWriter;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    private int maxSize;
    private String pathToReadFrom;
    private ConsoleWriter writer;
    private InputStream input;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run(){

        if (this.writer == null) {
            this.writer = new ConsoleWriter(System.out);
        }

        if(this.input == null) {
            this.input = System.in;
        }

        init();
        ReplacementSelection replacementSelection = new ReplacementSelection(maxSize, pathToReadFrom);
        replacementSelection.run();
    }

    /**
     * Main start of the program
     */
    private void init(){
        Scanner scanner = new Scanner(this.input);

        String[] pathNames;
        File file = new File("data/");
        pathNames = file.list();

        int input;
        if (pathNames != null) {
            this.printPathNames(pathNames);

            System.out.print("Please select which file you want to run:");
            input = scanner.nextInt();
            while (input < 0 || input > pathNames.length){
                this.writer.println("Invalid input try again!");
                this.printPathNames(pathNames);
                System.out.print("Please select which file you want to run:");
                input = scanner.nextInt();
            }
            pathToReadFrom = pathNames[input];
            this.writer.println("you chose " + pathToReadFrom);
        }
        System.out.print("Please select Max Heap size: ");

        while ((maxSize = scanner.nextInt()) <= 0){
            System.out.print("Input cant be Negative! Please try again: ");
            maxSize = scanner.nextInt();
        }
    }

    /**
     * User can choose which input file they are gonna choose
     * by giving the I index in the array
     *
     * @param pathNames String
     */
    private void printPathNames(String[] pathNames){
        for (int i = 0; i < pathNames.length ; i++) {
            this.writer.println((i) + ") " + pathNames[i]);
        }
    }

    /**
     * Used for testing purposes
     *
     * @param writer ConsoleWriter
     */
    public void setOutputStream(ConsoleWriter writer) {
        this.writer = writer;
    }

    /**
     * Used for testing purposes
     *
     * @param inputStream ConsoleWriter
     */
    public void setInputStream(InputStream inputStream) {
        this.input = inputStream;
    }
}
