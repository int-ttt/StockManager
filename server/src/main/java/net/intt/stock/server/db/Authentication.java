package net.intt.stock.server.db;

import net.intt.stock.server.ServerLauncher;
import net.intt.util.LogManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class Authentication {

    private final LogManager log = ServerLauncher.log;

    private final Statement state = SQLite.getInstance().state;

    //private final PrintWriter pw;

    public Authentication(/*PrintWriter pw*/) {
        //this.pw = pw;
    }

    public int login(String id, String pwd) {
        if (!(id.isEmpty() || pwd.isEmpty())) {
            if (SQLite.ID(id)) {
                if (SQLite.Pwd(DigestUtils.sha3_512Hex(pwd))) {
                    return 0;
                } else return 3;
            } else return 2;
        } else return 1;
    }

    public int createAccount(@NotNull String id, @NotNull String pwd) throws SQLException {
        if (!(id.isEmpty() ||
                pwd.isEmpty())) {

            if (!SQLite.ID(id)) {
                if (pwd.length() >= 6) {
                    state.executeUpdate(
                            "INSERT INTO Users(id,pwd,data,money) VALUES " +
                                    "('" + id +"','" + DigestUtils.sha3_512Hex(pwd) + "','{}','200')");
                    return 0;
                } else return 3;
            } else return 2;
        } else return 1;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQLite.getInstance().DBInit();
        Authentication auth = new Authentication();
        ResultSet rs = SQLite.getInstance().state.executeQuery(
                "SELECT * FROM Users WHERE id LIKE 'id'");

        List<String> list = new ArrayList<>();
        list.add(rs.getString("id"));
        list.add(rs.getString("pwd"));
        list.add(rs.getString("data"));
        list.add(rs.getString("money"));

        System.out.println(list);
        System.out.println(
                auth.createAccount("idff", "dfffdfd"));

        System.out.println(
                auth.login("idff", "dfffdfd"));
    }
}
