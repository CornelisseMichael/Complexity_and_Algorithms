package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Utility class that allows the user to generate txt files to feed to the replacement selection algorithm
 */
public class InputFileHandler {

    /**
     * Directory where we store the input files
     */
    private static final String INPUT_FILE_OUTPUT_FOLDER_PATH = "data";

    /**
     *
     * @param nNumbers number of items going to be used
     */
    private InputFileHandler(int nNumbers) {
        try{
            File file = new File("data/" + nNumbers + "-n.txt");
            FileWriter fw = new FileWriter(file);
            Random random = new Random();
            for (int i = 0; i < nNumbers; i++) {
                fw.write( random.nextInt(nNumbers + 1) + "\n");
            }
            System.out.println("File with filename: " + file + " Is successfully created.");
            fw.close();

        }catch (IOException io){
            io.printStackTrace();
        }
    }

    /**
     * Generates output directory to put input files in
     */
    private static void generateDataFolder(){
        new File(INPUT_FILE_OUTPUT_FOLDER_PATH).mkdir();
    }

    /**
     *
     * @param args arguments for the program
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String nNumbers = "";
        try{
            while (!nNumbers.equals("q")) {
                System.out.println("Please enter the range you want to generate an input file for press q to exit");
                nNumbers = in.next();
                generateDataFolder();
                new InputFileHandler(Integer.parseInt(nNumbers));
            }
        }catch (NumberFormatException e){
            System.out.println("Exiting input file generator..");
        }
    }
}
