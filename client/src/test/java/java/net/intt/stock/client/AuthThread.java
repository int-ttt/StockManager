package java.net.intt.stock.client;

import net.intt.stock.client.ClientLauncher;
import org.intt.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class AuthThread extends Thread {

    private BufferedReader br;
    private LogManager log = ClientLauncher.log;

    public AuthThread(Socket socket) throws IOException {
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = br.readLine()) != null) {
                log.info(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        log.error("서버와의 연결이 끊겼습니다");
    }
}
