package net.intt.stock.client;

import net.intt.util.LogManager;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ClientLauncher {
    protected static boolean quit = false;

    public static final LogManager log = new LogManager("StockManager");
    public static ReadThread t2;
    public static File PATH;
    public static String ID;
    public static boolean login = true;

    public static void main(String[] args) {
        init();
        try {
            while (!quit) {
                System.out.print("Server IP: ");
                BufferedReader cbr = new BufferedReader(new InputStreamReader(System.in));

                String arg = cbr.readLine();
                Socket socket;

                try {
                    socket = new Socket(arg, 56077);
                } catch (IOException e) {
                    log.error("서버가 열려있지 않습니다");
                    continue;
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Authentication login = new Authentication(socket);
                String line;
                boolean _break = false;
                System.out.print("login$ ");
                while (true) {
                    String ag = cbr.readLine();

                    if (ag.isEmpty()) {
                        log.error("[login|signin] [ID] [Password]");
                        continue;
                    }

                    String[] ags = ag.split("\\s");
                    int _return = -1;

                    if (ags[0].equals("help") || ags[0].equals("?")) {
                        log.info("[login|signin] [ID] [Password]");
                        continue;
                    }

                    if (ags.length < 3) {
                        log.error("[login|signin] [ID] [Password]");
                        continue;
                    }

                    switch (ags[0]) {

                        case "login" -> _return = login.login(ags[1], ags[2]);
                        case "signin" -> _return = login.signin(ags[1], ags[2]);
                    }
                    System.out.println(_return);
                    switch (_return) {
                        case -1 -> log.error("[login|signin] [ID] [Password]");
                        case 0 -> _break = true;
                    }
                    if (_break) break;
                }
                InputThread t1 = new InputThread(socket, log);
                t2 = new ReadThread(socket, t1);
                System.out.print(ID + ":" + Inet4Address.getLocalHost() + "$ ");
                t1.start();
                t2.start();
                t2.join();

                t1.stop();
                socket.close();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void init() {
        boolean b = false;
        try {
            if (OS.isMac()) {
                File file = new File(System.getProperty("user.home") + "/Library/Application Support/StockManager");
                if (file.mkdir()) {
                }
                File f = new File(file + "/userdata.json");
                if (!f.exists()) {
                    f.createNewFile();
                    b = true;
                }
                PATH = f;
            } else if (OS.isWin()) {
                String path = "C:\\" + System.getProperty("user.home") + "\\appdata\\roaming\\StockManager";
                File file = new File(path);
                if (file.mkdir()) {
                }
                File f = new File(file + "\\.userdata.json");
                if (!f.exists()) {
                    f.createNewFile();
                    b = true;
                }
                PATH = f;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (b) {
            JSONObject json = new JSONObject();
            json.put("name", "");
            json.put("password", "");
            json.put("lastjoin", new SimpleDateFormat("yyyy-MM").format(new Date()));

            try (FileWriter file = new FileWriter(PATH)) {
                file.write(json.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}