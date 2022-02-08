package net.intt.stock.server;

import net.intt.util.LogManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class ServerLauncher {

    static LogManager log = new LogManager("FireStockServer");

    public static void main(String[] args) {
        ServerSocket server;
        Socket s;
        BufferedReader br;
        BufferedReader CLin;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw;
        try {
            server = new ServerSocket(56077);
            System.out.println("Server Ready........");
            s = server.accept();

            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream(), true);
            System.out.println(br.readLine() + " 님이 접속하셨습니다..");

            String line = null;
            while (true) {

                String str = in.readLine();
                pw.println(line);
                if (str.equals("stop")) {
                    break;
                }
            }
            br.close();
        }catch(Exception e) {
            System.out.println("Client와의 연결이 끊어졌습니다..");
        }
    }
}
