package net.intt.stock.client;

import net.intt.stock.client.Utils.Application;
import net.intt.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientLauncher {

    public static final LogManager log = new LogManager("StockClient");

    public static void main(String[] gs) {
        Socket socket;

        BufferedReader cbr = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            all: while (true) {
                try {
                    socket = Application.ServerJoin(56077, cbr);
                } catch (IOException e) {
                    log.error("서버가 열려있지 않습니다");
                    continue;
                }
                Application.MainClient client = new Application.MainClient(socket);
                while (true) {
                    int re;
                    if ((re = client.login()) == 0) break;
                    else if (re == 1) {
                        break all;
                    }
                }
                if (client.client() == 0) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
