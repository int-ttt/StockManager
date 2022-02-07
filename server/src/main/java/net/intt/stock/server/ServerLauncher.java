package net.intt.stock.server;

import net.intt.util.LogManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class ServerLauncher {

    static LogManager log = new LogManager("FireStockServer");

    public static void main(String[] args) {
        Socket socket;
        ServerSocket server_socket = null;
        BufferedReader CLin;
        BufferedReader in;
        PrintWriter out;

        try {
            server_socket = new ServerSocket(56077);

        } catch (IOException e) {
            log.error("해당 포트가 열려있습니다.");
        }
        log.info("서버 오픈!!");

        try {
            socket = Objects.requireNonNull(server_socket).accept();

            CLin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String str = CLin.readLine();
                String inStr = in.readLine();

                log.info("Client로 부터 온 메세지 : " + str);

                if (inStr.equals("exit")) {
                    break;
                }

                out.write(str);
                out.flush();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
