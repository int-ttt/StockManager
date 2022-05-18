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

    public boolean ID(String id) throws SQLException {
        ResultSet rs = state.executeQuery("SELECT id FROM Users");
        List<String> idList = new ArrayList<>();
        while (rs.next()) {
            idList.add(rs.getString("id"));
        }

        boolean bool = false;

        for (String str : idList) {
            if (id.equals(str)) {
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

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        SQLite.getInstance().DBInit();
//        Statement state = SQLite.getInstance().state;
//        System.out.println( SQLite.getInstance().ID("abcdefgf"));
//        ResultSet rs = state.executeQuery("SELECT id FROM Users");
//        ArrayList<String> stringArrayList = new ArrayList<>();
//        while (rs.next()) {
//            stringArrayList.add(rs.getString("id"));
//        }
//        System.out.println(stringArrayList);
//    }
}
