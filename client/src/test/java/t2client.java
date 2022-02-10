import net.intt.util.LogManager;

public class t2client {
    static LogManager log = new LogManager("Server");

    public static void main( String[] args ) {
        String ip = "localhost";
        int port = 56077;
        new Client1(ip, port);
    }
}
