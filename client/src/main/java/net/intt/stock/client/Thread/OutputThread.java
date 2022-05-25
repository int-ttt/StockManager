package net.intt.stock.client.Thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class OutputThread implements Runnable {

    private BufferedReader br;
    private PrintWriter pw;

    public OutputThread(Socket socket) throws IOException {
        this.br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        this.pw = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void run() {

    }
}
