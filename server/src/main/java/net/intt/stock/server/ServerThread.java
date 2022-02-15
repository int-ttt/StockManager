package net.intt.stock.server;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

class ServerThread extends Thread {
    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        MongoDatabase database = ServerLauncher.mongoClient.getDatabase("StockManager");
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String arg;
            while ((arg = br.readLine()) != null) {
                String[] args = arg.split("\\s");
                switch (args[0]) {
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
                    case "^signin":

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
            socket.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
