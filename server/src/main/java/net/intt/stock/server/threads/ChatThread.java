package net.intt.stock.server.threads;

import net.intt.stock.server.ServerLauncher;
import net.intt.util.LogManager;

import java.io.PrintWriter;
import java.util.Scanner;

public class ChatThread implements Runnable {

    private final LogManager log = ServerLauncher.log;
    private boolean quit = true;

    @Override
    public void run() {
        Scanner scn = new Scanner(System.in);
        while (quit) {
            System.out.print("server$ ");
            String arg = scn.nextLine();
            String[] args = arg.split("\\s");

            switch (args[0]) {
                case "quit" -> {
                    ServerLauncher.setQuit(false);
                    this.setQuit(false);
                    ServerThread.setQuit(false);
                }
                case "broadcast" -> {
                    String str = arg.replace("broadcast ", "");
                    broadcast(str);
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

    public void setQuit(boolean quit) {
        this.quit = quit;
    }
}
