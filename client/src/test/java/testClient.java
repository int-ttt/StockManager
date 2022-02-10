import net.intt.util.LogManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class testClient {

    static LogManager log = new LogManager("FireStock");

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 56077);
        boolean brea = false;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        BufferedReader SVin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        double money = 10000;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String p = null;

        do {
            try {
                label:
                while (true) {
                    if (!socket.isClosed()) {
                        try {
                            socket.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("FireStock:" + p + "$ ");
                    String[] arg = in.readLine().split("\\s");
                    log.debug(arg.length);
                    switch (arg[0]) {
                        case "exit":
                            brea = true;
                            break label;
                        case "help":
                            if (arg.length <= 1) {
                                log.info(0);
                            } else
                                switch (arg[1]) {
                                    case "1":
                                        log.info(1);
                                        break;
                                }
                            break;
                        case "finance":
                            if (arg.length == 2) {
                                if (arg[1].isEmpty())
                                    log.error("finance [subject code] [buy|sell] [count] \nex) finance btc-usd buy 0.1");
                                else {
                                    var e = Finance(arg[1]);
                                    if (e instanceof String) log.error("finance: subject code not found");
                                    else log.info(arg[1] + ": " + e);
                                }
                            } else if (arg.length >= 4) {
                                if (!arg[3].isEmpty()) {
                                    double d;
                                    var e = Finance(arg[1]);
                                    double de = 0;
                                    try {
                                        d = Double.parseDouble(arg[3]);
                                        de = (double) e * d;
                                    } catch (NumberFormatException exception) {
                                        log.error("count is Number");
                                        continue;
                                    }
                                    if (e instanceof String) log.error("finance: subject code not found");
                                    if (arg[2].equals("buy")) {
                                        Objects.requireNonNull(pw).println("finance buy" + arg[2] + " " + arg[3]);
                                        log.info(SVin.readLine());
                                    } else if (arg[2].equals("sell")) {
                                        Objects.requireNonNull(pw).println("finance sell" + arg[2] + " " + arg[3]);
                                        log.info(SVin.readLine());
                                    } else {
                                        log.error("error");
                                    }
                                } else log.error("finance [subject code] [buy|sell] [count]");
                            } else log.error("finance [subject code] [buy|sell] [count]");
                            break;
                        case "money":
                            log.info("your money: " + money);
                            break;
                    }
                    //TODO finance gui and sell
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    socket.close();
                    SVin.close();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (!brea);
    }
    public static Object Finance(String subject) {
        String URL = "https://finance.yahoo.com/quote/" + subject + "?p=" + subject + "&.tsrc=fin-srch";
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elem = doc.select("div[class=\"D(ib) Mend(20px)\"]");
        String eliment = null;

        for (Element e: elem.select("fin-streamer[class=\"Fw(b) Fz(36px) Mb(-4px) D(ib)\"]")) {
            eliment = String.valueOf(e);
        }

        if (eliment == null) {
            return "1";
        }

        var idx1 = eliment.indexOf("\n<");
        String el1 = eliment.substring(0, idx1);
        var id = el1.indexOf("\">");
        String el2 = el1.substring(id+1);
        el2 = el2.replace(">", "");
        el2 = el2.replace("\n", "");
        el2 = el2.replace(" ", "");

        var el3 = Double.parseDouble(el2.replace(",", ""));

        return el3;
    }
    public static void Fin(boolean type, String[] args) {
        boolean tp = false;
        if (type) {

        } else {

        }
    }
}
