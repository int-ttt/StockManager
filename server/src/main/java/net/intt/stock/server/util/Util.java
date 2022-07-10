package net.intt.stock.server.util;

import net.intt.stock.server.ServerLauncher;
import net.intt.util.LogManager;

import java.io.PrintWriter;

public class Util {

    private Util() {}

    public static void broadcast(Object msg) {
        ServerLauncher.log.info(msg);
        synchronized (ServerLauncher.broadcast) {
            if (ServerLauncher.broadcast.isEmpty()) {
                return;
            }
            for (PrintWriter pw : ServerLauncher.broadcast) {
                pw.println(msg);
            }
        }
    }
}
