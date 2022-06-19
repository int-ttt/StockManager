import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws IOException {
        String[] list = {"adfs", "asd", "adfas"};

//        list.add("adfs");
//        list.add("asd");
//        list.add("adfas");

        System.out.println(list + "," + list.length);

        System.out.println(YahooFinance.get("Btc-usd").getQuote(true).getChangeInPercent());
    }
}
