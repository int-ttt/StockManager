package net.intt.stock.server;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import net.intt.util.LogManager;
import org.bson.Document;

import java.io.*;
import java.net.Socket;

public final class Authentication {

    private final LogManager log = ServerLauncher.log;
    private final BufferedReader br;
    private final MongoDatabase database;

    public Authentication(Socket socket, MongoDatabase database) throws IOException {
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.database = database;
    }

    public int login(String id, String pwd) {
        MongoCollection<Document> collection = database.getCollection("Users");
        Document document = collection.find(Filters.eq("name", id)).first();

        FindIterable<Document> it = collection.find(Filters.eq("name", id));
        if (!document.isEmpty() && document.get("pwd").equals(pwd)) {
            ServerThread.login = true;
            return 0;
        } else if (!document.get("pwd").equals(pwd)) {
            return 1;
        } else if (document.isEmpty()) {
            return 2;
        }
        return -1;
    }

    public int signin() {
        return 0;
    }
}
