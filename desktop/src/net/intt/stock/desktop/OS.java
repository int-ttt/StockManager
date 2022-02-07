package net.intt.stock.desktop;

public class OS {

    static String os = System.getProperty("os.name").toLowerCase();

    public static final boolean isMac() {
        return (os.contains("mac"));
    }
    public static final boolean isWin() {
        return (os.contains("win"));
    }
}
