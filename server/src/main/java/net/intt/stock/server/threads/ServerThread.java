package net.intt.stock.server.threads;

import net.intt.stock.server.ServerLauncher;
import net.intt.stock.server.util.Util;
import net.intt.util.LogManager;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.sql.BatchUpdateException;

public class ServerThread implements Runnable {

    private final LogManager log = ServerLauncher.log;
    private final String id;
    private final String playerData;
    private final double money;
    private final PrintWriter pw;
    private final BufferedReader br;

    private static boolean quit = true;

    public ServerThread(@NotNull Socket socket, String id, String playerData, double money) throws IOException {
        this.id = id;
        this.playerData = playerData;
        this.money = money;
        this.pw = new PrintWriter(socket.getOutputStream(), true);
        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            log.info("new player join");
            String arg;
            while (quit && (arg = br.readLine()) != null) {
                String[] args = arg.split("\\s");
                switch (args[0]) {
                    case "moo" -> pw.println("                 (__)=                 (oo)=           /------\\/=          / |    ||=         *  /\\---/\\=            ~~   ~~");
                    case "finance" -> {

                    }
                    case "say" -> {
                        String str = arg.replace("say ", "");
                        Util.broadcast(str);
                    }
                }
            }
            pw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setQuit(boolean quit) {
        ServerThread.quit = quit;
    }
}
