package java.net.intt.stock.client;

import net.intt.util.LogManager;
import yahoofinance.YahooFinance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

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
        loop:
        while (true) {
            Scanner scn = new Scanner(System.in);
            String arg = scn.nextLine();

            String[] args = arg.split("\\s");

            if (args.length == 0) pw.println("");
            else if (args[0].indexOf("/") == 0) {
                args[0] = args[0].substring(1);
                try {
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
                                    var e = YahooFinance.get(args[1]).getQuote().toString();
                                    if (e == null) log.error("finance: subject code not found");
                                    else log.info(args[1] + ": " + e);
                                }
                            } else if (args.length >= 4) {
                                if (!args[3].isEmpty()) {
                                    double d;
                                    var e = YahooFinance.get(args[1]).getQuote().getPrice().toString();

                                    try {
                                        d = Double.parseDouble(args[3]);
                                    } catch (NumberFormatException exception) {
                                        log.error("count is Number");
                                        continue;
                                    }

                                    try {
                                        var v = Double.parseDouble(e) * d;
                                    } catch (Exception exception) {log.error("finance: subject code not found");}
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
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
}
