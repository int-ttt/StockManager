package net.intt.stock.server.db;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class SQLite {

    public Connection con;
    public Statement state;

    public void DBInit() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");

        File file = new File("sql.db");

        con = DriverManager.getConnection("jdbc:sqlite:sql.db");

        state = con.createStatement();

//        int t = stat.executeUpdate("INSERT INTO Users(id,pwd,data,money) VALUES ('a','b','{krw}','0')");
//        ResultSet rs = state.executeQuery("SELECT * FROM Users");
//
//        System.out.println(rs.getString("id"));
    }

    public static boolean ID(String id) {
        ResultSet rs;
        try {
            rs = getInstance().state.executeQuery("SELECT id FROM Users");
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getString("id"));
            }
            for (String str : list) {
                if (str.equals(id)) return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean Pwd(String pwd) {
        ResultSet rs;
        List<String> pwdList;
        try {
            rs = getInstance().state.executeQuery("SELECT pwd FROM Users");
            pwdList = new ArrayList<>();
            while (rs.next()) {
                pwdList.add(rs.getString("pwd"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean bool = false;

        for (String str : pwdList) {
            if (pwd.equals(str)) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    private static SQLite instance;

    public static SQLite getInstance() {
        if (instance == null) {
            instance = new SQLite();
        }
        return instance;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQLite.getInstance().DBInit();
        System.out.println(ID("ab"));
    }
}
