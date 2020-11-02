package test;

import main.Main;

public class MainRunThread extends Thread {
    public Main main;

    public MainRunThread(Main main) {
        this.main = main;
    }
    @Override
    public void run() {
        main.run();
    }
}
