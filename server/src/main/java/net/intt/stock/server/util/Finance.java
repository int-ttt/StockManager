package net.intt.stock.server.util;

import yahoofinance.YahooFinance;

import java.io.IOException;

public class Finance {
    public static int finance(String arg, double money) throws IOException {
        int i;
        String[] args = arg.split("\\s");
        boolean buy = args[1].equals("buy");
        //pw.println("^finance " + str + " sell " + subject + " " + dou);
        if (buy) {
            double price = Double.parseDouble(YahooFinance.get(args[2]).getQuote().getPrice().toString());
        } else {
            double price = Double.parseDouble(YahooFinance.get(args[2]).getQuote().getPrice().toString());
        }
        return 0;
    }
}
