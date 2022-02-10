import net.intt.util.LogManager;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class ColorChat {
    static LogManager log = new LogManager("Server");

    public static void main(String[] args) {
        try {
            Socket socket = null;
            socket = new Socket("localhost", 56077);
            System.out.println("서버에 접속 성공!");

            ListeningThread t1 = new ListeningThread(socket);
            WritingThread t2 = new WritingThread(socket);

            t1.start();
            t2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
