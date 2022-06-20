package net.intt.stock.client.thread;

import yahoofinance.YahooFinance;

import java.io.IOException;

public final class FinanceThread extends AbstractThreads {

    private final String financeName;
    private boolean stop = true;

    public FinanceThread(String financeName) {
        this.financeName = financeName;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(YahooFinance.get("030200.KS").getQuote().getPrice());
        Thread t = new Thread(new FinanceThread("TSLA"));
        t.start();
    }

    @Override
    public void run() {
        stop = true;
        System.out.println("press enter key");
        Thread wait = new Thread(new WaitThread(this));
        wait.start();
        while (stop) loop();
        System.out.println("\r ");
    }

    @Override
    public void loop() {
        String price = "";
        try {
            price = YahooFinance.get(financeName).getQuote().getPrice().toString();
        } catch (IOException e) {

        }
        if (price != null) {
            System.out.print("\r" + financeName + "'s price is " + price);
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
