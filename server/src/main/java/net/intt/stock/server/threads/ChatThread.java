package net.intt.stock.server.threads;

import net.intt.stock.server.ServerLauncher;
import net.intt.stock.server.util.Util;
import net.intt.util.LogManager;
import org.jline.reader.LineReader;

import java.io.PrintWriter;
import java.util.Scanner;

public class ChatThread implements Runnable {

    private final LogManager log = ServerLauncher.log;
    private boolean quit = true;
    private final LineReader reader = ServerLauncher.reader;

    @Override
    public void run() {
        while (quit) {
            String arg = reader.readLine("server$ ");
            String[] args = arg.split("\\s");

            switch (args[0]) {
                case "quit" -> System.exit(1);
                case "help" -> help();
                case "moo" -> {
                    log.info("                 (__)");
                    log.info("                 (oo)");
                    log.info("           /------\\/");
                    log.info("          / |    ||");
                    log.info("         *  /\\---/\\");
                    log.info("            ~~   ~~");
                }
                case "broadcast" -> {
                    if (args.length < 2) continue;
                    String str = arg.replace("broadcast ", "");
                    Util.broadcast(str);
                }
            }
        }
    }

    private void help() {
        String str = """
                    quit - quit the game
                    broadcast - broadcast message
                    say - say message
                    moo - moooooooooo
                    playerList - get player List
                """;
        log.info(str);
    }

    private void broadcast(Object msg) {
        log.info(msg);
        synchronized (ServerLauncher.broadcast) {
            if (ServerLauncher.broadcast.isEmpty()) {
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
