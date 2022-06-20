package net.intt.stock.server.threads;

import net.intt.stock.server.db.Authentication;
import net.intt.stock.server.db.SQLite;
import org.apache.commons.codec.digest.DigestUtils;

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

    public LoginThread(Socket socket) throws IOException {
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(socket.getOutputStream(), true);
        this.socket = socket;
    }

    private String playerData;


    @Override
    public void run() {
        try {
            while (stop) {
                int return_;
                System.out.print(">");
                String arg = br.readLine();
                System.out.println(arg);
                String[] args;
                try {
                    args = arg.split("\\s");
                } catch (Exception e) {
                    return_ = -2;
                    pw.println("-2");
                    return;
                }
                Authentication auth = new Authentication();

                switch (args[0]) {
                    case "^login" -> {
                        int _return = auth.login(
                                args[1], DigestUtils.sha1Hex(args[2]));
                        pw.println(_return);
                        System.out.println(_return);
                        if (_return == 0) {
                            return_ = _return;
                        }
                    }
                    case "^signup" ->  {
                        int _return = auth.createAccount(args[1], DigestUtils.sha1Hex(args[2]));
                        System.out.println(_return);
                        pw.println(_return);
                        if (_return == 0) {
                            this.setStop(false);
                            Thread thread1 = new Thread(new ServerThread(socket));
                            Thread thread2 = new Thread(new ChatThread());
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQLite.getInstance().DBInit();
//        Thread t = new Thread(new LoginThread());

//        t.start();
        Authentication auth = new Authentication();
//        System.out.println(auth.createAccount("id", "pwdpwd"));

        System.out.println(auth.login("id", "pwdpwd"));
    }

}
