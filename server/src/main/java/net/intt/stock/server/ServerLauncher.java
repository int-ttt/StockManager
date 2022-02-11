/**
 * @version 0.0.5
 * @auther int_t
 *
 * server file
 */

package net.intt.stock.server;

import net.intt.util.LogManager;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Objects;

public class ServerLauncher {

    static LogManager log = new LogManager("FireStockServer");

    static int port = 56077;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("socket : " + port + " open to server");

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
}