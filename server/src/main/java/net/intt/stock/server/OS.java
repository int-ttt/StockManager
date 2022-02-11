package net.intt.stock.server;

import java.io.File;
import java.io.IOException;

public class OS {

    private static String os = System.getProperty("os.name").toLowerCase();

    public static final boolean isMac() {
        return (os.contains("mac"));
    }
    public static final boolean isWin() {
        return (os.contains("win"));
    }
}
