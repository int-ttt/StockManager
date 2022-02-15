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
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLauncher {

    static LogManager log = new LogManager("FireStockServer");
    public static MongoClient mongoClient;
    static int port = 56077;

    public static void main(String[] args) {
        mongoClient = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb+srv://int_t:250099@cluster0.m0z0j.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"))
                .build());
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            log.info("socket : " + port + " open to server");

            while(true) {
                Socket socket = serverSocket.accept();

                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}