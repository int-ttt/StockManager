package net.intt.stock.client.Thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectThread implements Runnable {

    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;

    public ConnectThread(Socket socket) throws IOException {
        this.socket = socket;
        this.br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        this.pw = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void run() {

    }
}
