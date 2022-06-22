import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scn = new Scanner(System.in);
        Thread t = new Thread(() -> {
            System.out.print(">>> ");
            String arg = scn.nextLine();
            System.out.println(arg);
        });
        t.start();
        Thread.sleep(2500);
        System.out.println("\ragb");
        System.out.print(">>> ");
    }
}
