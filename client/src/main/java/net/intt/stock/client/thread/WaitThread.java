package net.intt.stock.client.thread;

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
        thread.setStop(false);
    }

    @Override
    public void loop() {

    }
}
