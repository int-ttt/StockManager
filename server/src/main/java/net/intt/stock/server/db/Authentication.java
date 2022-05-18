package net.intt.stock.server.db;

import net.intt.stock.server.ServerLauncher;
import net.intt.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public final class Authentication {

    private final LogManager log = ServerLauncher.log;
    private final BufferedReader br;

    public Authentication(Socket socket) throws IOException {
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public int login(String id, String pwd) {
        return 0;
    }

    public int createAccount(String id, String pwd) {
        if (!(id.isEmpty() || pwd.isEmpty())) {
            try {

            } catch (Exception e) {

            }
        }
        return 0;
    }
}
