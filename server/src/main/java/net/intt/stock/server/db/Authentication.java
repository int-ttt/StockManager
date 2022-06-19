package net.intt.stock.server.db;

import net.intt.stock.server.ServerLauncher;
import net.intt.stock.server.annotation.Test;
import net.intt.stock.server.util.References;
import org.apache.commons.codec.digest.DigestUtils;
import org.intt.util.LogManager;
import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class Authentication {

    private final LogManager log = ServerLauncher.log;

    private final Statement state = SQLite.getInstance().state;

    public int login(String id, String pwd) {
        if (!(id.isEmpty() || pwd.isEmpty())) {
            //if (SQLite.ID(id)) {
                if (SQLite.Pwd(DigestUtils.sha3_512Hex(pwd))) {
                    return 0;
                } else return 2;
            //} else return 1;
        } else return 3;
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

    @Test
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQLite.getInstance().DBInit();
        Authentication auth = new Authentication();

//        System.out.println(
//                auth.createAccount("idff", "dfffdfd"));

        System.out.println(
                auth.login("idff", "dfffdfd"));
    }
}
