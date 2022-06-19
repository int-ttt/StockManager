package net.intt.stock.client.Thread;

import net.intt.stock.client.ClientLauncher;
import org.intt.util.LogManager;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class InputThread extends AbstractThreads {

    private static final String prefix = "finance";
    private static final LogManager log = ClientLauncher.log;
    public boolean isStop() {
        return this.stop;
    }
    private int return_;
    private final Scanner scn = new Scanner(System.in);
    private final PrintWriter pw;

    public InputThread(PrintWriter pw) {
        this.pw = pw;
    }

    @Override
    public void loop() {
        System.out.print("\r" + prefix + "> ");
        String arg = scn.nextLine();
        String []args = arg.split("\\s");
        switch (args[0]) {
            case "stop", "quit" -> {
                return_ = 0;
                setStop(false);
            }
            case "finance" -> {
                if (args.length >= 2) {
                    try {
                        Stock stock = YahooFinance.get(args[1]);
                        try {
                            double d = Double.parseDouble(stock.getQuote().getAsk().toString());
                            if (args.length == 2) {
                                log.info(stock.getQuote().getPrice());
                            } else {
                                switch (args[2]) {
                                    case "price" -> log.info(stock.getQuote().getPrice());
                                    case "percent" -> log.info(stock.getQuote().getChangeInPercent());
//                                  case "buy" ->
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
//                                  case "buy" ->
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
            default -> pw.println(arg);
        }
    }

    public static String getPrefix() {
        return prefix;
    }

    public int getReturn() {
        return return_;
    }
}
