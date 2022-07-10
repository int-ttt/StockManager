package net.intt.stock.server.threads;

import net.intt.stock.server.ServerLauncher;
import net.intt.stock.server.db.Authentication;
import net.intt.stock.server.db.SQLite;
import net.intt.util.LogManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class LoginThread implements Runnable {
    private boolean stop = true;
    private final BufferedReader br;
    private final PrintWriter pw;
    private final Socket socket;
    private double money;

    public LoginThread(@NotNull Socket socket) throws IOException {
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(socket.getOutputStream(), true);
        this.socket = socket;
    }

    private String playerData;
    private String ID;

    @Override
    public void run() {
        try {
            while (stop) {
                String arg = br.readLine();
                String[] args;
                try {
                    args = arg.split("\\s");
                } catch (Exception e) {
                    pw.println("login -2");
                    return;
                }
                Authentication auth = new Authentication();

                switch (args[0]) {
                    case "^login" -> {
                        ServerLauncher.log.info(arg);
                        int _return = auth.login(
                                args[1], DigestUtils.sha1Hex(args[2]));
                        pw.println("login " + _return);
                        ServerLauncher.log.info(_return);
                        if (_return == 0) {
                            ID = args[1];
                            String[] str = SQLite.getInstance().getPlayerData(args[1]).split("\\s");
                            if (Boolean.parseBoolean(str[0])) {
                                playerData = str[1];
                                money = Double.parseDouble(str[2]);
                            }
                            new Thread(new ServerThread(socket, getID(), getPlayerData(), getMoney())).start();
                        }
                    }
                    case "^signup" ->  {
                        int _return = auth.createAccount(args[1], DigestUtils.sha1Hex(args[2]));
                        pw.println("login " + _return);
                        ServerLauncher.log.info(arg);
                        ServerLauncher.log.info(_return);
                        if (_return == 0) {
                            this.setStop(false);
                            new Thread(new ServerThread(socket, getID(), getPlayerData(), getMoney())).start();
                        }
                    }
                }
            }
        } catch (SQLException | IOException e) {
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public String getPlayerData() {
        return playerData;
    }

    public String getID() {
        return ID;
    }

    public double getMoney() {
        return money;
    }
}
