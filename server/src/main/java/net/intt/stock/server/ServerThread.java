package net.intt.stock.server;

import net.intt.stock.server.db.Authentication;
import net.intt.stock.server.db.SQLite;
import net.intt.util.LogManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServerThread implements Runnable {

    private final Socket socket;

    private final LogManager log = ServerLauncher.log;

    public static boolean login = false;

    public static BigDecimal money;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            log.info("new player join");
            PrintWriter pw = new PrintWriter(
                    socket.getOutputStream(), true);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String[] args;
            String arg;
            String playerData = "";
            while ((arg = br.readLine()) != null) {
                args = arg.split("\\s");

                if (!login) {
                    Authentication auth = new Authentication();
                    switch (args[0]) {
                        case "^login" -> {
                            int _return = auth.login(
                                    args[1], DigestUtils.sha1Hex(args[2]));

                            pw.println(_return);
                            if (_return == 0) {
                                    ResultSet rs = SQLite.getInstance().
                                            state.executeQuery("");

                                    playerData = rs.getString("data");

                            }
                        }
                        case "^signup" -> auth.createAccount(args[1], DigestUtils.sha1Hex(args[2]));
                    }
                } else {
                    JSONObject json = (JSONObject) new JSONParser().parse(playerData);
                    switch (args[0]) {
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
            }
            pw.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
