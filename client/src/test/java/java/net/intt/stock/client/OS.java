package java.net.intt.stock.client;

public class OS {
    private static String os = System.getProperty("os.name").toLowerCase();

    public static final boolean isMac() {
        return (os.contains("mac"));
    }
    public static final boolean isWin() {
        return (os.contains("win"));
    }
}
