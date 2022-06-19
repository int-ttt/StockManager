package net.intt.stock.server.threads;

import net.intt.stock.server.ServerLauncher;
import org.intt.util.LogManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;

public class ServerThread implements Runnable {

    private final Socket socket;

    private final LogManager log = ServerLauncher.log;

    public static boolean login = false;

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

//                if (!login) {
//                    Authentication auth = new Authentication();
//                    switch (args[0]) {
//                        case "^login" -> {
//                            int _return = auth.login(
//                                    args[1], DigestUtils.sha1Hex(args[2]));
//
//                            pw.println(_return);
//                            if (_return == 0) {
//                                    ResultSet rs = SQLite.getInstance().
//                                            state.executeQuery("");
//
//                                    playerData = rs.getString("data");
//
//                            }
//                        }
//                        case "^signup" -> auth.createAccount(args[1], DigestUtils.sha1Hex(args[2]));
//                    }
//                } else {
                JSONObject json = (JSONObject) new JSONParser().parse(playerData);
                switch (args[0]) {
                    case "moo" -> {
                        pw.println("                 (__)=                 (oo)=           /------\\/=          / |    ||=         *  /\\---/\\=            ~~   ~~");
                        System.out.println("                 (__)=                 (oo)=           /------\\/=          / |    ||=         *  /\\---/\\=            ~~   ~~");
                    }

                    default -> {
                        pw.println(arg);
                        System.out.println(arg);
                    }
                }
            }
            pw.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
