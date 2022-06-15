package net.intt.stock.client.Thread;

public class WaitThread extends AbstractThreads {

    protected AbstractThreads thread;

    public WaitThread(AbstractThreads thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            System.in.read();
        } catch (Exception e) {
        }
        thread.setStop(true);
    }
}
