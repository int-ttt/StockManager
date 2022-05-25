package net.intt.stock.client.Thread;

public class InputThread implements Runnable {
    private static boolean quit = false;
    @Override
    public void run() {

    }

    public static boolean isQuit() {
        return quit;
    }
}
