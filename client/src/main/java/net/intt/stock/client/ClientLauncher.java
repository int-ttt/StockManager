package net.intt.stock.client;

import net.intt.util.LogManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class ClientLauncher {
    protected static boolean quit = false;

    public static final LogManager log = new LogManager("StockManager");
    public static t2Thread t2;
    public static File PATH;
    public static String id;

    public static void main(String[] args) {
        init();
        try {
            while (!quit) {
                Scanner scn = new Scanner(System.in);
                System.out.print("Server IP: ");
                String arg = scn.next();
                Socket socket;
                try {
                    socket = new Socket(arg, 56077);
                } catch (IOException e) {
                    log.error("서버가 열려있지 않습니다");
                    continue;
                }
                Login login = new Login();
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(Files.readString(Paths.get(PATH.getPath())));
                log.debug(json.get("lastjoin"));
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line;
                if (!json.get("name").equals("")) login.autologin(socket, json);
                else while (!(line = br.readLine()).equals("pass")) {

                }
                //login();
                t1Thread t1 = new t1Thread(socket, arg, log);
                t2 = new t2Thread(socket, t1, arg);
                System.out.print(arg + ":" + Inet4Address.getLocalHost() + "$ ");
                t1.start();
                t2.start();
                t2.join();

                t1.stop();
                socket.close();
            }
        } catch (ParseException | IOException | InterruptedException e) {
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

    /*static void login() {
        if (!json.get("name").equals("")) {
            log.debug("DDFD");
            int[] array = Arrays.stream(json.get("lastjoin").toString().split("-")).mapToInt(Integer::parseInt).toArray();
            if (array[1] + 1 > Integer.parseInt(new SimpleDateFormat("MM").format(new Date()))) {
                if (!(array[0] > Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())))) {
                    ID = json.get("name").toString();
                    password = json.get("password").toString();
                    pw.println("^login " + ID + password);
                    json.put("lastjoin", new SimpleDateFormat("yyyy-MM").format(new Date()));
                    fw.write(json.toJSONString());
                    fw.flush();
                    fw.close();
                } else login = true;
            } else login = true;
        } else login = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        if (login) {
            while (!((line = br.readLine()).equals("success"))) {
                log.error("dd");

                BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("login$");
                String[] ag = sin.readLine().split("\\s");
                if (ag.length == 0) log.error("[login|signin] [ID] [PW]");
                else if (ag[0].equals("login")) {
                    ID = ag[1];
                    password = new DigestUtils("SHA3-512").digestAsHex(ag[2]);
                    pw.println("^login " + ID + " " + password);
                } else if (ag[0].equals("signin")) {
                    if (ag[1].contains(",")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains(".")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("/")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains(":")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains(";")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("\"")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("'")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("{")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("}")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("[")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("]")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("|")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("\\")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("+")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("=")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("<")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains(">")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("?")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("*")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("(")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains(")")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("!")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("@")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("#")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("$")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("%")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("^")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("&")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("`")) {
                        log.error("this character is cannot use");
                    } else if (ag[1].contains("~")) {
                        log.error("this character is cannot use");
                    } else if (ag[2].length() < 8) {
                        log.error("password is short");
                    } else {
                        pw.println("^signin " + ID + " " + password);
                    }
                }
            }
            log.info(line);
            json.put("name", ID);
            json.put("password", password);
            json.put("lastjoin", new SimpleDateFormat("yyyy-MM").format(new Date()));
            fw.write(json.toJSONString());
            fw.flush();
            fw.close();
        }
    }*/
}