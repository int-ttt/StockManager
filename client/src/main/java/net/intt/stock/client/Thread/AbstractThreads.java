package net.intt.stock.client.Thread;

import net.intt.stock.client.interfaces.IThread;

public abstract class AbstractThreads extends Thread implements IThread {
    protected boolean stop = true;

    @Override
    public void run() {
        while (stop) {
            loop();
        }

    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public synchronized void start() {
        super.start();
    }
}
