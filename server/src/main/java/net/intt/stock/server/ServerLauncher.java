/**
 * @version 0.0.5
 * @auther int_t
 *
 * server file
 */

package net.intt.stock.server;

import net.intt.util.LogManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

public class ServerLauncher {

    static LogManager log = new LogManager("FireStockServer");

    public static void main(String[] args) {
        setUp();
        System.out.println("121231231");
        try {
            ServerSocket serverSocket = new ServerSocket(56077);
            System.out.println("socket : " + 56077 + " open to server");

            while(true) {
                Socket socket = serverSocket.accept();

                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println("");

                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUp() {
        String PATH = "";
        try {
            String[] path = new File(new File(ServerLauncher.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath()).toString().split("/");
            for (int i = 1; i < path.length - 1; i++) {
                PATH = PATH + "/" + path[i];
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File file = new File(PATH);
        if (file.mkdirs()) {
        }
        file = new File(PATH + "/config.yml");
        try {
            if (file.createNewFile()) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("finished init");
    }
}