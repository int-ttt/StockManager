package net.intt.stock.client.thread;

import net.intt.stock.client.ClientLauncher;
import net.intt.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputThread extends AbstractThreads {

    private LogManager log = ClientLauncher.log;
    private final BufferedReader br;

    public OutputThread(BufferedReader br, PrintWriter pw) {
        this.br = br;
    }

    @Override
    public void loop() {
        String arg;
        try {
            while ((arg = br.readLine()) != null) {
                log.info(arg);
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
