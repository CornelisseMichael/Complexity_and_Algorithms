package test;

import java.io.PrintStream;

public class ConsoleWriter {
    private final PrintStream out;

    public ConsoleWriter(PrintStream out) {
        this.out = out;
    }
    public void print(String string) {
        this.out.print(string);
    }

    public void println(String string) {
        this.print(string);
        this.print("\n");
    }
}
