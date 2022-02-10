import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListeningThread extends Thread {

    Socket socket = null;

    public ListeningThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            do {
                System.out.println(reader.readLine());
            } while (!socket.isClosed());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
