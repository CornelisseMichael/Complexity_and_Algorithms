package test;

import main.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

class MainTest {

    @Test
    void whenSuccessfullyCreatesOutputFile() {
        new File("output.txt").delete();
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        ConsoleWriter writer = new ConsoleWriter(new PrintStream(outSpy));

        Main main = new Main();
        main.setOutputStream(writer);
        System.setIn(new ByteArrayInputStream("0\n10".getBytes()));
        MainRunThread mainRunThread = new MainRunThread(main);

        mainRunThread.start();

        this.slowdownForWindows();

        Assertions.assertTrue(new File("output.txt").exists());
    }

    @Test
    void whenSuccessfullyWritesToOutputFile() {
        new File("output.txt").delete();
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        ConsoleWriter writer = new ConsoleWriter(new PrintStream(outSpy));

        Main main = new Main();
        main.setOutputStream(writer);
        System.setIn(new ByteArrayInputStream("0\n10".getBytes()));
        MainRunThread mainRunThread = new MainRunThread(main);

        mainRunThread.start();

        this.slowdownForWindows();

        Assertions.assertTrue(new File("output.txt").length() != 0);
    }

    @Test
    void outputsRunsToTheFile() {
        new File("output.txt").delete();
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        ConsoleWriter writer = new ConsoleWriter(new PrintStream(outSpy));

        Main main = new Main();
        main.setOutputStream(writer);
        System.setIn(new ByteArrayInputStream("0\n10".getBytes()));
        MainRunThread mainRunThread = new MainRunThread(main);

        mainRunThread.start();

        this.slowdownForWindows();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File("output.txt")));
            String line = reader.readLine();

            boolean foundRunName = false;
            while(line != null) {

                if(line.contains("Run:")) {
                    foundRunName = true;
                }

                line = reader.readLine();
            }
            Assertions.assertTrue(foundRunName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void outputsExpectedRunCountToTheFile() {
        new File("output.txt").delete();
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        ConsoleWriter writer = new ConsoleWriter(new PrintStream(outSpy));

        Main main = new Main();
        main.setOutputStream(writer);
        System.setIn(new ByteArrayInputStream("1\n10".getBytes()));
        MainRunThread mainRunThread = new MainRunThread(main);

        mainRunThread.start();

        this.slowdownForWindows();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File("output.txt")));
            String line = reader.readLine();

            int foundRunNameCount = 0;
            while(line != null) {

                if(line.contains("Run:")) {
                    foundRunNameCount++;
                }

                line = reader.readLine();
            }
            Assertions.assertSame(2, foundRunNameCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void makeSureThatNumbersInRunAreGoingUp() {
        new File("output.txt").delete();
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        ConsoleWriter writer = new ConsoleWriter(new PrintStream(outSpy));

        Main main = new Main();
        main.setOutputStream(writer);
        System.setIn(new ByteArrayInputStream("1\n10".getBytes()));
        MainRunThread mainRunThread = new MainRunThread(main);

        mainRunThread.start();

        this.slowdownForWindows();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File("output.txt")));
            String line = reader.readLine();

            boolean inRun = false;
            int previousNumber = 0;
            while(line != null) {

                if(line.contains("Run:")) {
                    inRun = true;
                }else if(line.equals("")) {
                    inRun = false;
                    previousNumber = 0;
                }else if(inRun) {
                    int currentNumber = 0;
                    try {
                        currentNumber = Integer.parseInt(line);
                    }catch(NumberFormatException e) {
                        // ignored
                    }
                    Assertions.assertTrue(currentNumber > previousNumber || currentNumber == previousNumber);
                    previousNumber = currentNumber;
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void ifGivenSameInputTwiceReturnsTheSameResult() {
        new File("output.txt").delete();
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        ConsoleWriter writer = new ConsoleWriter(new PrintStream(outSpy));

        Main main = new Main();
        main.setOutputStream(writer);
        System.setIn(new ByteArrayInputStream("1\n10".getBytes()));
        MainRunThread mainRunThread = new MainRunThread(main);

        mainRunThread.start();

        this.slowdownForWindows();
        BufferedReader reader;

        ArrayList<String> linesFirstTime = new ArrayList<String>();

        try {
            reader = new BufferedReader(new FileReader(new File("output.txt")));
            String line = reader.readLine();

            while(line != null) {

                line = reader.readLine();
                linesFirstTime.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        new File("output.txt").delete();
        outSpy = new ByteArrayOutputStream();
        writer = new ConsoleWriter(new PrintStream(outSpy));

        main = new Main();
        main.setOutputStream(writer);
        System.setIn(new ByteArrayInputStream("1\n10".getBytes()));
        mainRunThread = new MainRunThread(main);

        mainRunThread.start();

        this.slowdownForWindows();

        ArrayList<String> linesSecondTime = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(new File("output.txt")));
            String line = reader.readLine();

            while(line != null) {

                line = reader.readLine();
                linesSecondTime.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertArrayEquals(linesFirstTime.toArray(), linesSecondTime.toArray());
    }

    private void slowdownForWindows() {
        try {
            Thread.sleep(50);
            // DIRTY HACK
            // windows sometimes takes a bit longer to see a file being there
            // using this try / catch makes it 100% predictable
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
