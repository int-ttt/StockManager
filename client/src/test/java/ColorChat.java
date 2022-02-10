import net.intt.util.LogManager;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class ColorChat {
    static LogManager log = new LogManager("Server");

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 56077);

            testThread t1 = new testThread(socket);
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class testThread extends Thread {

    private PrintWriter pw;

    public testThread(Socket socket) {
        try {
            pw = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String arg = null;
            try {
                System.out.print(InetAddress.getLocalHost() + "$ ");
                arg = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] args = arg.split("\\s");

            if (args[0].equals("exit")) {
                break;
            } else {
                pw.println(arg);
            }
        }
    }
}
