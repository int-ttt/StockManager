package net.intt.stock.client;

import net.intt.stock.client.Util.ClientSetup;
import net.intt.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientLauncher {

    public static final LogManager log = new LogManager("StockClient");

    public static void main(String[] gs) {
        Socket socket = new Socket();

        BufferedReader cbr = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            while (true) {
                try {
                    ClientSetup.ServerJoin(56077, cbr);
                } catch (IOException e) {
                    log.error("서버가 열려있지 않습니다");
                    continue;
                }
                ClientSetup.MainClient client = new ClientSetup.MainClient(socket);
                while (true) {
                    if (client.login() == 0) break;
                    else if () {
                        
                    }
                }
                if (client.client() == 0) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
