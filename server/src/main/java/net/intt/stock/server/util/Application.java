package net.intt.stock.server.util;

import net.intt.stock.server.db.Authentication;
import net.intt.stock.server.db.SQLite;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application {

    private static String playerData;

    public static int login(String[] args, PrintWriter pw) throws SQLException {
        Authentication auth = new Authentication();
        switch (args[0]) {
            case "^login" -> {
                int _return = auth.login(args[1], DigestUtils.sha1Hex(args[2]));
                pw.println(_return);
                return 0;
            }
            case "^signup" -> auth.createAccount(args[1], DigestUtils.sha1Hex(args[2]));
        }
        return 0;
    }

    public static void setPlayerData(String playerData) {
        Application.playerData = playerData;
    }
}
