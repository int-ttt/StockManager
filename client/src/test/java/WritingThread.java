import java.net.Socket;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class WritingThread extends Thread {
    Socket socket = null;
    Scanner scn = new Scanner(System.in);
    boolean quit = false;

    public WritingThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            do {
                String[] args = scn.nextLine().split("\\s");
                if (args[0].indexOf("/") == 0) {
                    args[0] = args[0].substring(1);
                    if (args[0].equals("quit")) {
                        quit = true;
                        break;
                    }
                }
            } while (!socket.isClosed());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isQuit() {
        return quit;
    }
}