package net.intt.stock.client.thread;

import net.intt.stock.client.ClientLauncher;
import net.intt.util.LogManager;
import org.jline.reader.LineReader;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class InputThread extends AbstractThreads {

    private static final String prefix = "finance";
    private static final LogManager log = ClientLauncher.log;
    public boolean isStop() {
        return this.stop;
    }
    private int return_;
    private final LineReader reader = ClientLauncher.reader;
    private final PrintWriter pw;
    private final BufferedReader br;
    private String id;
    private final ChatThread chat;

    public InputThread(BufferedReader br, PrintWriter pw) {
        this.br = br;
        this.pw = pw;
        //this.id = ClientLauncher.ID;
        this.chat = new ChatThread(this.id);
    }

    @Override
    public void loop() {
        String arg = reader.readLine(prefix + "$ ");
        String[] args = arg.split("\\s");
        switch (args[0]) {
            case "stop", "quit" -> {
                return_ = 0;
                setStop(false);
                System.exit(0);
            }
            case "finance" -> {
                if (args.length >= 2) {
                    try {
                        Stock stock = YahooFinance.get(args[1]);
                        try {
                            double d = Double.parseDouble(stock.getQuote().getAsk().toString());
                            if (args.length == 2) log.info(stock.getQuote().getPrice());
                            else {
                                switch (args[2]) {
                                    case "price" -> log.info(stock.getQuote().getPrice());
                                    case "percent" -> log.info(stock.getQuote().getChangeInPercent());
                                    case "buy", "sell" -> {
                                        try {
                                            int dou = Integer.parseInt(args[3]);
                                            if (args[2].equals("buy")) {
                                                log.info(buy(args[1], dou));
                                            } else {
                                                log.info(sell(args[1], dou));
                                            }
                                        } catch (Exception e) {
                                            log.error("count is integer");
                                            log.error("finance [subject code]");
                                            log.error("finance [subject code] [price|percent]");
                                            log.error("finance [subject code] [buy|sell] [count]");
                                        }
                                    }
                                    default -> {
                                        log.error("finance [subject code]");
                                        log.error("finance [subject code] [price|percent]");
                                        log.error("finance [subject code] [buy|sell] [count]");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            try {
                                double d = Double.parseDouble(stock.getQuote().getPrice().toString());
                                log.error("use bitcoin command");
                                log.error("bitcoin [subject code]");
                                log.error("bitcoin [subject code] [price|percent]");
                                log.error("bitcoin [subject code] [buy|sell] [count]");
                            } catch (Exception ex) {
                                log.error("this subject is not exists");
                            }
                        }

                    } catch (IOException e) {
                    }
                } else {
                    log.error("finance [subject code]");
                    log.error("finance [subject code] [price|percent]");
                    log.error("finance [subject code] [buy|sell] [count]");
                }
            }
            case "bitcoin" -> {
                if (args.length >= 2) {
                    try {
                        Stock stock = YahooFinance.get(args[1]);
                        try {
                            double d = Double.parseDouble(stock.getQuote().getAsk().toString());
                            try {
                                double e = Double.parseDouble(stock.getQuote().getPrice().toString());
                                log.error("use finance command");
                                log.error("finance [subject code]");
                                log.error("finance [subject code] [price|percent]");
                                log.error("finance [subject code] [buy|sell] [count]");
                            } catch (Exception ex) {
                                log.error("this subject is not exists");
                            }
                        } catch (Exception e) {
                            if (args.length == 2) {
                                log.info(stock.getQuote().getPrice());
                            } else {
                                switch (args[2]) {
                                    case "price" -> log.info(stock.getQuote().getPrice());
                                    case "percent" -> log.info(stock.getQuote().getChangeInPercent());
                                    case "buy", "sell" -> {
                                        try {
                                            double dou = Double.parseDouble(args[3]);
                                            if (args[2].equals("buy")) {
                                                log.info(buy(args[1], dou));
                                            } else {
                                                log.info(sell(args[1], dou));
                                            }
                                        } catch (Exception ex) {
                                            log.error("count is number");
                                            log.error("finance [subject code]");
                                            log.error("finance [subject code] [price|percent]");
                                            log.error("finance [subject code] [buy|sell] [count]");
                                        }
                                    }
                                    default -> {
                                        log.error("finance [subject code]");
                                        log.error("finance [subject code] [price|percent]");
                                        log.error("finance [subject code] [buy|sell] [count]");
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                    }
                } else {
                    log.error("bitcoin [subject code]");
                    log.error("bitcoin [subject code] [price|percent]");
                    log.error("bitcoin [subject code] [buy|sell] [count]");
                }
            }
            case "chat" -> {
                chat.start();
                try {
                    chat.join();
                } catch (InterruptedException e) {
                }
            }
            default -> pw.println(arg);
        }
    }

    public static String getPrefix() {
        return prefix;
    }

    public int getReturn() {
        return return_;
    }

    public String buy(String subject, double dou) throws IOException {
        pw.println("finance buy " + subject + " " + dou);
        return br.readLine();
    }

    public String sell(String subject, double dou) throws IOException {
        pw.println("finance sell " + subject + " " + dou);
        return br.readLine();
    }

    public void chat(String str) {
        pw.println("");
    }
}
