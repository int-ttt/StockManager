package net.intt.stock.server;

import java.io.PrintWriter;
import java.util.List;

public class Voids {

    public void broadcast(String msg, List<PrintWriter> printWriters) {
        for (PrintWriter pw : printWriters) {
            pw.println(msg);
        }
    }
}
