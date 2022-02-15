package net.intt.stock.client;

import net.intt.util.LogManager;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Login {

    LogManager log = ClientLauncher.log;
    File PATH = ClientLauncher.PATH;

    public void autologin(Socket socket, JSONObject json) {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            if (!json.get("password").toString().isEmpty()) {
                Date format1 = new SimpleDateFormat("yyyy-MM-dd").parse(new Date().toString());
                Date format2 = new SimpleDateFormat("yyyy-MM-dd").parse(new Date().toString());

                long day = (format1.getTime() - format2.getTime()) / ((24*60*60)*30);
                if (day <= 30) {
                    pw.println("^login " + json.get("name").toString() + " " + json.get("password").toString());
                    FileWriter fw = new FileWriter(PATH);
                    json.put("lastjoin", new SimpleDateFormat("yyyy-MM").format(new Date()));
                    fw.write(json.toJSONString());
                    fw.flush();
                    fw.close();
                }
            } else log.error("dfdffdfdf");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void login(Socket socket, String id, String password) {

    }

    public void signin(Socket socket, String id, String password) {

    }
}
