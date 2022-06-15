package net.intt.stock.server;

import org.intt.util.LogManager;

import java.io.PrintWriter;
import java.util.Scanner;

public class ChatThread implements Runnable {

    LogManager log = ServerLauncher.log;

    @Override
    public void run() {
        Scanner scn = new Scanner(System.in);
         loop: while (true) {
            System.out.print("server$ ");
            String[] line = scn.nextLine().split("\\s");

            switch (line[0]) {
                case "quit" -> {
                    ServerLauncher.quit = true;
                    break loop;
                }
                case "broadcast" -> {
                    if (line.length <= 2) {
                        String s = "";
                        for (int i = 1; i < line.length; i++) {
                            s += line[i];
                        }
                        broadcast(s);
                    }
                }
            }
        }
    }

    public void broadcast(Object msg) {
        synchronized (ServerLauncher.broadcast) {
            if (ServerLauncher.broadcast.isEmpty()) {
                log.error("there is no accessor on the server");
                return;
            }
            for (PrintWriter pw : ServerLauncher.broadcast) {
                pw.println(msg);
            }
        }
    }
}
