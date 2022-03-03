/**
 * @version 0.0.5
 * @auther int_t
 *
 * server file
 */

package net.intt.stock.server;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import net.intt.util.LogManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerLauncher {

    public static final List<PrintWriter> broadcast = new ArrayList<>();
    public static LogManager log = new LogManager("StockServer");
    public static MongoClient mongoClient;
    private static final int port = 56077;
    public static boolean quit = false;

    public static void main(String[] args) {
        mongoClient = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb+srv://int_t:250099@cluster0.m0z0j.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"))
                .build());
        try {
            ServerSocket serverSocket = new ServerSocket(56077);
            log.info("socket : " + port + " open to server");



            System.err.flush();

            Thread chat = new Thread(new ChatThread());
            chat.start();

            while(true) {
                Socket socket = serverSocket.accept();

                broadcast.add(new PrintWriter(socket.getOutputStream()));

                Thread thread = new Thread(new ServerThread(socket));
                thread.start();
                if (quit) break;
            }
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}