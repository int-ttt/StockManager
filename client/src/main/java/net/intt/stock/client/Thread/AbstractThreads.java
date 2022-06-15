package net.intt.stock.client.Thread;

public abstract class AbstractThreads extends Thread {
    protected boolean stop = true;

    public abstract void run();

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
