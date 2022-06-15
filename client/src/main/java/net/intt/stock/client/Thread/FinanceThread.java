package net.intt.stock.client.Thread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public final class FinanceThread extends AbstractThreads {

    private final String financeName;
    private boolean stop = false;

    public FinanceThread(String financeName) {
        this.financeName = financeName;
    }

    public static void main(String[] args) {
        Thread t = new Thread(new FinanceThread("TSLA"));
        t.start();
    }

    @Override
    public void run() {
        System.out.println("press any key");
        Thread wait = new Thread(new WaitThread(this));
        wait.start();
        while (true) {
            if (stop) break;
            double price = finance(financeName);
            if (price == -1) break;
            System.out.print("\r" + financeName + "'s price is " + price);
        }
        System.out.print("\r ");
        System.out.println();
    }

    private double finance(String subject) {
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
            return -1;
        }

        var idx1 = eliment.indexOf("\n<");
        String el1 = eliment.substring(0, idx1);
        var id = el1.indexOf("\">");
        String el2 = el1.substring(id+1);
        el2 = el2.replace(">", "");
        el2 = el2.replace("\n", "");
        el2 = el2.replace(" ", "");

        return Double.parseDouble(el2.replace(",", ""));
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
