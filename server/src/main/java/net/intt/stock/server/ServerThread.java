package net.intt.stock.server;

import net.intt.stock.server.db.Authentication;
import net.intt.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
    private final Socket socket;
    public static boolean login = false;
    private final LogManager log = ServerLauncher.log;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String[] args;
            String arg;
            while ((arg = br.readLine()) != null) {
                args = arg.split("\\s");

                if (!login) {
                    Authentication auth = new Authentication(/*socket*/);
                    switch (args[0]) {
                        case "^login":
                            log.info(auth.login(args[1], args[2]));
                            break;
                        case "^signup":

                            break;
                    }
                } else switch (args[0]) {
                    case "^login":

                        break;
                    case "^signup":

                        break;
                    case "moo":
                        pw.println("                 (__)=                 (oo)=           /------\\/=          / |    ||=         *  /\\---/\\=            ~~   ~~");
                        System.out.println("                 (__)=                 (oo)=           /------\\/=          / |    ||=         *  /\\---/\\=            ~~   ~~");
                        break;
                    default:
                        pw.println(arg);
                        System.out.println(arg);
                        break;
                }
            }
            pw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
