package net.intt.stock.client.Thread;

import net.intt.stock.client.ClientLauncher;
import org.intt.util.LogManager;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

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
                        if (stock.getQuote().getAsk() != null) {
                            if (stock.getQuote().getPrice() != null) {
                                if (args.length == 2) {
                                    log.info(stock.getQuote().getPrice());
                                } else {
                                    switch (args[2]) {
                                        case "price" -> log.info(stock.getQuote().getPrice());
                                        case "percent" -> log.info(stock.getQuote().getChangeInPercent());
//                                        case "buy" ->
                                    }
                                }
                            } else {
                                log.error("use bitcoin command");
                                log.error("bitcoin [subject code]");
                                log.error("bitcoin [subject code] [price|percent]");
                                log.error("bitcoin [subject code] [buy|sell] [count]");
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
