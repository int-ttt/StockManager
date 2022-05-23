package java.net.intt.stock.client;

import net.intt.util.LogManager;

import java.io.*;
import java.net.Socket;

import static java.net.intt.stock.client.ClientLauncher.ID;
import static java.net.intt.stock.client.ClientLauncher.login;

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
        try {
            String password = pwd;
            String line;
            if (login) {
                while (!((line = br.readLine()).equals("success"))) {
                    log.error("dd");

                    BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("login$");
                    String[] ag = sin.readLine().split("\\s");
                    if (ag.length == 0) log.error("[login|signin] [ID] [PW]");
                    else if (ag[0].equals("login")) {
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
                        } else if (ag[2].length() < 6) {
                            log.error("password is short");
                        } else {
                            pw.println("^signin " + ID + " " + password);
                        }
                    }
                }
                log.info(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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