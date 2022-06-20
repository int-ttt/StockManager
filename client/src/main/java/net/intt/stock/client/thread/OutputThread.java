package net.intt.stock.client.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputThread extends AbstractThreads {

    private final BufferedReader br;
    private final PrintWriter pw;

    public OutputThread(BufferedReader br, PrintWriter pw) {
        this.br = br;

        this.pw = pw;
    }

    @Override
    public void loop() {
        String arg;
        try {
            while ((arg = br.readLine()) != null) {
                System.out.println("\r" + arg);
                System.out.print("\r" + InputThread.getPrefix()+ "> ");
            }
            stop = false;
        } catch (IOException e) {
            stop = false;
        }
    }

    public boolean isStop() {
        return this.stop;
    }
}
