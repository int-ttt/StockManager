package net.intt.stock.client;

import net.intt.util.LogManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class InputThread extends Thread {

    private PrintWriter pw;
    private final Socket socket;
    private final LogManager log;

    public InputThread(Socket socket, LogManager log) throws IOException {
        this.socket = socket;
        this.log = log;
        try {
            pw = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        loop: while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String arg = null;
            try {
                arg = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] args = arg.split("\\s");

            if (args.length == 0) pw.println("");
            else if (args[0].indexOf("/") == 0) {
                args[0] = args[0].substring(1);
                switch (args[0]) {
                    case "exit":
                        break loop;
                    case "help":
                        if (args.length <= 1) {
                            log.info("0");
                        } else
                            switch (args[1]) {
                                case "1":
                                    log.info("1");
                                    break;
                            }
                        break;
                    case "finance":
                        if (args.length == 2) {
                            if (args[1].isEmpty())
                                log.error("finance [subject code] [buy|sell] [count] \nex) finance btc-usd buy 0.1");
                            else {
                                var e = Finance(args[1]);
                                if (e instanceof String) log.error("finance: subject code not found");
                                else log.info(args[1] + ": " + e);
                            }
                        } else if (args.length >= 4) {
                            if (!args[3].isEmpty()) {
                                double d;
                                var e = Finance(args[1]);
                                double de = 0;
                                try {
                                    d = Double.parseDouble(args[3]);
                                    de = (double) e * d;
                                } catch (NumberFormatException exception) {
                                    log.error("count is Number");
                                    continue;
                                }
                                if (e instanceof String) log.error("finance: subject code not found");
                                if (args[2].equals("buy")) {
                                    Objects.requireNonNull(pw).println("finance buy" + args[2] + " " + args[3]);
                                } else if (args[2].equals("sell")) {
                                    Objects.requireNonNull(pw).println("finance sell" + args[2] + " " + args[3]);
                                } else {
                                    log.error("error");
                                }
                            } else log.error("finance [subject code] [buy|sell] [count]");
                        } else log.error("finance [subject code] [buy|sell] [count]");
                        break;
                    case "money":
                        int money = 0;
                        log.info("your money: " + money);
                        break;

                    default:
                        ClientLauncher.log.error("명령어가 존재하지 않습니다");
                        try {
                            System.out.print(ClientLauncher.ID + ":" + InetAddress.getLocalHost() + "$ ");
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            } else {
                pw.println(arg);
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClientLauncher.t2.stop();
        pw.close();
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

}
