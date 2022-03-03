package net.intt.stock.client;

import net.intt.util.LogManager;

import java.io.*;
import java.net.Socket;

public class Authentication {

    private final LogManager log = ClientLauncher.log;
    private final File PATH = ClientLauncher.PATH;
    private final PrintWriter pw;
    private final BufferedReader br;

    public Authentication(Socket socket) throws IOException {
        pw = new PrintWriter(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public int login(String id, String pwd) throws IOException {
        pw.println("^login " + id + " " + pwd);
        return br.read();
    }

    public int signin(String id, String pwd) {

        /*BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
        }*/
        return 0;
    }
}


//        try {
//            FileWriter fw = new FileWriter(PATH);
//            JSONObject json = new JSONObject();
//            json.put("lastjoin", new SimpleDateFormat("yyyy-MM").format(new Date()));
//            json.put("name", id);
//            json.put("password", pwd);
//            fw.write(json.toJSONString());
//            fw.flush();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }