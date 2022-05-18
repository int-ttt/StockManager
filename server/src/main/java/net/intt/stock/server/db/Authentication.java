package net.intt.stock.server.db;

import net.intt.stock.server.ServerLauncher;
import net.intt.stock.server.util.Exception.NoIDException;
import net.intt.util.LogManager;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Statement;

public final class Authentication {

    private final LogManager log = ServerLauncher.log;
//    private final BufferedReader br;

    private final Statement state = SQLite.getInstance().state;

    public Authentication(/*Socket socket*/) {
//        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }



    public int login(String id, String pwd) {
        return 0;
    }

    public int createAccount(@NotNull String id, @NotNull String pwd) {
        if (!(id.isEmpty() || pwd.isEmpty())) {
            if (!SQLite.ID(id)) {
                if (pwd.length() > 6) {
                    try {
                        state.executeUpdate("INSERT INTO Users(id,pwd,data,money) VALUES ('" + id +"','" + pwd + "','{}','0')");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else return 3;
            } else return 2;
        } else return 1;
        return 0;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQLite.getInstance().DBInit();
        Authentication auth = new Authentication();
        System.out.println( auth.createAccount("id", "pwddfdfdf"));
    }

}
