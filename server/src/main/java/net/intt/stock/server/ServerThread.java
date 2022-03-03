package net.intt.stock.server;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import net.intt.util.LogManager;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ServerThread implements Runnable {
    private final Socket socket;
    public static boolean login = false;
    private final LogManager log = ServerLauncher.log;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        MongoDatabase database = ServerLauncher.mongoClient.getDatabase("StockManager");
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String[] args;
            String arg;
            while ((arg = br.readLine()) != null) {
                args = arg.split("\\s");

                if (!login) {
                    Authentication auth = new Authentication(socket, database);
                    switch (args[0]) {
                        case "^login":
                            log.info(auth.login(args[1], args[2]));
                            break;
                        case "^signup":

                            break;
                    }
                } else switch (args[0]) {
                    case "^login":
                        MongoCollection<Document> collection = database.getCollection("Users");
                        Document document = collection.find(Filters.eq("name", args[1])).first();
                        if (document == null) {
                            pw.println("id is not found");
                            continue;
                        } else if (!document.get("password").toString().equals(args[2])) {
                            pw.println("password is different");
                            continue;
                        } else {
                            pw.println(document.get("password").toString());
                        }
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
