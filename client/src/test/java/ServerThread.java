import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {

    final Socket socket;

    public ServerThread(Socket socket) { this.socket = socket; }


    @Override
    public void run() {
        while (true) {
            UserThread user = new UserThread();
            System.out.println("r");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
