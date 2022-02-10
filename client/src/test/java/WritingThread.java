import java.net.Socket;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class WritingThread extends Thread {
    Socket socket = null;
    Scanner scanner = new Scanner(System.in);

    public WritingThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            do {
                writer.println(scanner.nextLine());
            } while (!socket.isClosed());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}